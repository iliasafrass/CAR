
/**
 * Classe Main pour le TP-Akka.
 * 
 * @author AFRASS ILIAS
 *
 */

public class MainAkka {
	public static void main(String[] args) {
		
		System.out.println("###############################################");
		System.out.println("#	TP3-Akka ");
		System.out.println("#	Binome : AFRASS");
		System.out.println("###############################################");

		System.out.println("");

		/* Question 2:
		 * Écrire le programme Akka qui met en œuvre le mécanisme de transfert décrit ci-dessus.
		 * Vous ferez en sorte de fournir des messages de trace permettant de visualiser la propagation
         * des données dans l’arbre.
		 */
		
		//Arbre arbre2 = new Arbre();
		//arbre2.tell("Bonjour! ça va ?");
			

		/*
		 * Question 3 :
		 * On souhaite maintenant faire en sorte que les diffusions puissent être initiées à partir de
		 * n’importe quel acteur de l’arbre (pas uniquement à partir de l’acteur racine).
		 */
		
		 //Arbre arbre3 = new Arbre();
		 //arbre3.tell("2", "Bonjour! ça va ?");


		/*
		 *  Question 4
		 *	On suppose maintenant que les acteurs sont répartis dans deux systèmes d’acteurs différents.
		 *	Configurer votre application pour qu’elle s’exécute de façon répartie.
		 */
		 // ArbreReparti arbre4 = new ArbreReparti();
		 // arbre4.tell("1", "Bonjour! ça va ?");


		
		/*
		 * Question 5
		 * On suppose maintenant que les acteurs sont organisés selon une topologie qui n’est plus un
		 * arbre, mais un graphe. Par exemple, on ajoute un arc entre les nœuds 4 et 6 de l’exemple pré-
		 * cédent. Modifier le programme précédent pour gérer ce nouveau cas.
		 */
		 Graphe graphe = new Graphe();
		 graphe.tell("1", "Bonjour! ça va ?");
	}
}