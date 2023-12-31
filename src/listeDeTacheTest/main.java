/**
 * Cette classe contient des methodes qui permettent a 
 * un utilisateur de creer une liste de taches. 
 * @author Diala Sleman 
 * Code permanent : SLED82550309  
 * Courriel : ja791937@ens.uqam.ca 
 * Cours : INF1120-40  
 * @version 2023-11-19
 */
package listeDeTacheTest;

public class main {
	final static String MSG_PRESENTATION = "Ce logiciel permet de maintenir \nune liste de choses a faire.\n";
	final static String MSG_ERR = "\nErreur, choix du menu invalide... Recommencez!\n";
	final static String MSG_LISTE_VIDE = "\n===============\n" + "CHOSES A FAIRE\n" + "===============\n"
			+ "\nAUCUN ITEM DANS LA LISTE";
	final static String MSG_SOLL = "Entrez votre choix : ";
	final static String MSG_RETOUR_MENU = "\nAppuyez sur <ENTREE> pour revenir au menu... ";
	final static String MSG_MENU = "----" + "\nMENU\n" + "----\n" + "1. Afficher liste\n" + "2. Ajouter tache(s)\n"
			+ "3. Supprimer taches\n" + "4. Vider liste\n" + "5. Quitter le programme\n";
	final static String MSG_AJOUT_TACHE = "Tache a ajouter (<ENTREE> pour terminer): ";
	final static String MSG_ACCUEIL_TACHE = "\n-- AJOUTER TACHE(S) --\n";
	final static String MSG_ERR_TACHE = "\nErreur, la tache doit contenir entre 3 et 80 caracteres inclusivement... Recommencez !";
	final static String MSG_SUPP_TACHE = "\n-- SUPPRIMER TACHE(S) --\n";
	final static String MSG_CHOSES_A_FAIRE = "\n===============\n" + "CHOSES A FAIRE\n" + "===============\n";
	final static String MSG_TACHE_A_SUPP = "Numero de la tache a supprimer (<ENTREE> pour terminer) : ";
	final static String MSG_VIDER_LISTE = "\n-- VIDER LA LISTE --\n" + "\n" + "AUCUN ITEM DANS LA LISTE";
	final static String MSG_VERIF_VIDER = "\nVoulez-vous vraiment vider la liste (O)ui ou (N)on ? ";
	final static String MSG_ERR_VIDER = "Erreur, vous devez repondre par O ou N... Recommencez !";
	final static String MSG_OPP_ANN = "\nOperation annulee.\n";
	final static String MSG_LISTE_VIDEE = "\nTous les items ont ete supprimes de la liste.\n";

	public static String lireFichier(String liste) {
		liste = TP2Utils.lireFichier(); // lit le fichier listeTaches.txt et retourne son contenu
		return liste;
	}

	// Cette methode ne retourne rien, mais prend la liste a sauvegarder en
	// parametre.
	public static void sauvegarder(String liste) {
		TP2Utils.sauvegarder(liste);
	}

	// Cette methode permet de compter les taches dans la liste de taches, en
	// retournant un nouveau compte a chaque modification
	public static int compterTaches(String liste) {
		int compteDesTaches = 0;
		char caractereDeListe;
		int tailleListe = liste.length();
		for (int i = 0; i < tailleListe; i++) { // Se fier sur le nombre de caracteres \n pour avoir le nombre de taches
			caractereDeListe = liste.charAt(i);
			if (caractereDeListe == '\n') {
				compteDesTaches++;
			}
		}
		return compteDesTaches;
	}

	// Cette methode permet de verifier que l'utilisateur entre un chiffre entre les
	// bornes de la premiere tache a la derniere tache en se fiant a la methode
	// compterTaches()
	public static void messageRespectBornes(String liste) {
		int numPremiereTache = 1;
		int numDerniereTache = compterTaches(liste);
		String msgBornes = "\nErreur, le numero doit etre entre " + numPremiereTache + " et " + numDerniereTache
				+ "... Recommencez !";
		System.out.println(msgBornes);
	}

	// Cette méthode affiche un message pour annoncer que la liste est vide
	public static void listeVide() {
		System.out.println(MSG_LISTE_VIDE);
		System.out.println(MSG_RETOUR_MENU);
		Clavier.lireFinLigne();
	}

	// Cette methode affiche le menu principal
	public static String afficherMenu() {
		boolean choixMenuValide = false;
		char caractereInputLu;
		String choixMenuLu = "";
		// Verifier que l'utilisateur entre un chiffre entre 1 et 5 sans texte
		// supplementaire
		do {
			choixMenuValide = true;
			System.out.println(MSG_MENU);
			System.out.print(MSG_SOLL);
			choixMenuLu = Clavier.lireString();
			// Verifier chaque caractère du input de l'utilisateur en incrementant i pour
			// representer chaque caractere du input
			for (int i = 0; i < choixMenuLu.length(); i++) {
				caractereInputLu = choixMenuLu.charAt(i);
				if (caractereInputLu != '1' && caractereInputLu != '2' && caractereInputLu != '3'
						&& caractereInputLu != '4' && caractereInputLu != '5') {
					choixMenuValide = false;
				}
			}
			if (!choixMenuValide) {
				System.out.println(MSG_ERR);
			}
		} while (!choixMenuValide);
		return choixMenuLu;
	}

	// Cette methode permet d'ajouter des taches dans la liste en retournant la
	// liste modifiee
	public static String ajouterTache(String liste) {
		String tacheLue;
		int nbCaracteresMin = 3;
		int nbCaracteresMax = 80;
		int compte = compterTaches(liste);
		boolean finAjoutTache = false;

		System.out.println(MSG_ACCUEIL_TACHE);
		while (!(finAjoutTache)) {
			System.out.print(MSG_AJOUT_TACHE);
			tacheLue = Clavier.lireString();
			if (tacheLue.equals("")) { // Si ce que l'utilisateur entre est nul, finir d'ajouter des taches
				System.out.print("\n");
				finAjoutTache = true;
			} else if (tacheLue.length() >= nbCaracteresMin && tacheLue.length() <= nbCaracteresMax && tacheLue != "") {
				compte++;
				liste += compte + ". " + tacheLue + "\n"; // Liste modifiee formee des numeros de taches et du input
															// d'utilisateur
			} else {
				System.out.println(MSG_ERR_TACHE);
			}
		}
		return liste;
	}

	static void afficherListe(String liste) { // Cette methode permet d'afficher la liste de taches
		if (liste.equals("")) {
			System.out.println(MSG_LISTE_VIDE);
		} else {
			System.out.println(MSG_CHOSES_A_FAIRE);
			System.out.print(liste);
		}
		System.out.println(MSG_RETOUR_MENU);
		Clavier.lireFinLigne();
	}

	public static String supprimerTaches(String liste) { // Cette methode permet de supprimer des taches
		int numPremiereTache = 1;
		int numDerniereTache = compterTaches(liste);
		int debutSubst1;
		int finSubst1;
		int debutSubst2;
		int tacheForm;
		int numTacheInt;
		String tacheASupprimer;
		String substring1;
		String substring2;
		String tacheSuivante;
		String numTacheString;
		String numListeFinal;
		char numTacheChar;
		char numListeDecr;
		char numTacheASupp;
		boolean fin = false;
		boolean inputValide = true;
		boolean listeVide = false;
		while (!listeVide) {
			if (liste == "") {
				System.out.print(MSG_SUPP_TACHE);
				listeVide();
				listeVide = true;
			} else {
				System.out.println(MSG_SUPP_TACHE + MSG_CHOSES_A_FAIRE + liste + "\n");
				while (!fin) {
					do {
						inputValide = true;
						System.out.print(MSG_TACHE_A_SUPP);
						tacheASupprimer = Clavier.lireString();
						for (int i = 0; i < tacheASupprimer.length(); i++) {// Verifier chaque caractere du input de
																			// l'utilisateur selon la place de (int i)
																			// dans l'input
							numTacheASupp = tacheASupprimer.charAt(i);
							if (numTacheASupp != '1' && numTacheASupp != '2' && numTacheASupp != '3'
									&& numTacheASupp != '4' && numTacheASupp != '5' && numTacheASupp != '6'
									&& numTacheASupp != '7' && numTacheASupp != '8' && numTacheASupp != '9') {
								inputValide = false;
							}
						}
						if (!inputValide) {
							messageRespectBornes(liste);
						}
					} while (!inputValide);
					if (tacheASupprimer == "") {
						System.out.print("\n");
						fin = true;
						listeVide = true;
					} else if ((tacheForm = Integer.parseInt(tacheASupprimer)) < numPremiereTache
							|| (tacheForm = Integer.parseInt(tacheASupprimer)) > numDerniereTache) {
						messageRespectBornes(liste);
					} else {
						// Si l'utilisateur supprime la premier tache de la liste ou une tache du
						// milieu, diviser la liste en deux parties: avant et apres la tache a supprimer
						tacheForm++; // Incrementer la tache a supprimer pour commencer la deuxieme partie de la
										// liste a la tache suivante
						tacheSuivante = "" + tacheForm;
						if (liste.contains(tacheSuivante)) {
							// Premiere partie de la liste: du debut jusqu'a la tache a supprimer
							debutSubst1 = liste.indexOf(liste.charAt(0));
							finSubst1 = liste.indexOf(tacheASupprimer);
							substring1 = liste.substring(debutSubst1, finSubst1);
							// Deuxieme partie de la liste: de la tache suivante a la fin
							debutSubst2 = liste.indexOf(tacheSuivante);
							substring2 = liste.substring(debutSubst2);
							// Trouver tous les numéros dans la liste en incrementant i pour verifier chaque
							// caractere de la deuxieme partie de la liste
							for (int i = 0; i < substring2.length(); i++) {
								numTacheChar = substring2.charAt(i);
								numTacheString = "" + numTacheChar;
								if (numTacheString.contains("1") || numTacheString.contains("2")
										|| numTacheString.contains("3") || numTacheString.contains("4")
										|| numTacheString.contains("5") || numTacheString.contains("6")
										|| numTacheString.contains("7") || numTacheString.contains("8")
										|| numTacheString.contains("9")) {
									// Si la liste contient un numero, mettre ce numero en int, puis l'ajuster selon
									// la numerotation de la liste en le decrementant
									numTacheInt = Integer.parseInt(numTacheString);
									numTacheInt = numTacheInt - 1;
									numListeFinal = "" + numTacheInt;
									numListeDecr = numListeFinal.charAt(0);
									// Remplacer la numerotation par une numerotation decrementee pour la deuxieme
									// partie de la liste
									substring2 = substring2.replace(numTacheChar, numListeDecr);
								}
							}
							numDerniereTache = numDerniereTache - 1; // Borne diminue car une tache est supprimee
						} else {
							// Si l'utilisateur supprime la derniere tache de la liste
							debutSubst1 = liste.indexOf(liste.charAt(0));
							finSubst1 = liste.indexOf(tacheASupprimer);
							substring1 = liste.substring(debutSubst1, finSubst1);
							substring2 = "";
							numDerniereTache = numDerniereTache - 1;
						}
						liste = substring1 + substring2;
						if (liste == "") {
							listeVide();
							fin = true;
							listeVide = true;
						} else {
							System.out.println(MSG_CHOSES_A_FAIRE + liste);
						}
					}
				}
			}
		}
		return liste;
	}

	// Cette methode permet de vider la liste de tache
	public static String viderListe(String liste) {
		String repViderListe;
		boolean fini = false;

		while (!fini) {
			if (liste == "") {
				System.out.println(MSG_VIDER_LISTE);
				fini = true;
			} else {
				System.out.print(MSG_VERIF_VIDER);
				repViderListe = Clavier.lireString();
				if (repViderListe.equalsIgnoreCase("o")) {
					liste = "";
					System.out.print(MSG_LISTE_VIDEE);
					fini = true;
				} else if (repViderListe.equalsIgnoreCase("n")) {
					System.out.print(MSG_OPP_ANN);
					fini = true;
				} else {
					System.out.println(MSG_ERR_VIDER);
				}
			}
		}
		System.out.println(MSG_RETOUR_MENU);
		Clavier.lireFinLigne();
		return liste;
	}

	// Cette methode permet de quitter le programme
	public static void quitterProgramme(String liste) {
		sauvegarder(liste);
		String msgFinProg = "\n\nF I N   N O R M A L E   D U   P R O G R A M M E\n";
		System.out.println(msgFinProg);
	}

	// main
	public static void main(String[] args) {
		String listeTaches = "";
		String choixMenuLu = "";
		System.out.println(MSG_PRESENTATION);
		boolean finDuProgramme = false;
		while (!finDuProgramme) {
			lireFichier(listeTaches);
			choixMenuLu = afficherMenu();
			// Faire une action selon le choix de l'utilisateur
			switch (choixMenuLu) {
			case "1":
				afficherListe(listeTaches);
				break;
			case "2":
				listeTaches = ajouterTache(listeTaches); // retourner une nouvelle version de listeTaches
				break;
			case "3":
				listeTaches = supprimerTaches(listeTaches);
				break;
			case "4":
				listeTaches = viderListe(listeTaches);
				break;

			case "5":
				quitterProgramme(listeTaches);
				finDuProgramme = true;
				break;
			default:
				System.out.println(MSG_ERR);
			}
		}
	}
}