package CodeFTP;

/**
 * Created by Afrass on 31/01/2017.
 */

import CMD.MapCMD;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket = new Socket();
    private String initRep;
    private ChannelData channelData;

    /* Initialisation du serveur avec un port donnée.  */

    public void initServer(final int port){
        this.initRep = "C:\\Users\\Afrass\\ServerFTP";
        this.channelData = new ChannelData();

        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Initialisation du serveur a reussi sur le port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Attente d'une connexion d'un client.
     * si un client est connecté, un thread qui va etre créer.
     */
    public void run() {
        while (true) {
            System.out.println("Attente des clients ...");
            try {
                //attente du connexion.
                this.socket = this.serverSocket.accept();
                new Thread(new FtpRequest(this.socket,new MapCMD(this.initRep), this.channelData)).start();
            } catch (final IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static final void main(final String[] args){
        final Server s = new Server();
        s.initServer(2017);
        s.run();
    }

    //getters and setters
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}