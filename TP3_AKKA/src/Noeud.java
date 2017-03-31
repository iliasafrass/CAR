
import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Classe repr�sentant un noeud de l'arbre.
 * 
 * @author AFRASS ILIAS
 *
 */
public class Noeud extends UntypedActor {

	private String nom;
	private List<ActorRef> enfants;
	private List<Integer> messages;

	public Noeud(String nom, List<ActorRef> enfants) {
		this.nom = nom;
		this.enfants = enfants;
		this.messages = new ArrayList<Integer>();
	}

	public String getNom() {
		return this.nom;
	}

	/**
	 * Lorsqu'un noeud re�oit un message, il le diffuse a tous ses enfants
	 * 
	 * http://doc.akka.io/docs/akka/2.4.2/java/untyped-actors.html#receive-
	 * messages
	 */
	@Override
	public void onReceive(Object message) throws Exception {
		Msg msg = null;
		boolean envoyer = false;

		if (message instanceof Msg) {
			// Recuperer le message
			msg = (Msg) message;

			// trace permettant de visualiser la propagation (Q2)
			System.out.println("Message : " + msg.getText() + ", re�u par : " + this.nom);

			if (!messages.contains(msg.getIdentifiant())) {
				messages.add(msg.getIdentifiant());
				envoyer = true;
			}
		} else if (message instanceof String) {
			String text = (String) message;
			msg = new Msg(text, this.nom, 1);

			if (!messages.contains(msg.getIdentifiant())) {
				messages.add(msg.getIdentifiant());
				envoyer = true;
			}
		}

		if (envoyer) {
			for (ActorRef noeud : enfants) {
				// transferer le message
				// doc sur forward juste avant receive message
				noeud.forward(msg, getContext());
			}
		}
	}

}