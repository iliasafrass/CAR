package Pages;

import org.apache.commons.net.ftp.FTPFile;


/**
 * 
 * Classe permettant la génération d'une page décrivant un dossier
 * 
 * @author Afrass Ilias
 *
 */
public class GetFTPPage {

	final static String head="<html><head>"
			+ "<script src=\"http://www.mutu.rpdiv.com/script3.js\"></script>"
			+"<style> h1 { color: #FF0000; } </style>"
			+"<style> h2 { color: #00FF00; } </style>"
			+ "</head><body>"
			+ "<h1>REST'S AFRASS & TAIBI :)</h1>"
			+ "<h2>Server's files :</h2>";
	final static String end = "</body></html>";
	final static String form="<form action='' enctype='multipart/form-data' method=\"POST\">"
			+ "<input type=\"text\" name=\"name\" placeholder=\"nom\" required>"
			+ "<input type=\"file\" name=\"file\" id=\"file\" required/>"
			+ "<input value=\"Envoyer au serveur\" type=\"submit\" /><br /></form>";
	
	/**
	 * Retourne la description d'une page décrivant un dossier
	 * @param path le chemin du dossier dans lequel on est
	 * @param list la liste des fichiers obtenus par le client
	 * @return la description de la page html
	 */
	static public String getDir(final String path, final FTPFile[] list){
		String html= head+form;
		System.out.println("(getftppage)Number of files in dir: " + list.length);
		for(FTPFile f : list){
			
			if(!f.getName().equals(".")){
				String name= path + f.getName();
				html += "<p>" ;
				html += "Nom : " + f.getName() + " / ";
				html += "Taille en octets : " + f.getSize() + " / ";
				if(f.isFile()){
					html += "  <a href =" + "/rest/tp2/ftp/download/" +name+ ">T&eacute;l&eacute;charger</a>\n" ;
				}
				if(f.isDirectory()){
					html += "  <a href =" + "/rest/tp2/ftp/" + name + "/" + ">Ouvrir</a>\n" ;
				}
				html += "<button onclick=\"function(){del("+name+")}\">X</button></p>";
			}
		}
		return html+end;
		
	}
}