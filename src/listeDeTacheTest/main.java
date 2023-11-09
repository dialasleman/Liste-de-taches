package listeDeTacheTest;

public class main {
	private static int nbCaracteresMin = 3;
	private static int nbCaracteresMax = 80;
	private static int compteTaches = 0;
	public static int nbmax = 0;
	public static int nbmin = 1;
	private static int tacheASupprimer;
	private static String repVider;
	private static String tacheLue;

	private static String msgMenu = "----" + "\nMENU\n" + "----\n" + "1. Afficher liste\n" + "2. Ajouter tache(s)\n"
			+ "3. Supprimer taches\n" + "4. Vider liste\n" + "5. Quitter le programme\n";
	private static String msgFinProg = "\n\nF I N   N O R M A L E   D U   P R O G R A M M E\n";
	private static String listeTaches = ""; // déclarer à l'extérieur des méthodes pour conserver son état
	private static String revenirAuMenu = "\nAppuyez sur <ENTREE> pour revenir au menu... \n";
	private static String msgAccueilTache = "\n-- AJOUTER TACHE(S) --\n";
	private static String msgAjoutTache = "Tache a ajouter (<ENTREE> pour terminer): ";
	private static String msgErrTache = "Erreur, la tache doit contenir entre 3 et 80 caracteres inclusivement... Recommencez !";
	private static String msgSoll = "Entrez votre choix : ";
	private static String msgErr = "Erreur, choix du menu invalide... Recommencez!";
	private static String msgListeVide = "===============\n" + "CHOSES A FAIRE\n" + "===============\n"
			+ "AUCUN ITEM DANS LA LISTE";
	private static String msgViderListe = "\n-- VIDER LA LISTE --\n" + "\n" + "AUCUN ITEM DANS LA LISTE";
	private static String msgChosesAFaire = "\n===============\n" + "CHOSES A FAIRE\n" + "===============\n";
	private static String msgSuppTache = "\n-- SUPPRIMER TACHE(S) --\n";
	private static String msgTacheASupp = "Numero de la tache a supprimer (<ENTREE> pour terminer) :";
	private static String msgAucunItem = "AUCUN ITEM DANS LA LISTE\n";
	private static String msgVerifVider = "Voulez-vous vraiment vider la liste (O)ui ou (N)on ? \n";
	private static String msgErrVider = "Erreur, vous devez repondre par O ou N... Recommencez !\n";
	private static String msgOppAnn = "Operation annulee.\n";
	private static String msgListeVidee = "Tous les items ont ete supprimes de la liste.\n";

	// Cette méthode affiche le menu principal
	public static void afficherMenu() {
		System.out.println(msgMenu);
	}

	// Cette méthode permet d'ajouter des tâches
	public static String ajouterTache() {
		boolean finAjoutTache = false;
		System.out.println(msgAccueilTache);
		while (!(finAjoutTache)) {
			System.out.print(msgAjoutTache);
			tacheLue = Clavier.lireString();
			if (tacheLue.equals("")) { // si ce que l'utilisateur entre est nul, finir d'ajouter des tâches
				finAjoutTache = true;
			} else if (tacheLue.length() >= nbCaracteresMin && tacheLue.length() <= nbCaracteresMax && tacheLue != "") {
				compteTaches++;
				listeTaches += compteTaches + ". " + tacheLue + "\n";
			} else {
				System.out.println(msgErrTache);
			}
		} // while (finAjoutTache)
		return listeTaches;
	}

	static void afficherListe() {
		if (listeTaches.equals("")) {
			System.out.println(msgListeVide);
		} else {
			System.out.println(msgChosesAFaire);
			System.out.println(listeTaches);
		}
		System.out.println(revenirAuMenu);
		Clavier.lireString();
	} // afficherListe

	public static void supprimerTaches() {
		System.out.println(msgSuppTache + msgChosesAFaire + listeTaches);
		boolean tacheValide = false;
		while (!tacheValide) {
			nbmax += compteTaches;
			if (listeTaches == "") {
				System.out.println(msgAucunItem + revenirAuMenu);
				Clavier.lireString();
				break;
			} else {
				System.out.println(msgTacheASupp);
				tacheASupprimer = Clavier.lireInt();
				if (tacheASupprimer < nbmin || tacheASupprimer > nbmax) {
					System.out.println(
							"Erreur, le numero doit etre entre " + nbmin + " et " + nbmax + "... Recommencez !\n");
				} else {
					System.out.println("MONTRER LA LISTE SANS LE NUMERO SÉLECTIONNÉ"); // TODO
					tacheValide = true;
					System.out.println(revenirAuMenu);
					Clavier.lireString();
				}
			} // while tache valide
		} // else pas vide
	} // supprimerTache

	public static void viderListe() {
		boolean fini = false;
		while (!fini) {
			if (listeTaches == "") {
				System.out.println(msgViderListe);
				listeTaches = "";
				fini = true;
			} else {
				System.out.println(msgVerifVider);
				repVider = Clavier.lireString();
				if (repVider.equalsIgnoreCase("o")) {
					listeTaches = "";
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
		System.out.println(revenirAuMenu);
		Clavier.lireString();
	} // viderListe

	public static void quitterProgramme() {
		System.out.println(msgFinProg);
	} // quitterProgramme

	public static void main(String[] args) {
		boolean finDuProgramme = false;
		while (!finDuProgramme) {
			afficherMenu();
			char choixMenuLu;
			System.out.print(msgSoll);
			choixMenuLu = Clavier.lireCharLn();
			// Faire une action selon le choix
			switch (choixMenuLu) {
			case '1':
				afficherListe();
				break;
			case '2':
				listeTaches = ajouterTache(); // retourner une nouvelle version de listeTache après modification
				break;
			case '3':
				supprimerTaches();
				break;
			case '4':
				viderListe();
				break;
			case '5':
				quitterProgramme();
				finDuProgramme = true;
				break;
			default:
				System.out.println(msgErr);
			}// switch choixMenuLu
		} // while fin du programme
	} // main
} // ListeDeTâches
