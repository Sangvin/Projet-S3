package save;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * panel permettant la saisie d'informations générales d'un objet3d
 * @author Alex
 *
 */
public class GeneralInformation extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * contient l'url de l'objet 3d à enregistrer
	 */
	private String path;
	/**
	 * affiche le path
	 */
	private JLabel chemin;
	/**
	 * contient la date de l'ajout de la figure
	 */
	private JLabel date;
	/**
	 * Contiendra le nom rentré par l'utilisateur
	 */
	private JTextField nom;
	/**
	 * Contiendra le nom de l'auteur
	 */
	private JTextField auteur;

	public GeneralInformation(String path){
		this.path = path;
		this.setBorder(BorderFactory.createTitledBorder("Champs Obligatoires"));
		this.initComponents();
	}

	private void initComponents(){
		this.chemin = new JLabel(this.path);
		JScrollPane scroll = new JScrollPane(this.chemin);
		scroll.setMaximumSize(new Dimension(20,20));
		this.nom = new JTextField();
		this.nom.setToolTipText("Entrez le nom qui permet d'identifier l'objet");
		this.auteur = new JTextField();
		this.auteur.setToolTipText("Entrer un auteur pour l'objet");
		Calendar c = Calendar.getInstance();
		this.date = new JLabel(c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR));
		GridLayout g = new GridLayout(4,2);
		this.setLayout(g);
		g.setHgap(10);
		g.setVgap(5);
		this.add(new JLabel("URL:"));
		this.add(scroll);
		this.add(new JLabel("Nom:"));
		this.add(this.nom);
		this.add(new JLabel("Auteur:"));
		this.add(this.auteur);
		this.add(new JLabel("Date:"));
		this.add(this.date);
	}
}
