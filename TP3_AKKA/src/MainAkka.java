
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
		 * �crire le programme Akka qui met en �uvre le m�canisme de transfert d�crit ci-dessus.
		 * Vous ferez en sorte de fournir des messages de trace permettant de visualiser la propagation
         * des donn�es dans l�arbre.
		 */
		
		//Arbre arbre2 = new Arbre();
		//arbre2.tell("Bonjour! �a va ?");
			

		/*
		 * Question 3 :
		 * On souhaite maintenant faire en sorte que les diffusions puissent �tre initi�es � partir de
		 * n�importe quel acteur de l�arbre (pas uniquement � partir de l�acteur racine).
		 */
		
		 //Arbre arbre3 = new Arbre();
		 //arbre3.tell("2", "Bonjour! �a va ?");


		/*
		 *  Question 4
		 *	On suppose maintenant que les acteurs sont r�partis dans deux syst�mes d�acteurs diff�rents.
		 *	Configurer votre application pour qu�elle s�ex�cute de fa�on r�partie.
		 */
		 // ArbreReparti arbre4 = new ArbreReparti();
		 // arbre4.tell("1", "Bonjour! �a va ?");


		
		/*
		 * Question 5
		 * On suppose maintenant que les acteurs sont organis�s selon une topologie qui n�est plus un
		 * arbre, mais un graphe. Par exemple, on ajoute un arc entre les n�uds 4 et 6 de l�exemple pr�-
		 * c�dent. Modifier le programme pr�c�dent pour g�rer ce nouveau cas.
		 */
		 Graphe graphe = new Graphe();
		 graphe.tell("1", "Bonjour! �a va ?");
	}
}