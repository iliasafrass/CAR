
M1S2-INFO-Lille1.

AFRASS ILIAS


TP 3 : � Akka � Transfert de donn�es

I) Introduction


		L'objectif du TP �tait d'implanter une application Akka fonctionnant avec un syst�me d'acteur de message. Les acteurs peuvent communiquer entre eux uniquement par messages.

		L'arborescence est la suivante au d�but du d�veloppement.
				1
			   / \
			  2   5
			 / \  |
			3   4 6

	Suite � la question 5 l'arborescence est la suivante : 
				1
			   / \
			  2   5
			 / \  |
			3   4_6

II) Le programme

Pour lancer, il faut executer la classe MainAkka.

Pour tester le TP, il suffit de d�commenter le code du MainAkka qui correspond � la question � tester.

On peut pr�ciser l'acteur � partir duquel on souhaite propag� le message � la cr�ation d'un Arbre, ArbreReparti ou d'un Graphe.

Nous avons respect� la forme de l'arbre du TP.
Pour l'arbre r�parti, il est r�parti sur 2 syst�me. Les acteurs 1, 2 et 3 sont sur le premier syst�me. Les acteurs 4, 5 et 6 sont sur le deuxi�me.
Pour le graphe, nous avons, comme dans le TP, cr�� un arc entre l'acteur 4 et l'acteur 6.
Notre graphe est non orient� (Un lien va de 4 � 6 et un autre de 6 � 4).

Pour la partie sur les graphes, nous avons fait en sorte qu'un noeud n'envoie pas deux fois le m�me message. 
Cependant, un noeud peut recevoir plusieur fois le m�me message de la part de noeuds diff�rents. 
Le comportement n'�tant pas pr�cis� dans le TP, nous avons opt� pour appliqu� la logique vu dans les pr�c�dents TD.

Les tests n�ont pas pu �tre mis en place avec JUnit, mais nous avons r�dig� les sc�narios de test suivant:

Pour les arbres :
	- v�rifier que le message est bien envoyer � tous les fils.
	- v�rifier que le message est bien envoyer par le bon p�re.

Pour le graphe :
	- v�rifier que le message est envoy� qu'une fois par noeud.

III) Architecture 

	1) Les classes 

		MainAkka : La classe principale de l'application, elle contient le main. 

		Noeud : La classe repr�sentant un noeud de notre arbre.
			Elle poss�de des enfants. Lorsqu'un noeud re�oit un message il le rediffuse � ses enfants si il ne l'a pas d�j� re�u avant.
		
		Msg : La classe repr�sentant un message qui doit �tre trait�.
	
		Arbre : L'arbre du TP est cr�� dans cette classe.
		ArbreReparti : L'arbre r�parti sur plusieur systems est cr�� dans cette classe. 
		Graphe : Le graphe de la question 5 est cr�e dans cette classe.
	