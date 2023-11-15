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
		boolean inputValide = true;
		System.out.println(msgSuppTache + msgChosesAFaire + liste);
		//TODO: boolean pour input de l'utilisateur set à true or false, vérifier qd utilisateur entre chiffre vs lettres à plusieurs reprises

		while (!fin) {
			do {
				System.out.println(msgTacheASupp);
				tacheASupprimer = Clavier.lireString();

				for (int i = 0; i < tacheASupprimer.length(); i++) {
					char character = tacheASupprimer.charAt(i);
					if (character != '1' && character != '2' && character != '3' && character != '4' && character != '5'
							&& character != '6' && character != '7' && character != '8' && character != '9') {
						 inputValide = false;

					}

				}
				if (inputValide == false) {
					System.out.println(
							"Erreur, le numero doit etre entre " + nbmin + " et " + nbmax + "... Recommencez !");
				}
				
			} while (inputValide == false);
			
			if (tacheASupprimer == "") {
				fin = false;
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
