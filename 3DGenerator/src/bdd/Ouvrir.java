package bdd;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Cette classe sert à ouvrir un fichier à partir du systeme de fichiers. Elle est appelle depuis
 * la fenetre principale en cliquant sur "Fichier"  ensuite "Ouvrir" . elle nous servira pour pouvoir
 * modifier les fichiers .gts et aussi utiliser le nom du fichier récupéré pour afficher le modele.
 * 
 * @author Douae
 *
 */

public class Ouvrir{
	
	public static void main(String[] arg) throws IOException {
		File fichier = new File(".");
		JFileChooser dialogue = new JFileChooser(fichier);
		PrintWriter sortie;			
		int status = dialogue.showOpenDialog(null);
		if(status==JFileChooser.APPROVE_OPTION) {
			fichier = dialogue.getSelectedFile();
			System.out.println(fichier);
			sortie = new PrintWriter(new FileWriter(fichier.getPath()));
			sortie.close();
			if (Desktop.isDesktopSupported())
			{
				Desktop desktop = Desktop.getDesktop();
				desktop.open(fichier);
			}
		}

	}
}