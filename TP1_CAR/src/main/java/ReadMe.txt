

        TP 1 : Implémentation d'un serveur FTP en Java

Binome : Ilias Afrass et Anas Taibi.


I) Introduction


L'objectif du TP était d'implanter un serveur FTP en Java qui devait fonctionner avec un client FTP.
Il devait au moins être capable de gérer les commandes suivantes : USER, PASS, RETR, STOR, LIST, QUIT, PWD, CWD, et CDUP.


II) Le programme

	Le serveur écoute sur le port 2017 et attend une connexion sur ce dernier.
	Une fois la connexion établie le Serveur créé un nouveau Thread qui sera chargé de s'occuper du client qui vient de se connecter.
	Le serveur se met à nouveau à écouter sur le port 217 en attente d'un nouveau client.
	Le Thread créé est un FTPRequest, ce dernier est chargé de gérer toutes les commandes que le client lui envoie, jusqu'à ce que ce dernier décide de quitter la connexion.

III) Les commandes

	USER <Nom de l'utilisateur> : Permet d'authentifier l'utilisateur, cette action est nécessaire avant toute autres actions sur le serveur

	PASS <Mot de passe de l'utilisateur> : Permet de finaliser l'authentification de l'utilisateur, cette action est nécessaire avant toute autres actions sur le serveur

	QUIT : Indique que le client souhaite terminer la connexion avec le serveur

	Une fois Authentifié :

		RETR <nom du fichier> : Récupère le fichier distant qui porte le nom du fichier dans le répertoire courant distant

		STOR <nom du fichier> : Dépose le fichier local du répertoire courant qui porte le nom du fichier dans le répertoire distant

		LIST : Demande au serveur de donner une description des fichiers et dossiers du répertoire courant distant

		PWD : Demande au serveur de donner la valeur du répertoire courant distant

		CWD <chemin> : Demande au serveur de faire du chemin le nouveau répertoire courant

		CDUP : Permet d'accéder au répertoire parent


IV) Architecture

	1) Les classes

		Serveur : La classe principale de l'application, elle contient le main.
			C'est cette dernière qui va tourner en boucle en attendant que des clients se connectent. A chaque nouvelle connexion la classe créé un nouveau thread : FTPRequest

		FTPRequest : La classe gérant l'ensemble des commandes citer ci-dessus.
			Elle gère aussi les connexions supplémentaires si nécessaire (mode passif/ actif) pour les commandes STOR, RETR et LIST.

		NativeCMD : C'est une interface qui permet de definir toute les methodes des commandes déjà cité.
			Les classes l'implementant gardent connaissance dans quel état est le client afin de ne pas lui permettre d'utiliser des commandes qui lui serait interdit.

		MapCMD : c'est un classe qui implemente l'interface NativeCMD et qui defini toute les methodes déjà declaré dans NativeCMD.

	2 ) Gestion des erreurs

		L'ensemble des exceptions déclenchées, que ce soit par l'ouverture de socket, ou lié à l'écriture dans le socket et la lecture dans le socket, ont été capturées pour envoyer des messages d'erreur dans les logs du serveur.
		Les erreurs lié aux commandes utilisateurs sont capturés dans FTPRequest et envoyés au client sous forme de message "5XX message d'erreur"

	3) Tests

		La classe FTPRequestTest contient des tests unitaires réalisés à l'aide de JUnit 4 et Mockito.
		Grâce à ce dernier nous avons pu vérifier les bons renvois de la part de la méthode processRequest ainsi que l'enchainement de quelques commandes.
