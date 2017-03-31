package car.tp2;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.cxf.helpers.FileUtils;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import Pages.AuthPageWithPortConfig;
import Pages.GetFTPPage;
import Pages.HomePageWithPortConfig;



//pwd 
//cd
//cdup


@Path("/ftp2")
/**
 * Ressource REST accessible à l'adresse :
 * 
 * http://localhost:8080/rest/tp2/ftp
 * 
 * @author Afrass Ilias
 */
public class RessourcesWithPortConfig {
	final static private String url = "http://localhost:8080/rest/tp2/ftp2";

	//Client FTP
	private FTPClient ftp;
	// use to reconnect after timeout connexion
	//Le nom de l'utilisateur
	private String user;
	//Le password de l'utilisateur
	private String password;
	private int port;
	private String adresse;
	

	private boolean authentified = false;

	public RessourcesWithPortConfig(FTPClient ftp) {
		this.ftp = ftp;
	}

	/**
	 * Connecte le client au serveur FTP
	 * User : le pseudo de l'utilisateur
	 * Password : le mot de passe de l'utilisateur
	 */
	private void connexion(String user, String password,int port , String adresse) throws SocketException, IOException {
		
		
		//ftp.connect("ftp.univ-lille1.fr");
		ftp.connect("127.0.0.1", 2000);
		
		
		System.out.println("connect");
		System.out.println(ftp.getReplyString());
		System.out.println(ftp.user(user));
		System.out.println("no erreur");
		System.out.println(ftp.getReplyString());
		System.out.println(ftp.pass(password));
		System.out.println(ftp.getReplyString());

		
	}

	public boolean isAuthentified() {
		return authentified;
	}
	
	@GET
	@Path("/auth")
	@Produces("text/html")
	/**
	 * Retourne le formulaire d'authentification
	 */
	public String getConnexionForm() {
		return AuthPageWithPortConfig.getPage();
	}

	@POST
	@Path("/auth")
	@Produces("text/html")
	/**
	 * Valide l'authentification et tente de se connecter au serveur FTP
	 */
	public String postConnexionForm(@FormParam("login") String login, @FormParam("psw") String psw,@FormParam("port") int port,@FormParam("address") String address) {
		this.user = login;
		this.password = psw;
		this.port = port;
		this.adresse = address;
		
		try {
			this.connexion(login, psw,port,address);
	
			this.authentified = ftp.login(user, password);
			if(!isAuthentified()){
			return AuthPageWithPortConfig.badPwdPage();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return AuthPageWithPortConfig.postErrPage();
		}
		return AuthPageWithPortConfig.postSuccesPage();
	}
		
	
	

	
	@GET
	@Produces("application/octet-stream")
	@Path("download/{filename :.*}")

	/**
	 * Permet de télécharger le fichier sélectionner par l'utilisateur
	 */
	public Response getFile(@PathParam("filename") String filename) {
		InputStream in;
		try {
			in = this.ftp.retrieveFileStream(filename);
			Response response = Response.ok(in).build();
			ftp.completePendingCommand();
			return response;
		} catch (IOException e) {
			System.out.print("Erreur lors du téléchargement du fichier :" + filename);
		}
		return null;
	}
	 
	
	@GET
	@Path("{var: .*/}")
	@Produces("text/html")
	
	/**
	 * Permet de récupérer les fichiers et dossier d'un dossier
	 * @param path le chemin du dossier
	 * @return la page généré contenant tout les fichiers
	 */
	
	public String getStuff(@PathParam("var") String path) {
		try {
			
			return GetFTPPage.getDir(path, ftp.listFiles(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
	}
	
	//Doublon nécessaire pour le fonctionnement du home
	@GET
	@Produces("text/html")
	/**
	 * Permet de rediriger vers la page d'authentification ou d'accéder au dossier racine du serveur FTP
	 * @return
	 */
	
	public String getHome() {
		if (!ftp.isConnected()) {
			return HomePageWithPortConfig.getPage();
		}
		try {
			return GetFTPPage.getDir("", ftp.listFiles());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("text/html; charset=UTF-8")
	@Path("{var :.*}")
	/**
	 * Permet d'uploader le fichier souhaité d'un utilisateur
	 * @param file le fichier à envoyer
	 * @param name le nom du fichier quand il sera sur le serveur
	 * @param pathname le chemin du dossier actuel
	 * @return la reponse si l'action est passé ou non
	 */
	
	public Response upload( @Multipart("file") InputStream file, @Multipart("name") String name, @PathParam("var") String pathname)  {
		System.out.println(file);
		if (!ftp.isConnected()) {
			try {
				connexion(user, password,port,adresse);
			} catch (IOException e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		try {
			if (this.ftp.storeFile(pathname+name, file)) {
				file.close();
				return Response.ok().build();
			}
			return Response.serverError().build();
		} catch (IOException e) {
			System.out.print("Erreur lors du téléchargement du fichier :" + pathname+"/"+name);
		}
		return null;
	}
	
	//@DELETE
	//@Produces("text/html")
	//@Path("{var: .*}")
	/**
	 * Supprime le fichier sélectionné
	 * @param pathname le chemin du dossier actuel
	 * @return la reponse si l'action est passé ou non
	 */

	public Response deleteFile(@PathParam("var") String pathname) {
		if (!ftp.isConnected()) {
			try {
				connexion(user, password,port,adresse);
			} catch (IOException e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		try {
			if (this.ftp.deleteFile(pathname)) {
				return Response.ok().build();
			}
			return Response.serverError().build();
		} catch (IOException e) {
			System.out.print("Erreur lors du téléchargement du fichier :" + pathname);
		}
		return null;
	}
}