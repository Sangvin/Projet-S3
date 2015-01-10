package autre;

import graphic.Frame;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JTable;

public class Raccourcis extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Raccourcis(Frame parent){
		super(parent,"Liste des Raccourcis", false);
	    Object[][] data = {
				{"Z","Rotation sur l'axe X"},
				{"S","Rotation inverse sur l'axe X"},
				{"D","Rotation sur l'axe Y"},
				{"Q","Rotation inverse sur l'axe Y"},
				{"E","Rotation sur l'axe Z"},
				{"A","Rotation inverse sur l'axe Z"},
				{"",""},
				{"I","Déplacement vers le haut"},
				{"K","Déplacement vers le bas"},
				{"J","Déplacement vers la gauche"},
				{"L","Déplacement vers la droite"},
				{"",""},
				{"V","Dézoomer"},
				{"B","Zoomer"},
				{"",""},
				{"Souris click gauche","Rotation de la figure sur les axes X et Y"},
				{"Souris click droit","Déplacement de la figure au point souhaité"},
	    };

	    String  title[] = {"Raccourci","Effet"};
	    JTable tableau = new JTable(data, title);
	    tableau.setEnabled(false);
	    tableau.getColumnModel().getColumn(0).setPreferredWidth(120);
	    tableau.getColumnModel().getColumn(1).setPreferredWidth(350);
		this.getContentPane().add(tableau.getTableHeader(),BorderLayout.NORTH);
		this.getContentPane().add(tableau,BorderLayout.CENTER);
	    this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()-this.getWidth()-10);
		this.setLocation(x,10);
		this.setVisible(true);
	}
}
