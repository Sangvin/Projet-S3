package graphic;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6946857843971255702L;
	private PanelObjet tablette;
	
	public MenuBar(PanelObjet tablette){
		this.tablette = tablette;
		
		// Menu Fichier
		JMenu menuFichier = new JMenu("Fichier");
		JMenuItem itemOuvrir = new JMenuItem("Ouvrir");
		JMenuItem itemSauver = new JMenuItem("Sauver");
		JMenuItem itemQuitter = new JMenuItem("Quitter");
		menuFichier.add(itemOuvrir);
		menuFichier.add(itemSauver);
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
		JMenuItem itemCouleurBackground = new JMenuItem("Couleur background");
		menuOptions.add(itemCouleurFigure);
		menuOptions.add(itemCouleurBackground);
		
		//Ajout des JMenu
		this.add(menuFichier);
		this.add(menuOptions);
		this.add(menuAide);
	}

}
