/**
 * Cette classe represente...   
 * @author Diala Sleman 
 * Code permanent : SLED82550309  
 * Courriel : ja791937@ens.uqam.ca 
 * Cours : INF1120-40  
 * @version 2023-11-19
 */
package listeDeTacheTest;

public class main {
	final static String MSG_PRESENTATION = "Ce logiciel permet de maintenir \nune liste de choses a faire.\n";
	final static String MSG_ERR = "\nErreur, choix du menu invalide... Recommencez!";
	final static String MSG_LISTE_VIDE = "\n===============\n" + "CHOSES A FAIRE\n" + "===============\n"
			+ "\nAUCUN ITEM DANS LA LISTE";
	final static String MSG_SOLL = "Entrez votre choix : ";
	final static String MSG_RETOUR_MENU = "\nAppuyez sur <ENTREE> pour revenir au menu... ";
	final static String MSG_MENU = "----" + "\nMENU\n" + "----\n" + "1. Afficher liste\n" + "2. Ajouter tache(s)\n"
			+ "3. Supprimer taches\n" + "4. Vider liste\n" + "5. Quitter le programme\n";
	final static String MSG_AJOUT_TACHE = "Tache a ajouter (<ENTREE> pour terminer): ";
	final static String MSG_ACCUEIL_TACHE = "\n-- AJOUTER TACHE(S) --\n";
	final static String MSG_ERR_TACHE = "Erreur, la tache doit contenir entre 3 et 80 caracteres inclusivement... Recommencez !";
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

	// Cette méthode ne retourne rien, mais prend la liste à sauvegarder en
	// paramètre.
	public static void sauvegarder(String liste) {
		TP2Utils.sauvegarder(liste);
	}

	// Cette méthode permet de compter les tâches dans la liste de tâches, en
	// retournant un nouveau compte à chaque modification
	public static int compterTaches(String liste) {
		int compte = 0;
		int tailleListe = liste.length();
		for (int i = 0; i < tailleListe; i++) { // Se fier sur le nombre de caractères \n pour avoir le nombre de tâches
			char c = liste.charAt(i);
			if (c == '\n') {
				compte++;
			}
		}
		return compte;
	}

	// Cette méthode permet de vérifier que l'utilisateur entre un chiffre entre les
	// bornes de la première tâche à la dernière tâche en se fiant à la méthode
	// compterTaches()
	public static void messageRespectBornes(String liste) {
		int nbrMin = 1;
		int nbrMax = compterTaches(liste);
		String msgBornes = "\nErreur, le numero doit etre entre " + nbrMin + " et " + nbrMax + "... Recommencez !";
		System.out.println(msgBornes);
	}

	// Cette méthode affiche le menu principal
	public static String afficherMenu() {
		boolean choixMenuValide = false;
		String choixMenuLu = "";
		// Vérifier que l'utilisateur entre un chiffre entre 1 et 5 sans texte
		// supplémentaire
		do {
			choixMenuValide = true;
			System.out.println(MSG_MENU);
			System.out.print(MSG_SOLL);
			choixMenuLu = Clavier.lireString();
			// Vérifier chaque caractère du input de l'utilisateur en incrémentant i pour
			// représenter chaque caractère du input
			for (int i = 0; i < choixMenuLu.length(); i++) {
				char character = choixMenuLu.charAt(i);
				if (character != '1' && character != '2' && character != '3' && character != '4' && character != '5') {
					choixMenuValide = false;
				}
			}
			if (!choixMenuValide) {
				System.out.println(MSG_ERR);
			}
		} while (!choixMenuValide);
		return choixMenuLu;
	}

	// Cette méthode permet d'ajouter des tâches dans la liste en retournant la
	// liste modifiée
	public static String ajouterTache(String liste) {
		String tacheLue;
		int nbCaracteresMin = 3;
		int nbCaracteresMax = 80;
		int compte = compterTaches(liste);
		boolean finAjoutTache = false;

		System.out.println(MSG_ACCUEIL_TACHE);
		while (!(finAjoutTache)) {
			System.out.print(MSG_ACCUEIL_TACHE);
			tacheLue = Clavier.lireString();
			if (tacheLue.equals("")) { // Si ce que l'utilisateur entre est nul, finir d'ajouter des tâches
				finAjoutTache = true;
			} else if (tacheLue.length() >= nbCaracteresMin && tacheLue.length() <= nbCaracteresMax && tacheLue != "") {
				compte++;
				liste += compte + ". " + tacheLue + "\n"; // Liste modifiée formée des numéros de tâches et du input
															// d'utilisateur
			} else {
				System.out.println(MSG_ERR_TACHE);
			}
		}
		return liste;
	}

	static void afficherListe(String liste) { // Cette méthode permet d'afficher la liste de tâche
		if (liste.equals("")) {
			System.out.println(MSG_LISTE_VIDE);
		} else {
			System.out.println(MSG_CHOSES_A_FAIRE);
			System.out.println(liste);
		}
		System.out.println(MSG_RETOUR_MENU);
		Clavier.lireFinLigne();
	}

	public static String supprimerTaches(String liste) { // Cette méthode permet de supprimer des tâches
		if (liste == "") { // Liste vide
			System.out.print(MSG_SUPP_TACHE);
			System.out.println(MSG_LISTE_VIDE);
			System.out.println(MSG_RETOUR_MENU);
			Clavier.lireFinLigne();
			return liste;
		}
		int nbrMax = compterTaches(liste);
		int nbrMin = 1;
		int debut1;
		int fin1;
		int debut2;
		int tacheForm;
		int numListeInt;
		String tacheASupprimer;
		String substring1;
		String substring2;
		String tacheSuivante;
		String numListeString;
		String numListeFinal;
		char numListe;
		char numListeDecr;
		boolean fin = false;
		boolean inputValide = true;
		boolean listeVide = false;
		while (!listeVide) {
			if (liste == "") {
				System.out.print(MSG_SUPP_TACHE);
				System.out.println(MSG_LISTE_VIDE);
				System.out.println(MSG_RETOUR_MENU);
				Clavier.lireFinLigne();
				listeVide = true;
			} else {
				System.out.println(MSG_SUPP_TACHE + MSG_CHOSES_A_FAIRE + liste);
				while (!fin) {
					do {
						inputValide = true;
						System.out.print(MSG_TACHE_A_SUPP);
						tacheASupprimer = Clavier.lireString();
						for (int i = 0; i < tacheASupprimer.length(); i++) {// Vérifier chaque caractère du input de
																			// l'utilisateur selon la place de (int i)
																			// dans l'input
							char character = tacheASupprimer.charAt(i);
							if (character != '1' && character != '2' && character != '3' && character != '4'
									&& character != '5' && character != '6' && character != '7' && character != '8'
									&& character != '9') {
								inputValide = false;
							}
						}
						if (!inputValide) {
							messageRespectBornes(liste);
						}
					} while (!inputValide);

					if (tacheASupprimer == "") {
						fin = true;
						listeVide = true;
					} else if ((tacheForm = Integer.parseInt(tacheASupprimer)) < nbrMin
							|| (tacheForm = Integer.parseInt(tacheASupprimer)) > nbrMax) {
						messageRespectBornes(liste);
					} else {
						// Si l'utilisateur supprime la premier tache de la liste ou une tache du
						// milieu, diviser la liste en deux parties: avant et après la tache à supprimer
						tacheForm++; // Incrémenter la tâche à supprimer pour commencer la deuxième partie de la
										// liste à la tache suivante
						tacheSuivante = "" + tacheForm;
						if (liste.contains(tacheSuivante)) {
							// Première partie de la liste: du début jusqu'à la tâche à supprimer
							debut1 = liste.indexOf(liste.charAt(0));
							fin1 = liste.indexOf(tacheASupprimer);
							substring1 = liste.substring(debut1, fin1);
							// Deuxième partie de la liste: de la tache suivante à la fin
							debut2 = liste.indexOf(tacheSuivante);
							substring2 = liste.substring(debut2);
							// Trouver tous les numéros dans la liste en incrémentant i pour vérifier chaque
							// caractère de la deuxieme partie de la liste
							for (int i = 0; i < substring2.length(); i++) {
								numListe = substring2.charAt(i);
								numListeString = "" + numListe;
								if (numListeString.contains("1") || numListeString.contains("2")
										|| numListeString.contains("3") || numListeString.contains("4")
										|| numListeString.contains("5") || numListeString.contains("6")
										|| numListeString.contains("7") || numListeString.contains("8")
										|| numListeString.contains("9")) {
									// Si la liste contient un numéro, mettre ce numéro en int, puis l'ajuster selon
									// la numérotation de la liste en le décrémentant
									numListeInt = Integer.parseInt(numListeString);
									numListeInt = numListeInt - 1;
									numListeFinal = "" + numListeInt;
									numListeDecr = numListeFinal.charAt(0);
									// Remplacer la numérotation par une numérotation décrémentée pour la deuxieme
									// partie de la liste
									substring2 = substring2.replace(numListe, numListeDecr);
								}
							}
							nbrMax = nbrMax - 1; // Borne diminue car une tache est supprimée
						} // FIN DU IF
						else {
							// Si l'utilisateur supprime la derniere tache de la liste
							debut1 = liste.indexOf(liste.charAt(0));
							fin1 = liste.indexOf(tacheASupprimer);
							substring1 = liste.substring(debut1, fin1);
							substring2 = "";
							nbrMax = nbrMax - 1;
						}
						liste = substring1 + substring2;
						if (liste == "") {
							System.out.println(MSG_LISTE_VIDE);
							System.out.println(MSG_RETOUR_MENU);
							Clavier.lireFinLigne();
							fin = true;
							listeVide = true;
						} else {
							System.out.println("\n" + "===============\n" + "CHOSES A FAIRE\n" + "===============\n");
							System.out.println(liste);
						}
					}
				}
			}
		}
		return liste;
	}

	// Cette méthode permet de vider la liste de tâche
	public static String viderListe(String liste) {
		String repVider;
		boolean fini = false;

		while (!fini) {
			if (liste == "") {
				System.out.println(MSG_VIDER_LISTE);
				fini = true;
			} else {
				System.out.print(MSG_VERIF_VIDER);
				repVider = Clavier.lireString();
				if (repVider.equalsIgnoreCase("o")) {
					liste = "";
					System.out.print(MSG_LISTE_VIDEE);
					fini = true;
				} else if (repVider.equalsIgnoreCase("n")) {
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

	// Cette méthode permet de quitter le programme
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