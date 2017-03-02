 /**
 * Created by Afrass on 31/01/2017.
 */

 package CodeFTP;

 //literal pour donner l'autorisation d'envoyer n'importe quelle commande.

import CMD.*;
import CTE.Ctes;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class FtpRequest implements  Runnable{


    /** The socket needed to stay connect to the client **/
    private final Socket socket;

    /** Give if the user is authenticated **/
    private boolean isAuthenticated;

    /** The address of connected client **/
    private final InetAddress clientAddr;

    /** Stream containing the incomming data **/
    private final InputStream dataIn;

    /** Stream where FtpRequest will write the data to send **/
    private final OutputStream dataOut;

    /** Define if the clientIsStillConnected **/
    private boolean clientIsConnected;

    /** The port to communicate with the client **/
    private int communicationPort;

    /** Return if the transfert mode is passive or not **/
    private boolean isPassive;

    /** Passive socket for passive connection **/
    private final ServerSocket passiveSocket;

    /** Set one behavior for the resquest system**/
    private final NativeCMD cmd;

    private ChannelData dataChannel;






    public FtpRequest(final Socket socket, final NativeCMD cmd, ChannelData channelData) throws IOException {
        this.socket = socket;
        this.dataIn = this.socket.getInputStream();
        this.dataOut = this.socket.getOutputStream();
        this.clientAddr = this.socket.getInetAddress();
        this.communicationPort = this.socket.getPort();
        this.isAuthenticated = false;
        this.cmd=cmd;
        this.dataChannel=channelData;
        this.clientIsConnected = true;
        // par defaut on est en active, attendre le PASV (pas implementer pour l'instant)
        this.isPassive = false;
        this.passiveSocket = new ServerSocket(0);
    }


    private String cleanCmd(final String cmd) {
        return cmd.replaceAll("\n|\r", "");
    }

    @Override
    public void run() {
        try {
            this.sendMessage(Ctes.RESPONSE_220_WELCOME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.dataIn));
        while (this.clientIsConnected) {
            try {
                final String message = bufferedReader.readLine();
                this.processRequest(message);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.socket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client Disconnect");
    }


    private void sendMessage(String msg) throws IOException {
        msg += Ctes.END_LINE;
        this.dataOut.write(msg.getBytes());
        this.dataOut.flush();
    }

    private void processRequest(String message) {
        if (message == null) {
            return;
        }
        System.out.println("Demande : " + message);

        //limit = 2 str = test1 test2 test3 ==> resultat de req : test1 et test2 test3.
        final String[] req = message.split(" ", 2);
        String mess;
        try{
            final String request = req[0].toUpperCase();
            if (Ctes.CMD_USER.equals(request) && (req.length == 2) ) {
                mess = this.processUser(this.cleanCmd(req[1]));
            } else if (Ctes.CMD_PASS.equals(request) && (req.length == 2)) {
                mess = this.processPass(this.cleanCmd(req[1]));
            } else if (Ctes.CMD_RETR.equals(request) && (req.length == 2)) {
                mess = this.processRetr(this.cleanCmd(req[1]));
            } else if (Ctes.CMD_STOR.equals(request) && (req.length == 2)) {
                mess = this.processStor(this.cleanCmd(req[1]));
            } else if (Ctes.CMD_LIST.equals(request)) {
                mess = this.processList();
            } else if (Ctes.CMD_QUIT.equals(request)) {
                mess = this.processQuit();
            } else if (Ctes.CMD_PWD.equals(request)) {
                mess = this.processPwd();
            }
            else if (Ctes.CMD_CWD.equals(request)  && (req.length == 2)) {
                mess = this.processCwd(this.cleanCmd(req[1]));
            }  else if (Ctes.CMD_CDUP.equals(request)) {
                mess = this.processCdup();
            } else {
                mess = "500 unkown command";
            }
        }catch(BadOrderException e){
            mess = Ctes.ERREUR_530;
        }catch(NotDirException e){
            mess = Ctes.ERREUR_400_NotDir;
        }catch(NoSuchFileException e){
            mess = Ctes.ERREUR_404;
        }catch(ConnectException e){
            mess = Ctes.ERREUR_500_dataChan;
        }catch(NotImplementedException e){
            mess = Ctes.ERREUR_500_implent;
        }catch(Exception e){
            e.printStackTrace();
            mess = "500 unkown server erreur";
        }
        try {
            this.sendMessage(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processUser(String user)throws NotImplementedException {
        if (this.cmd.userExist(user)) {
            return Ctes.RESPONSE_331_USER;// User name okay
        }
        return Ctes.RESPONSE_530_USER;
    }

    private String processPass(String password) throws BadOrderException, NotImplementedException{
        if (this.cmd.isAuthenticated()) {
            return Ctes.RESPONSE_230_PASS;
            // si
            // on accepte les connexions
            // successives ou pas) : Already
            // logged in.
        }

        if (this.cmd.goodPassword(password)) {
            this.isAuthenticated = true;
            return "connexion a reussi ! \n"+Ctes.RESPONSE_257_PASS_BEGIN + this.cmd.currentDir() + Ctes.RESPONSE_257_PASS_END;
        }
        return Ctes.RESPONSE_530_PASS; // Not logged in
    }


    //get
    private String processRetr(String fileName) throws BadOrderException, NotImplementedException, ConnectException, NoSuchFileException {

        final Path target = this.cmd.getFilePath(fileName);
        System.out.println(target.toString());

        try {
            this.sendMessage(Ctes.RESPONSE_150_LIST);
        } catch (final IOException e2) {
            e2.printStackTrace();
        }

        System.out.println(this.clientAddr);
        System.out.println(this.communicationPort);
        System.out.println(this.isPassive);
        System.out.println(this.passiveSocket);
        Socket communicationSocket =this.dataChannel.getNewChannelData(this.clientAddr, this.communicationPort,this.isPassive, this.passiveSocket);

        OutputStream os = null;
        DataOutputStream dos = null;
        try {
            os = communicationSocket.getOutputStream();
            Files.copy(target, os);
            dos = new DataOutputStream(os);
            dos.writeBytes( Ctes.RESPONSE_226_RETR+ Ctes.END_LINE);
           // communicationSocket.close();
            communicationSocket = null;
        } catch (final NoSuchFileException e) {
            throw new NoSuchFileException(fileName);
        }catch (final IOException e) {
            e.printStackTrace();
        }

        return Ctes.RESPONSE_226_RETR;
    }


    //put
    private String processStor(String fileName) throws BadOrderException, NotImplementedException, ConnectException, NoSuchFileException {
        final Path target = this.cmd.getFilePath(fileName);

        try {
            this.sendMessage(Ctes.RESPONSE_150_LIST);
        } catch (final IOException e2) {
            e2.printStackTrace();
        }

        Socket communicationSocket =this.dataChannel.getNewChannelData(this.clientAddr, this.communicationPort,this.isPassive, this.passiveSocket);

        OutputStream os = null;
        DataOutputStream dos = null;
        InputStream is = null;

        try {
            is = communicationSocket.getInputStream();
            Files.copy(is,target, StandardCopyOption.REPLACE_EXISTING);
            os = communicationSocket.getOutputStream();
            dos = new DataOutputStream(os);
            dos.writeBytes( Ctes.RESPONSE_226_STOR+ Ctes.END_LINE);
            communicationSocket.close();
            communicationSocket = null;
        }catch (final IOException e) {
            e.printStackTrace();
        }

        return Ctes.RESPONSE_226_STOR;
    }


    //ls sur linux | dir sur windows.
    private String processList()throws BadOrderException, NotImplementedException, ConnectException {
        if (!this.isAuthenticated) {
            return Ctes.ERREUR_530;
        }
        try {
            this.sendMessage(Ctes.RESPONSE_150_LIST);
        } catch (final IOException e2) {
            e2.printStackTrace();
        }

        final String answer = this.cmd.getFilesList();

        Socket communicationSocket =this.dataChannel.getNewChannelData(this.clientAddr, this.communicationPort,this.isPassive, this.passiveSocket);

        OutputStream os = null;
        DataOutputStream dos = null;

        try {
            os = communicationSocket.getOutputStream();
            dos = new DataOutputStream(os);
            dos.writeBytes(answer + Ctes.RESPONSE_226_LIST+ Ctes.END_LINE);
            System.out.println("Server says : " + answer);
            communicationSocket.close();
            communicationSocket = null;
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return Ctes.RESPONSE_226_LIST;
    }

    //tapez quit.
    private String processQuit() throws IOException{
        this.clientIsConnected = false;
        return Ctes.RESPONSE_231_QUIT;
    }

    // taper pwd.
    private String processPwd() throws BadOrderException, NotImplementedException{
        System.out.println("server : pwd :"+this.cmd.currentDir());
        return Ctes.RESPONSE_257_PWD + this.cmd.currentDir();
    }

    //cd + entree ==> saisie le reprtoire distant.
    private String processCwd(final String rep) throws BadOrderException, NotImplementedException, NotDirException  {
        this.cmd.changeDirectory(rep);
        return Ctes.RESPONSE_250_CWD;
    }

    //cd ..
    private String processCdup()throws BadOrderException, NotImplementedException  {
        this.cmd.directoryUp();
        return Ctes.RESPONSE_200_CDUP;
    }

}
