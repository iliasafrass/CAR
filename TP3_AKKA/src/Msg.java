
/**
 * Classe Msg pour le message diffuser entre les noeuds.
 * 
 * @author AFRASS ILIAS
 *
 */


public class Msg {
	private int identifiant;
	private String text;

	public Msg(String msg, String envoyeur, int identifiant) {
		this.identifiant = identifiant;
		this.text = msg;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public String getText() {
		return text;
	}

}
