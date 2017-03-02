package CTE;

/**
 * Created by Afrass on 15/02/2017.
 */
public class Ctes {

    //Commandes possibles
    public static final String CMD_USER = "USER";
    public static final String CMD_PASS = "PASS";
    public static final String CMD_STOR = "STOR";
    public static final String CMD_RETR = "RETR";
    public static final String CMD_LIST = "LIST";
    public static final String CMD_QUIT = "QUIT";
    public static final String CMD_PWD = "XPWD";
    public static final String CMD_CWD = "CWD";
    public static final String CMD_CDUP = "CDUP";

    //Reponses selon la commande recu
    public static final String RESPONSE_220_WELCOME = "220 Welcome on the FTP CodeFTP.Server\n";
    public static final String RESPONSE_331_USER = "331 User name okay, need password";
    public static final String RESPONSE_530_USER = "530 Bad username";
    public static final String RESPONSE_230_PASS = "230 Already logged in";
    public static final String RESPONSE_530_PASS = "530 Not logged in";
    public static final String RESPONSE_257_PASS_BEGIN = "257 \"";
    public static final String RESPONSE_257_PASS_END = "\"";
    public static final String RESPONSE_150_LIST = "150 Opening data channel for directory list.";
    public static final String RESPONSE_231_QUIT = "231 Bye :)";
    public static final String RESPONSE_257_PWD = "257 ";
    public static final String RESPONSE_226_LIST = "226 LIST Done";
    public static final String RESPONSE_200_CDUP = "200 OK";
    public static final String RESPONSE_250_CWD = "250 OK";
    public static final String RESPONSE_226_RETR = "226 Transfer complete.";
    public static final String RESPONSE_226_STOR = "226 Transfer complete.";
    public static final String ERREUR_530 = "530 Error : Not authenticated";
    public static final String ERREUR_500_implent = "500 Error : Not implemented command";
    public static final String ERREUR_500_dataChan = "500 Error : Fail to open a data channel";
    public static final String ERREUR_404 = "404 Error : File not found";
    public static final String ERREUR_400_NotDir = "400 Error : Not a directory";

    //retour à la ligne
    public static final String END_LINE = "\n";


}
