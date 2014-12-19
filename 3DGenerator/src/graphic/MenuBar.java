package graphic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import bdd.Recherche;
import objet.Objet3D;
import colorChooser.MyColorChooser;

public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6946857843971255702L;
	private Frame f;
	
	/**
	 * @param f
	 */
	public MenuBar(Frame f){
		this.f = f;
		this.initComponents();
	}

	private void initComponents(){
		// Menu Fichier
		JMenu menuFichier = new JMenu("Fichier");
		JMenuItem itemOuvrir = new JMenuItem("Ouvrir");
		itemOuvrir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Recherche();
			}
		});
		JMenuItem itemSauver = new JMenuItem("Sauver");
		JMenuItem itemImporter = new JMenuItem("Importer");
		itemImporter.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File fichier = new File(".");
				JFileChooser dialogue = new JFileChooser(fichier);	
				int status = dialogue.showOpenDialog(null);
				if(status==JFileChooser.APPROVE_OPTION) {
					fichier = dialogue.getSelectedFile();
					try {
						f.attachObjet3D(new Objet3D(fichier.getAbsolutePath(),Color.RED));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		JMenuItem itemQuitter = new JMenuItem("Quitter");
		menuFichier.add(itemOuvrir);
		menuFichier.add(itemSauver);
		menuFichier.add(itemImporter);
		menuFichier.add(itemQuitter);

		// Menu Aide
		JMenu menuAide = new JMenu("Aide");
		JMenuItem itemAPropos = new JMenuItem("A Propos");
		JMenuItem itemFAQ = new JMenuItem("F.A.Q.");
		menuAide.add(itemAPropos);
		menuAide.add(itemFAQ);
		
		// Menu Options
		JMenu menuOptions = new JMenu("Options");
		JMenuItem itemCouleurFigure = new JMenuItem("Couleur figure");
		itemCouleurFigure.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				f.setEnabled(false);
				new MyColorChooser(f, f.getTablette().getObject());
			}
		});
		JMenuItem itemCouleurBackground = new JMenuItem("Couleur background");
		itemCouleurBackground.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				f.setEnabled(true);
				new MyColorChooser(f);
			}
		});
		menuOptions.add(itemCouleurFigure);
		menuOptions.add(itemCouleurBackground);
		
		//Ajout des JMenu
		this.add(menuFichier);
		this.add(menuOptions);
		this.add(menuAide);
	}
}