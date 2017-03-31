package Pages;
/**
 * Classe décrivant la page principal lorsque l'authentification n'a pas encore était faite
 * @author Afrass ilias
 *
 */
public class HomePage {
	final static String head="<html><head></head><body>";
	final static String authLink="<p><a href=" + "http://localhost:8080/rest/tp2/ftp/auth"+ ">Cliquez ici pour l'authentification</a> </p>";
	final static String end = "</body></html>";

	/**
	 * Retourne la page d'accueil redirigeant vers l'authentification
	 * @return la page d'accueil redirigeant vers l'authentification
	 */
	public static String getPage() {
		return head+authLink + end;
	}

}