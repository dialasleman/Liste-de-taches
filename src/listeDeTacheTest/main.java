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

	public static void texteRevenirAuMenu() {
		String revenirAuMenu = "\nAppuyez sur <ENTREE> pour revenir au menu... \n";
		System.out.println(revenirAuMenu);
	}

	public static void afficherMenu() {
		String msgMenu = "----" + "\nMENU\n" + "----\n" + "1. Afficher liste\n" + "2. Ajouter tache(s)\n"
				+ "3. Supprimer taches\n" + "4. Vider liste\n" + "5. Quitter le programme\n";
		System.out.println(msgMenu);
	}

	public static int compterTaches(String liste) {

		int compte = 0;
		int tailleListe = liste.length();
		for (int i = 0; i < tailleListe; i++) {
			char c = liste.charAt(i);
			if (c == '\n') {
				compte++;
			}
		}
		return compte;
	}

	// Cette méthode permet d'ajouter des tâches
	public static String ajouterTache(String liste) {
		String msgAjoutTache = "Tache a ajouter (<ENTREE> pour terminer): ";
		String msgAccueilTache = "\n-- AJOUTER TACHE(S) --\n";
		String msgErrTache = "Erreur, la tache doit contenir entre 3 et 80 caracteres inclusivement... Recommencez !";
		String tacheLue;
		int nbCaracteresMin = 3;
		int nbCaracteresMax = 80;
		int compte = compterTaches(liste);
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
		texteRevenirAuMenu();
		Clavier.lireString();
	} // afficherListe

	public static String supprimerTaches(String liste) {
		String msgAucunItem = "AUCUN ITEM DANS LA LISTE\n";
		if (liste == "") {
			System.out.println(msgAucunItem);
			texteRevenirAuMenu();
			Clavier.lireString();
			return liste;
		}

		String msgChosesAFaire = "\n===============\n" + "CHOSES A FAIRE\n" + "===============\n";
		String msgTacheASupp = "Numero de la tache a supprimer (<ENTREE> pour terminer) :";
		String msgSuppTache = "\n-- SUPPRIMER TACHE(S) --\n";

		int nbmax = 10000;
		int nbmin = 1;
		int debut1;
		int fin1;
		int debut2;
		String tacheASupprimer;
		String substring1;
		String substring2;
		boolean fin = false;

		System.out.println(msgSuppTache + msgChosesAFaire + liste);

		while (!fin) {
			System.out.println(msgTacheASupp);
			tacheASupprimer = Clavier.lireString();
			int longueurListe = tacheASupprimer.length();
			for (int i = 0; i < longueurListe; i++) {
				char character = tacheASupprimer.charAt(i);
				String characterS = "" + character;
				if (!characterS.contains("1") && !characterS.contains("2") && !characterS.contains("3")
						&& !characterS.contains("4") && !characterS.contains("5") && !characterS.contains("6")
						&& !characterS.contains("7") && !characterS.contains("8") && !characterS.contains("9")) {
					System.out.println(
							"Erreur, le numero doit etre entre " + nbmin + " et " + nbmax + "... Recommencez !\n");
				}
			}
			if (tacheASupprimer == "") {
				fin = false;
			} else if ((!tacheASupprimer.contains("1") && !tacheASupprimer.contains("2")
					&& !tacheASupprimer.contains("3") && !tacheASupprimer.contains("4")
					&& !tacheASupprimer.contains("5") && !tacheASupprimer.contains("6")
					&& !tacheASupprimer.contains("7") && !tacheASupprimer.contains("8")
					&& !tacheASupprimer.contains("9"))) {
				System.out
						.println("Erreur, le numero doit etre entre " + nbmin + " et " + nbmax + "... Recommencez !\n");
			} else {
				int int1 = Integer.parseInt(tacheASupprimer);
				int1++;
				String int1Format = "" + int1;

				if (liste.contains(int1Format)) {
					debut1 = liste.indexOf(liste.charAt(0));
					fin1 = liste.indexOf(tacheASupprimer);
					debut2 = liste.indexOf(int1Format);
					substring1 = liste.substring(debut1, fin1);
					substring2 = liste.substring(debut2);
					int longueur = substring2.length();
					for (int i = 0; i < longueur; i++) {
						char numListe = substring2.charAt(i);
						String hello = "" + numListe;
						if (hello.contains("1") || hello.contains("2") || hello.contains("3") || hello.contains("4")
								|| hello.contains("5") || hello.contains("6") || hello.contains("7")
								|| hello.contains("8") || hello.contains("9")) {
							int numListeI = Integer.parseInt(hello);
							numListeI = numListeI - 1;
							String numListeS = "" + numListeI;
							char numListeIncr = numListeS.charAt(0);
							substring2 = substring2.replace(numListe, numListeIncr);
						}
					}

				} else {
					debut1 = liste.indexOf(liste.charAt(0));
					fin1 = liste.indexOf(tacheASupprimer);
					substring1 = liste.substring(debut1, fin1);
					substring2 = "";

				}
				liste = substring1 + substring2;
				System.out.println(liste);
			}

		}

		return liste;

	}// supprimerTache

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
		texteRevenirAuMenu();
		Clavier.lireString();
		return liste;
	} // viderListe

	public static void quitterProgramme() {
		String msgFinProg = "\n\nF I N   N O R M A L E   D U   P R O G R A M M E\n";
		System.out.println(msgFinProg);
	} // quitterProgramme

	public static void main(String[] args) {
		String listeTaches = "";

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
				listeTaches = ajouterTache(listeTaches); // retourner une nouvelle version de listeTache
				break;
			case '3':
				listeTaches = supprimerTaches(listeTaches);
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
