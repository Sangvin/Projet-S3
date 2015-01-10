package save;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * permet la saisie d'informations visant au tri des figures
 * @author Alex
 *
 */
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
	/**
	 * Permet de saisir la description de l'objet
	 */
	private JTextArea description;

	/**
	 * Constructeur du panel
	 */
	public SortInformation(){
		this.setBorder(BorderFactory.createTitledBorder("Champs facultatifs"));
		this.initComponents();
	}
	
	/**
	 * Permet d'initialiser les composants
	 */
	private void initComponents(){
		this.tag = new JTextArea(4,15);
		this.tag.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(this.tag);
		this.tag.setToolTipText("Entrez vos tag ici en les séparant par un espace");
		this.forme = new JTextField(15);
		this.forme.setToolTipText("Entrez ici la forme de l'objet (véhicule,personnage etc)");
		this.utilisation = new JTextField(15);
		this.utilisation.setToolTipText("Entrez ici une utilisation de l'objet");
		this.description = new JTextArea(6,30);
		this.description.setLineWrap(true);
		JScrollPane scrollDescription = new JScrollPane(this.description);
		this.description.setToolTipText("Entrez ici une description du fichier");

		
		GridBagLayout bagLayout = new GridBagLayout();
        this.setLayout(bagLayout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(3,2,3,2);	
        c.weightx = 1;
        c.weighty = 0;
        c.fill = GridBagConstraints.WEST;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        JLabel tagLabel = new JLabel("Tag:");
        bagLayout.setConstraints(tagLabel, c);
        this.add(tagLabel);
		c.gridx = 1;
		bagLayout.setConstraints(scrollPane, c);
		this.add(scrollPane);
		
		JLabel utilisationLabel = new JLabel("Utilisation:");
		c.gridx = 0;
		c.gridy = 1;
		bagLayout.setConstraints(utilisationLabel, c);
		this.add(utilisationLabel);
		c.gridx = 1;
		bagLayout.setConstraints(this.utilisation, c);
		this.add(this.utilisation);
		
		JLabel formeLabel = new JLabel("Forme:");
		c.gridx = 0;
		c.gridy = 2;
		bagLayout.setConstraints(formeLabel, c);
		this.add(formeLabel);
		c.gridx = 1;
		bagLayout.setConstraints(this.forme, c);
		this.add(this.forme);
		
		
		JPanel tmp = new JPanel();
		tmp.setBorder(BorderFactory.createTitledBorder("Description"));
		tmp.add(scrollDescription);
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 3;
		bagLayout.setConstraints(tmp, c);
		this.add(tmp);
	}

	/**
	 * permet de récupérer les information supplémentaire saisies
	 * @return
	 */
	public String[][] getInfo() {
		String[][] info = new String[4][];
		info[0] = null;
		if(this.tag.getText().length() != 0){
			info[0] = this.tag.getText().replaceAll("	", " ").split(" ");
		}
		info[1] = new String[1];
		if(this.utilisation.getText().replaceAll(" ", "").length() != 0)
			info[1][0] = this.utilisation.getText();
		info[2] = new String[1];
		if(this.forme.getText().replaceAll(" ", "").length() != 0)
			info[2][0] = this.forme.getText();
		info[3] = new String[1];
		if(this.description.getText().replaceAll(" ", "").length() != 0)
			info[3][0] = this.description.getText();
		return info;
	}
}
