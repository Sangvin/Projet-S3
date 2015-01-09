package graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import mvc.Model;
import mvc.ObjectController;
import objet.Objet3D;
import objet.Point;
import open.Recherche;
import save.Save;
import autre.Outils;
import colorChooser.MyColorChooser;

/**
 * Contient la bare de Menu de la frame
 * @author Vincent
 *
 */
public class MenuBar extends JMenuBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6946857843971255702L;
	/**
	 * Contient l'item pour sauvegarder une figure
	 */
	private JMenuItem itemSauver;
	/**
	 * Contient l'item pour changer la couleur d'une figure
	 */
	private JMenuItem itemCouleurFigure;
	/**
	 * Contient l'item pour fermer la fenêtre
	 */
	private JMenuItem itemFermer;
	/**
	 * Contient l'item pour modifier les données de la figure
	 */
	private JMenuItem itemModifier;
	/**
	 * Contient la fenêtre principale
	 */
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
	 * Constructeur de la bar de menu
	 * @param f
	 */
	public MenuBar(Frame f,Model model, ObjectController controller){
		this.f = f;
		this.model = model;
		this.controller = controller;
		this.initComponents();
	}

	/**
	 * Permet d'initialiser les composants
	 */
	private void initComponents(){
		// Menu Fichier
		JMenu menuFichier = new JMenu("Fichier");
		JMenuItem itemOuvrir = new JMenuItem("Ouvrir");
		itemOuvrir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Recherche(f,controller);
				if(model.getObject() != null){
					itemCouleurFigure.setEnabled(true);
					itemFermer.setEnabled(true);
					itemSauver.setEnabled(true);
					itemModifier.setEnabled(true);
				}
			}
		});
		itemSauver = new JMenuItem("Sauver");
		itemSauver.setEnabled(false);
		itemSauver.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Save(f,model,controller);
			}
		});
		this.itemModifier = new JMenuItem("Modifier données");
		this.itemModifier.setEnabled(false);
		JMenuItem itemImporter = new JMenuItem("Importer");
		itemImporter.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File fichier = new File(".");
				JFileChooser dialogue = new JFileChooser(fichier);	
				dialogue.setAcceptAllFileFilterUsed(false);
				dialogue.setFileFilter(new FileNameExtensionFilter("GNU Triangulated Surface (.gts)", "gts"));
				int status = dialogue.showOpenDialog(null);
				if(status==JFileChooser.APPROVE_OPTION) {
					fichier = dialogue.getSelectedFile();
					try {
						controller.attachObjet3D(new Objet3D(fichier.getAbsolutePath(),Outils.randomColor(),f),"","");
						double posx = f.getTablette().getSize().getWidth()/2;
						double posy = f.getTablette().getSize().getHeight()/2;
						controller.setVector(new Point(posx,posy,0));
						if(model.getObject() != null){
							itemSauver.setEnabled(true);
							itemCouleurFigure.setEnabled(true);
							itemFermer.setEnabled(true);
							itemModifier.setEnabled(true);
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(f, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		itemFermer = new JMenuItem("Fermer l'objet");
		itemFermer.setEnabled(false);
		itemFermer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.fermerObjet();
				itemModifier.setEnabled(false);
				itemFermer.setEnabled(false);
				itemSauver.setEnabled(false);
				itemCouleurFigure.setEnabled(false);
			}
		});
		JMenuItem itemQuitter = new JMenuItem("Quitter");
		itemQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		menuFichier.add(itemOuvrir);
		menuFichier.add(itemSauver);
		menuFichier.add(itemModifier);
		menuFichier.addSeparator();
		menuFichier.add(itemImporter);
		menuFichier.add(itemFermer);
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
		itemCouleurFigure = new JMenuItem("Couleur figure");
		itemCouleurFigure.setEnabled(false);
		itemCouleurFigure.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MyColorChooser(f,model,'o');
			}
		});
		JMenuItem itemCouleurBackground = new JMenuItem("Couleur background");
		itemCouleurBackground.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MyColorChooser(f,model,'b');
			}
		});
		JMenu preferenceAffichage = new JMenu("Mode d'affichage");
		ButtonGroup group = new ButtonGroup();
		JRadioButtonMenuItem normal = new JRadioButtonMenuItem("Normal");
		this.controller.setMode(0);
		normal.setSelected(true);
		normal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setMode(0);
			}
		});
		JRadioButtonMenuItem squelete = new JRadioButtonMenuItem("Squelete");
		squelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setMode(1);
			}
		});
		JRadioButtonMenuItem lumiere = new JRadioButtonMenuItem("Lumière");
		lumiere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setMode(2);
			}
		});
		group.add(normal);
		group.add(squelete);
		group.add(lumiere);
		preferenceAffichage.add(normal);
		preferenceAffichage.add(squelete);
		preferenceAffichage.add(lumiere);
		menuOptions.add(itemCouleurFigure);
		menuOptions.add(itemCouleurBackground);
		menuOptions.addSeparator();
		menuOptions.add(preferenceAffichage);
		
		//Ajout des JMenu
		this.add(menuFichier);
		this.add(menuOptions);
		this.add(menuAide);
	}
}