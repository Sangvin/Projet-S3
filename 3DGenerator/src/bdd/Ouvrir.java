package bdd;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

/**
 * Cette classe sert à ouvrir un fichier à partir du systeme de fichiers. Elle est appelle depuis
 * la fenetre principale en cliquant sur "Fichier"  ensuite "Ouvrir" . elle nous servira pour pouvoir
 * modifier les fichiers .gts et aussi utiliser le nom du fichier récupéré pour afficher le modele.
 * 
 * @author Douae
 *
 */

public class Ouvrir {

	public static void main(String[] arg) throws IOException {
		JFileChooser dialogue = new JFileChooser(new File("."));
		PrintWriter sortie;
		File fichier;	
		if(dialogue.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			fichier = dialogue.getSelectedFile();
			sortie = new PrintWriter(new FileWriter(fichier.getPath(), true));
			sortie.close();
			if (Desktop.isDesktopSupported())
			{
				Desktop desktop = Desktop.getDesktop();
				desktop.open(fichier);
			}
		}

	}
}