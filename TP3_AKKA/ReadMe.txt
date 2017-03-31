
M1S2-INFO-Lille1.

AFRASS ILIAS


TP 3 : – Akka – Transfert de données

I) Introduction


		L'objectif du TP était d'implanter une application Akka fonctionnant avec un système d'acteur de message. Les acteurs peuvent communiquer entre eux uniquement par messages.

		L'arborescence est la suivante au début du développement.
				1
			   / \
			  2   5
			 / \  |
			3   4 6

	Suite à la question 5 l'arborescence est la suivante : 
				1
			   / \
			  2   5
			 / \  |
			3   4_6

II) Le programme

Pour lancer, il faut executer la classe MainAkka.

Pour tester le TP, il suffit de décommenter le code du MainAkka qui correspond à la question à tester.

On peut préciser l'acteur à partir duquel on souhaite propagé le message à la création d'un Arbre, ArbreReparti ou d'un Graphe.

Nous avons respecté la forme de l'arbre du TP.
Pour l'arbre réparti, il est réparti sur 2 système. Les acteurs 1, 2 et 3 sont sur le premier système. Les acteurs 4, 5 et 6 sont sur le deuxième.
Pour le graphe, nous avons, comme dans le TP, créé un arc entre l'acteur 4 et l'acteur 6.
Notre graphe est non orienté (Un lien va de 4 à 6 et un autre de 6 à 4).

Pour la partie sur les graphes, nous avons fait en sorte qu'un noeud n'envoie pas deux fois le même message. 
Cependant, un noeud peut recevoir plusieur fois le même message de la part de noeuds différents. 
Le comportement n'étant pas précisé dans le TP, nous avons opté pour appliqué la logique vu dans les précédents TD.

Les tests n’ont pas pu être mis en place avec JUnit, mais nous avons rédigé les scénarios de test suivant:

Pour les arbres :
	- vérifier que le message est bien envoyer à tous les fils.
	- vérifier que le message est bien envoyer par le bon père.

Pour le graphe :
	- vérifier que le message est envoyé qu'une fois par noeud.

III) Architecture 

	1) Les classes 

		MainAkka : La classe principale de l'application, elle contient le main. 

		Noeud : La classe représentant un noeud de notre arbre.
			Elle possède des enfants. Lorsqu'un noeud reçoit un message il le rediffuse à ses enfants si il ne l'a pas déjà reçu avant.
		
		Msg : La classe représentant un message qui doit être traité.
	
		Arbre : L'arbre du TP est créé dans cette classe.
		ArbreReparti : L'arbre réparti sur plusieur systems est créé dans cette classe. 
		Graphe : Le graphe de la question 5 est crée dans cette classe.
	