package save;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SortInformation extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Permet la saisie de tag
	 */
	private JTextArea tag;
	/**
	 * Permet de saisir une utilisation
	 */
	private JTextField utilisation;
	/**
	 * Permet de saisir une forme
	 */
	private JTextField forme;

	public SortInformation(){
		this.setBorder(BorderFactory.createTitledBorder("Champs facultatifs"));
		this.initComponents();
	}
	
	private void initComponents(){
		this.tag = new JTextArea(2,15);
		this.tag.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(this.tag);
		this.tag.setToolTipText("Entrez vos tag ici en les séparant par un espace");
		this.forme = new JTextField(15);
		this.forme.setToolTipText("Entrez ici la forme de l'objet (véhicule,personnage etc)");
		this.utilisation = new JTextField(15);
		this.utilisation.setToolTipText("Entrez ici une utilisation de l'objet");

		GridLayout g = new GridLayout(3,2);
		this.setLayout(g);
		g.setHgap(10);
		g.setVgap(5);
		this.add(new JLabel("Tag:"));
		this.add(scrollPane);
		this.add(new JLabel("Utilisation:"));
		this.add(this.utilisation);
		this.add(new JLabel("Forme:"));
		this.add(this.forme);
	}
}
