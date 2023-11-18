package listeDeTacheTest;

import java.io.*;

/**
 * Classe contenant des methodes a utiliser dans le cadre du TP2 (INF1120).
 * (compatible avec JDK 7)
 * 
 * @author Melanie Lord
 * @version A23
 */
public class TP2Utils {

	// nom du fichier dans lequel on enregistre la liste des taches
	public static final String CHEMIN_FIC = "listeTaches.txt";

	/**
	 * Lit le fichier texte donne par CHEMIN_FIC, et retourne son contenu sous la
	 * forme d'une chaine de caracteres.
	 * 
	 * Si le fichier donne par CHEMIN_FIC n'existe pas, la methode retourne une
	 * chaine vide.
	 * 
	 * Si le contenu contient des caracteres blanc au debut ou a la fin, ceux-ci
	 * sont supprimes avec la methode trim() de la classe String.
	 * 
	 * @return le contenu du fichier texte donne par CHEMIN_FIC ou une chaine vide
	 *         si ce fichier n'existe pas.
	 */
	public static String lireFichier() {
		String texte = "";
		BufferedReader in;

		try {
			texte = "";
			in = new BufferedReader(new FileReader(CHEMIN_FIC));
			while (in.ready()) {
				texte += in.readLine() + "\n";
			}

		} catch (IOException e) {
			// ne rien faire
		}
		return texte.trim();
	}

	/**
	 * Sauvegarde le texte de la liste de taches dans le fichier donne par
	 * CHEMIN_FIC. Si texte contient des caracteres blancs au debut ou a la fin,
	 * ceux-ci sont supprimes avec la methode trim() de la classe String.
	 * 
	 * @param texte le texte a sauvegarder dans le fichier texte.
	 */
	public static void sauvegarder(String texte) {
		PrintWriter out;

		try {
			out = new PrintWriter(new FileWriter(CHEMIN_FIC));
			out.print(texte.trim());
			out.close();

		} catch (IOException e) {
			System.err.println("\n\nERREUR INATTENDUE ! Ne devrait pas se " + "produire.\n\n");
		}
	}
}