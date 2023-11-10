package listeDeTacheTest;

public class main {

	public static void messageErreur() {
		String msgErr = "Erreur, choix du menu invalide... Recommencez!";
		System.out.println(msgErr);
	}

	public static void messageSollicitation() {
		String msgSoll = "Entrez votre choix : ";
		System.out.println(msgSoll);
	}

	public static void revenirAuMenu() {
		String revenirAuMenu = "\nAppuyez sur <ENTREE> pour revenir au menu... \n";
		System.out.println(revenirAuMenu);
	}

	public static void afficherMenu() {
		String msgMenu = "----" + "\nMENU\n" + "----\n" + "1. Afficher liste\n" + "2. Ajouter tache(s)\n"
				+ "3. Supprimer taches\n" + "4. Vider liste\n" + "5. Quitter le programme\n";
		System.out.println(msgMenu);
	}

	// Cette méthode permet d'ajouter des tâches
	public static String ajouterTache(String liste, int compte) {
		String msgAjoutTache = "Tache a ajouter (<ENTREE> pour terminer): ";
		String msgAccueilTache = "\n-- AJOUTER TACHE(S) --\n";
		String msgErrTache = "Erreur, la tache doit contenir entre 3 et 80 caracteres inclusivement... Recommencez !";
		String tacheLue;
		int nbCaracteresMin = 3;
		int nbCaracteresMax = 80;
		boolean finAjoutTache = false;
		System.out.println(msgAccueilTache);
		while (!(finAjoutTache)) {
			System.out.print(msgAjoutTache);
			tacheLue = Clavier.lireString();
			if (tacheLue.equals("")) { // si ce que l'utilisateur entre est nul, finir d'ajouter des tâches
				finAjoutTache = true;
			} else if (tacheLue.length() >= nbCaracteresMin && tacheLue.length() <= nbCaracteresMax && tacheLue != "") {
				compte++;
				liste += compte + ". " + tacheLue + "\n";
			} else {
				System.out.println(msgErrTache);
			}
		} // while (finAjoutTache)
		return liste;
	}

	static void afficherListe(String liste) {
		String msgListeVide = "===============\n" + "CHOSES A FAIRE\n" + "===============\n"
				+ "AUCUN ITEM DANS LA LISTE";
		String msgChosesAFaire = "\n===============\n" + "CHOSES A FAIRE\n" + "===============\n";

		if (liste.equals("")) {
			System.out.println(msgListeVide);
		} else {
			System.out.println(msgChosesAFaire);
			System.out.println(liste);
		}
		revenirAuMenu();
		Clavier.lireString();
	} // afficherListe

	public static String supprimerTaches(String liste, int compte) {
		String msgChosesAFaire = "\n===============\n" + "CHOSES A FAIRE\n" + "===============\n";
		String msgTacheASupp = "Numero de la tache a supprimer (<ENTREE> pour terminer) :";
		String msgSuppTache = "\n-- SUPPRIMER TACHE(S) --\n";
		String msgAucunItem = "AUCUN ITEM DANS LA LISTE\n";
		int nbmax = 0;
		int nbmin = 1;
		int tacheASupprimer;

		System.out.println(msgSuppTache + msgChosesAFaire + liste);
		//TODO trouver comment lire combien de tâches l'utilisateur entre, les classer par numero 
		boolean tacheValide = false;
		int index1 = liste.indexOf('1');
		int index2 =liste.indexOf('2');
		int index3 = liste.indexOf('3');
		int index4 = liste.indexOf('4');
		String substring1 = liste.substring(index1, index2);
		String substring2 = liste.substring(index2, index3);
		String substring3 = liste.substring(index3, index4);
		String substring4 = liste.substring(index4);

		substring1 = "";
		liste = substring2 +  substring3 +  substring4;
		System.out.println(liste);
		while (!tacheValide) {

			nbmax += compte;
			if (liste == "") {
				System.out.println(msgAucunItem);
				revenirAuMenu();
				Clavier.lireString();
				break;
			} else {
				System.out.println(msgTacheASupp);
				tacheASupprimer = Clavier.lireInt();
				if (tacheASupprimer < nbmin || tacheASupprimer > nbmax) {
					System.out.println(
							"Erreur, le numero doit etre entre " + nbmin + " et " + nbmax + "... Recommencez !\n");
				} else {
					System.out.println("CODEBLAAAA");
				}
				tacheValide = true;
				revenirAuMenu();
				Clavier.lireString();
			}
		} // while tache valide
		return liste;
	} // supprimerTache

	public static String viderListe(String liste) {
		String repVider;
		String msgViderListe = "\n-- VIDER LA LISTE --\n" + "\n" + "AUCUN ITEM DANS LA LISTE";
		String msgVerifVider = "Voulez-vous vraiment vider la liste (O)ui ou (N)on ? \n";
		String msgErrVider = "Erreur, vous devez repondre par O ou N... Recommencez !\n";
		String msgOppAnn = "Operation annulee.\n";
		String msgListeVidee = "Tous les items ont ete supprimes de la liste.\n";

		boolean fini = false;
		while (!fini) {
			if (liste == "") {
				System.out.println(msgViderListe);
				fini = true;
			} else {
				System.out.println(msgVerifVider);
				repVider = Clavier.lireString();
				if (repVider.equalsIgnoreCase("o")) {
					liste = "";
					System.out.println(msgListeVidee);
					fini = true;
				} else if (repVider.equalsIgnoreCase("n")) {
					System.out.println(msgOppAnn);
					fini = true;
				} else {
					System.out.println(msgErrVider);
				} // else pas valide
			} // else liste pas vide
		} // while liste Remplie
		revenirAuMenu();
		Clavier.lireString();
		return liste;
	} // viderListe

	public static void quitterProgramme() {
		String msgFinProg = "\n\nF I N   N O R M A L E   D U   P R O G R A M M E\n";
		System.out.println(msgFinProg);
	} // quitterProgramme

	public static void main(String[] args) {
		String listeTaches = "";
		int compteTaches = 0;

		boolean finDuProgramme = false;
		while (!finDuProgramme) {
			afficherMenu();
			char choixMenuLu;
			messageSollicitation();
			choixMenuLu = Clavier.lireCharLn();
			// Faire une action selon le choix
			switch (choixMenuLu) {
			case '1':
				afficherListe(listeTaches);
				break;
			case '2':
				listeTaches = ajouterTache(listeTaches, compteTaches); // retourner une nouvelle version de listeTache
																		// après modification
				break;
			case '3':
				listeTaches = supprimerTaches(listeTaches, compteTaches);
				break;
			case '4':
				listeTaches = viderListe(listeTaches);
				break;
			case '5':
				quitterProgramme();
				finDuProgramme = true;
				break;
			default:
				messageErreur();
			}// switch choixMenuLu
		} // while fin du programme
	} // main
} // ListeDeTâches
