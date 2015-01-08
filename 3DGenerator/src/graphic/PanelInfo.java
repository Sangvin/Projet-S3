package graphic;

import java.awt.GridLayout;
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
public class PanelInfo extends JPanel implements Observer {
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
	public PanelInfo(Model model) {
		this.model = model;
		this.initComponents();
		this.model.addObserver(this);
	}

	/**
	 * Permet d'initialiser les composants
	 */
	private void initComponents(){
		this.setLayout(new GridLayout(5, 2, 2, 2));

		this.textNom = new JTextField();
		this.textAuteur = new JTextField();
		this.textNbPoints = new JTextField("0");
		this.textNbSeg = new JTextField("0");
		this.textNbFaces = new JTextField("0");

		this.add(new JLabel("Nom"));
		this.add(textNom);
		this.add(new JLabel("Auteur"));
		this.add(textAuteur);
		this.add(new JLabel("Nombre de points"));
		this.add(textNbPoints);
		this.add(new JLabel("Nombre de segments"));
		this.add(textNbSeg);
		this.add(new JLabel("Nombre de faces"));
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
