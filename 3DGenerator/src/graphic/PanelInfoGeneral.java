package graphic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mvc.Model;

/**
 * Permet d'afficher les info générales du fichier
 * @author Vincent
 *
 */
public class PanelInfoGeneral extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6457681512937973805L;
	/**
	 * Affiche le nom de l'objet
	 */
	JTextField textNom;
	/**
	 * Affiche le nom de l'auteur
	 */
	JTextField textAuteur;
	/**
	 * Affiche le nombre de points
	 */
	JTextField textNbPoints;
	/**
	 * Affiche le nombre de segments
	 */
	JTextField textNbSeg;
	/**
	 * Affiche le nombre de faces
	 */
	JTextField textNbFaces;
	/**
	 * Contient le modèle principal
	 */
	private Model model;

	/**
	 * Constructeur du panel
	 * @param model
	 */
	public PanelInfoGeneral(Model model) {
		this.model = model;
		this.initComponents();
		this.model.addObserver(this);
	}

	/**
	 * Permet d'initialiser les composants
	 */
	private void initComponents(){
		this.textNom = new JTextField(15);
		this.textNom.setFocusable(false);
		this.textAuteur = new JTextField(15);
		this.textAuteur.setFocusable(false);
		this.textNbPoints = new JTextField("0",15);
		this.textNbPoints.setFocusable(false);
		this.textNbSeg = new JTextField("0",15);
		this.textNbSeg.setFocusable(false);
		this.textNbFaces = new JTextField("0",15);
		this.textNbFaces.setFocusable(false);

		GridBagLayout bagLayout = new GridBagLayout();
		this.setLayout(bagLayout);
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(2,2,2,2);
		c.weightx = 1;
		c.weighty = 0;
		c.fill = GridBagConstraints.NORTHEAST;
		c.gridwidth = 1;
		c.gridheight = 1;
		
		JLabel nom = new JLabel("Nom");
		c.gridx = 0;
		c.gridy = 0;
		bagLayout.setConstraints(nom, c);
		this.add(nom);
		c.gridx = 1;
		bagLayout.setConstraints(textNom, c);
		this.add(textNom);
		
		JLabel auteur = new JLabel("Auteur");
		c.gridy = 1;
		c.gridx = 0;
		bagLayout.setConstraints(auteur, c);
		this.add(auteur);
		c.gridx = 1;
		bagLayout.setConstraints(textAuteur, c);
		this.add(textAuteur);
		
		JLabel nbPoints = new JLabel("Nombre de points");
		c.gridx = 0;
		c.gridy = 2;
		bagLayout.setConstraints(nbPoints, c);
		this.add(nbPoints);
		c.gridx = 1;
		bagLayout.setConstraints(textNbPoints, c);
		this.add(textNbPoints);
		
		JLabel nbSeg = new JLabel("Nombre de segments");
		c.gridx = 0;
		c.gridy = 3;
		bagLayout.setConstraints(nbSeg, c);
		this.add(nbSeg);
		c.gridx = 1;
		bagLayout.setConstraints(textNbSeg, c);
		this.add(textNbSeg);
		
		JLabel nbFaces = new JLabel("Nomvre de faces");
		c.gridx = 0;
		c.gridy = 4;
		bagLayout.setConstraints(nbFaces, c);
		this.add(nbFaces);
		c.gridx = 1;
		bagLayout.setConstraints(textNbFaces, c);
		this.add(textNbFaces);

	}
	
	@Override
	public void update(Observable arg0, Object arg) {
		if((Integer)arg != 1){
			this.textAuteur.setText(this.model.getAuteur());
			this.textNom.setText(this.model.getName());
			this.textNbFaces.setText(this.model.getNBFace() + "");
			this.textNbPoints.setText(this.model.getNBPoint() + "");
			this.textNbSeg.setText(this.model.getNBSeg() + "");
		}
	}
}
