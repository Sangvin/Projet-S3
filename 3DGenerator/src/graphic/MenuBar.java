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

import mvc.Model;
import mvc.ObjectController;
import objet.Objet3D;
import objet.Point;
import open.Recherche;
import colorChooser.MyColorChooser;

public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6946857843971255702L;
	private Frame f;
	/**
	 * Contient le modèle
	 */
	private Model model;
	/**
	 * Contient le controller
	 */
	private ObjectController controller;
	
	/**
	 * @param f
	 */
	public MenuBar(Frame f,Model model, ObjectController controller){
		this.f = f;
		this.model = model;
		this.controller = controller;
		this.initComponents();
	}

	private void initComponents(){
		// Menu Fichier
		JMenu menuFichier = new JMenu("Fichier");
		JMenuItem itemOuvrir = new JMenuItem("Ouvrir");
		itemOuvrir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Recherche(f);
			}
		});
		JMenuItem itemSauver = new JMenuItem("Sauver");
		itemSauver.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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
						controller.attachObjet3D(new Objet3D(fichier.getAbsolutePath(),Color.RED));
						double posx = f.getTablette().getSize().getWidth()/2;
						double posy = f.getTablette().getSize().getHeight()/2;
						controller.setVector(new Point(posx,posy,0));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		JMenuItem itemQuitter = new JMenuItem("Quitter");
		menuFichier.add(itemOuvrir);
		menuFichier.add(itemSauver);
		menuFichier.addSeparator();
		menuFichier.add(itemImporter);
		menuFichier.addSeparator();
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
				new MyColorChooser(f,model);
			}
		});
		JMenuItem itemCouleurBackground = new JMenuItem("Couleur background");
		itemCouleurBackground.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MyColorChooser(f,model);
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