package save;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	public GeneralInformation(String path){
		this.path = path;
		this.setBorder(BorderFactory.createTitledBorder("Champs Obligatoires"));
		this.initComponents();
	}
	
	private void initComponents(){
		GridLayout g = new GridLayout(4,2);
		this.setLayout(g);
		g.setHgap(10);
		g.setVgap(5);
		this.add(new JLabel("URL:"));
		this.add(new JLabel(this.path));
		this.add(new JLabel("Nom:"));
		this.add(new JTextField());
		this.add(new JLabel("Auteur:"));
		this.add(new JTextField());
		this.add(new JLabel("Date:"));
		this.add(new JTextField());
	}
}
