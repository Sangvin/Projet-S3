package save;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

	/**
	 * Constructeur du panel
	 * @param path
	 */
	public GeneralInformation(String path){
		this.path = path;
		this.setBorder(BorderFactory.createTitledBorder("Champs Obligatoires"));
		this.initComponents();
	}

	/**
	 * Permet d'initialiser les composants
	 */
	private void initComponents(){
		this.chemin = new JLabel(this.path);
		JScrollPane scroll = new JScrollPane(this.chemin);
		this.nom = new JTextField(15);
		this.nom.setToolTipText("Entrez le nom qui permet d'identifier l'objet");
		this.auteur = new JTextField(15);
		this.auteur.setToolTipText("Entrer un auteur pour l'objet");
		Calendar cal = Calendar.getInstance();
		this.date = new JLabel(cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR));

		int sizex = (int) this.auteur.getPreferredSize().getWidth();
		int sizey = (int) (this.auteur.getPreferredSize().getHeight()+JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(sizex,sizey));
		
		GridBagLayout bagLayout = new GridBagLayout();
        this.setLayout(bagLayout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(2,2,2,2);
        c.weightx = 1;
        c.weighty = 0;
        c.fill = GridBagConstraints.WEST;
        c.gridwidth = 1;
        c.gridheight = 1;
        
        JLabel urlTag = new JLabel("URL:");
        c.gridx = 0;
        c.gridy = 0;
        bagLayout.setConstraints(urlTag, c);
        this.add(urlTag);
        c.gridx = 1;
        bagLayout.setConstraints(scroll, c);
        this.add(scroll);
        
        JLabel nomLabel = new JLabel("Nom:");
        c.gridx = 0;
        c.gridy = 1;
        bagLayout.setConstraints(nomLabel, c);
        this.add(nomLabel);
        c.gridx = 1;
        bagLayout.setConstraints(this.nom, c);
        this.add(this.nom);
        
        JLabel auteurLabel = new JLabel("Auteur:");
        c.gridx = 0;
        c.gridy = 2;
        bagLayout.setConstraints(auteurLabel, c);
        this.add(auteurLabel);
        c.gridx = 1;
        bagLayout.setConstraints(this.auteur, c);
        this.add(this.auteur);
        
        JLabel dateLabel = new JLabel("Date:");
        c.gridx = 0;
        c.gridy = 3;
        bagLayout.setConstraints(dateLabel, c);
        this.add(dateLabel);
        c.gridx = 1;
        bagLayout.setConstraints(date, c);
        this.add(date);
	}

	/**
	 * permet de récupérer les informations générales renvois null si une info est manquante
	 * @return
	 */
	public String[] getInfo() {
		if(this.auteur.getText().replaceAll(" ", "").length() == 0)
			return null;
		if(this.nom.getText().replaceAll(" ","").length() == 0)
			return null;
		String[] info = new String[4];
		info[0] = this.nom.getText();
		info[1] = this.path;
		info[2] = this.date.getText();
		info[3] = this.auteur.getText();
		return info;
	}
}
