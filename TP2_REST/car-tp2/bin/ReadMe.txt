
TP 2 : Passerelle REST.

Binome : Afrass Ilias & Taibi Anas


I) Introduction


		L'objectif du TP �tait d'implanter une passerelle REST servant d'interface � un serveur FTP.

		Elle doit utiliser toutes les capacit�s que fournis REST avec les diff�rents verbes : GET, PUT, DELETE, POST


II) Le programme


			Une fois vous avez executez le programme, la passerelle est accessible � l'adresse suivante : localhost:8080/rest/tp2/ftp/ ou localhost:8080/rest/tp2/ftp2/(si vous voulez saisir manuellement le port et l'adresse). 

	A partir de cette adresse un lien doit vous �tre proposez car vous n'�tes pas encore authentifi� � un serveur FTP.

	En suivant ce dernier vous aurez un formulaire de connexion, apr�s l'avoir rempli vous pourrez cliquer sur le lien redirigeant vers la racine.

	A partir de ce moment vous pouvez naviguer parmis les fichiers du serveur FTP.

	Un dossier sera indiqu� par le lien "ouvrir", cliquer sur ce dernier permet d'acc�der au contenu de ce dossier.

	Un fichier est indiqu� par le lien "t�l�charger", cliquer sur ce dernier permet de t�l�charger ce dernier.

	En plus de ces liens on trouve la Croix qui permet de supprimer un fichier.


III) Les verbes et leurs comportements 

	GET <Sur un dossier>: Fera appel � LIST du serveur FTP afin de connaitre tout les fichiers dans le dossier

	GET <download/Sur un fichier>: T�l�charge le fichier sur le poste gr�ce � RETR

	DELETE <Sur un fichier> : Supprime le fichier sur le serveur FTP gr�ce � DELETE

	PUT <Un fichier local> : Envoie le fichier sur le serveur FTP gr�ce � STOR, les dossiers necessaires sont cr�es

	POST <Un repertoire local> : Envoie le fichier sur le serveur FTP gr�ce � STOR. Le nom de fichier et le fichier sont pass�s via un form  les dossiers necessaires sont cr��s

IV) Architecture 

	1) Les classes 

		Starter : La classe permettant de lancer la passerelle REST.
			C'est la classe contenant le main.

		Config : La classe initialisant la configuration de la passerelle REST.
			Elle g�re aussi les connexions suppl�mentaires si n�cessaire (mode passif/ actif) pour les commandes STOR, RETR et LIST.
		
		FTPRessource : La classe contenant la gestion de nos verbes pour faire le lien avec un serveur FTP.
			Cette classe contient toutes les m�thodes permettant de s�parer les comportements selon les verbes.

		Page : Interface d'une page, doit contenir une m�thode getPage

		HomePage/HomePageWithPortConfig  : La page home lorsqu'on est pas authentifi�

		AuthPage/AuthPageWithPortConfig : La page d'authentification pour s'authentifier

		GetFTPPage : La page repr�sentant les dossiers.

	2 ) Gestion des erreurs 

		L'ensemble des IOException sont r�cup�r� au sein des m�thodes et affich�es dans les logs de la passerelle.
		On renvoie des indication html (ex mauvais login/password + formulaire de connexion) en guise d'indication pour l'utilisateur

	3) Tests

		les tests ont seulement �taient effectu� � la main.

