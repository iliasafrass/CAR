package CMD;

import CTE.Ctes;
import sun.reflect.generics.reflectiveObjects.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Afrass on 15/02/2017.
 */
public class MapCMD implements NativeCMD {

    // nom d'utilisateur du courant utilisateur.
    private String user;

    //authentifié: true / non authentifié: false
    private boolean isAuth;

    //utilisateurs et leurs mot de passe.
    private Map<String,String> userPassword;

    private String defaultDir;

    private String currentDir;

    public MapCMD(String folder){
        this.user = "";
        this.isAuth = false;
        this.initUserPassword();
        this.defaultDir = folder;
        this.currentDir = folder;
    }

    public void initUserPassword(){
        this.userPassword = new HashMap<String, String>();
        this.userPassword.put("ilias", "afrass1");
        this.userPassword.put("ayoub", "afrass2");
        this.userPassword.put("manar", "afrass3");
    }


    @Override
    public boolean userExist(String user) throws NotImplementedException {
        if (this.userPassword.containsKey(user)) {
            this.user = user;
            return true;// nom utilisateur correct.
        }
        return false;
    }

    @Override
    public boolean goodPassword(String password) throws BadOrderException, NotImplementedException {
        if ((this.user == null) || ("".equals(this.user.trim()))) {
            throw new BadOrderException();
        }
        if (this.userPassword.get(this.user).equals(password)) {
            this.isAuth = true;
            return true;
        }
        return false;
    }

    @Override
    public Path getFilePath(String name) throws BadOrderException, NotImplementedException {
        if (!isAuthenticated()) {
            throw new BadOrderException();
        }
        if(!name.startsWith("/")){
            name= this.currentDir+"/"+name;
        }
        System.out.println(name);
        return Paths.get(name);
    }

    @Override
    public void directoryUp() throws BadOrderException, NotImplementedException {
        if (!isAuthenticated()) {
            throw new BadOrderException();
        }
        this.currentDir=new File(this.currentDir).getParent();
    }

    @Override
    public void changeDirectory(String dir) throws BadOrderException, NotImplementedException, NotDirException {
        if (!isAuthenticated()) {
            throw new BadOrderException();
        }
        System.out.println("avant startwith "+dir);
        System.out.println(dir.startsWith("/"));
        if(!dir.startsWith("/")){
            dir= this.currentDir+"/"+dir;
        }
        System.out.println("apres startwith "+dir);
        System.out.println(dir);
        final File directory = new File(dir);
        System.out.println(directory.isDirectory());
        if(!directory.isDirectory()){
            throw new NotDirException();
        }
        this.currentDir =dir;
    }

    @Override
    public String currentDir() throws BadOrderException, NotImplementedException {
        if (!isAuthenticated()) {
            throw new BadOrderException();
        }
        return this.currentDir;
    }

    @Override
    public String getFilesList() throws BadOrderException, NotImplementedException {
        String answer = "";
        final File dir = new File(this.currentDir);
        final File filesList[] = dir.listFiles();
        String currentFile = "";

        // pour le format de list :http://stackoverflow.com/questions/2443007/ftp-list-format

        System.out.println(filesList);
        for (final File file : filesList) {
            if (!file.isHidden()) {
                int type=6;
                String permstr  ="drw-rw-rw-";

                if (file.isFile()){
                    type=1;
                    permstr= "-rw-rw-rw-";
                }
                final Date date=new Date(file.lastModified());
                final SimpleDateFormat df2 = new SimpleDateFormat("yyyy MMM dd");
                final String dateText = df2.format(date);

                String username;
                try {
                    username = java.nio.file.Files.getOwner(file.toPath()).toString();
                } catch (final IOException e) {
                    // TODO Auto-generated catch block
                    username="utilisateur inconnu !";
                }
                currentFile = String.format( "%s %d %-10s  %10d %s %s\r\n",
                        permstr, type,username,
                        file.length(), dateText,
                        file.getName());

                answer += currentFile + Ctes.END_LINE;
            }
        }
        return answer;
    }

    @Override
    public boolean isAuthenticated() throws NotImplementedException {
        return this.isAuth;
    }
}
