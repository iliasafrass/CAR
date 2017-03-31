
import java.util.ArrayList;
import java.util.HashMap;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * 
 * @author AFRASS ILIAS
 *
 */
public class Graphe {

	private final ActorSystem system = ActorSystem.create("SystemGraphe");
	private HashMap<String, ActorRef> noeuds;

	/**
	 * Création de l'arbre dans le document de Car
	 */
	public Graphe() {
		ArrayList<ActorRef> parent = new ArrayList<ActorRef>();
		ArrayList<ActorRef> childOfTwo = new ArrayList<ActorRef>();
		ArrayList<ActorRef> childOfFive = new ArrayList<ActorRef>();
		ArrayList<ActorRef> arc6to4 = new ArrayList<ActorRef>();
		ArrayList<ActorRef> arc4to6 = new ArrayList<ActorRef>();

		ActorRef noeud1 = system.actorOf(Props.create(Noeud.class, "1", parent));
		ActorRef noeud2 = system.actorOf(Props.create(Noeud.class, "2", childOfTwo));
		ActorRef noeud3 = system.actorOf(Props.create(Noeud.class, "3", new ArrayList<ActorRef>()));
		ActorRef noeud4 = system.actorOf(Props.create(Noeud.class, "4", arc4to6));
		ActorRef noeud5 = system.actorOf(Props.create(Noeud.class, "5", childOfFive));
		ActorRef noeud6 = system.actorOf(Props.create(Noeud.class, "6", arc6to4));

		arc6to4.add(noeud4);
		arc4to6.add(noeud6);

		childOfTwo.add(noeud3);
		childOfTwo.add(noeud4);

		childOfFive.add(noeud6);

		parent.add(noeud2);
		parent.add(noeud5);

		noeuds = new HashMap<String, ActorRef>();
		noeuds.put("1", noeud1);
		noeuds.put("2", noeud2);
		noeuds.put("3", noeud3);
		noeuds.put("4", noeud4);
		noeuds.put("5", noeud5);
		noeuds.put("6", noeud6);
	}

	public void tell(String message) {
		this.tell("1", message);
	}

	public void tell(String id, String message) {
		System.out.println("Message : " + message + ", émis par : " + id);
		noeuds.get(id).tell(message, ActorRef.noSender());
	}

}