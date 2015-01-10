package open;
import java.awt.GridLayout;
import java.text.ParseException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;
/**
 * Cette classe contient le panel de recherche avancée dans la base de donnee
 * 
 * @autor Douae
 *
 */
public class PanelAtrCritere extends JPanel implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Permet la saisie de nom de l'objet
	 */
	private JTextField nomObj;
	/**
	 * Permet de saisie la date d'ajout
	 */
	private JFormattedTextField dateAjt;
	/**
	 * Permet de choisir un nombre maximum de faces
	 */
	private JSpinner nbf;
	/**
	 * Permet de choisir un nombre maximum de segments
	 */
	private JSpinner nbs;
	/**
	 * Permet de choisir un nombre maximum de points
	 */
	private JSpinner nbp;
	/**
	 * Permet la saisie du nom de l'auteur
	 */
	private JTextField nmauteur;
	/**
	 * Contient un controller mvc de la fenêtre
	 */
	private ControllerRecherche controller;
	/**
	 * Contient le modèle mvc de la fenêtre
	 */
	private ModelRecherche model;

	/**
	 * Constructeur du panel
	 * @param model
	 * @param controller
	 */
	public PanelAtrCritere(ModelRecherche model, ControllerRecherche controller){
		this.model = model;
		this.controller = controller;
		this.setBorder(BorderFactory.createTitledBorder("Autres critères de recherche :"));
		this.initComponents();
		this.model.addObserver(this);
	}

	/**
	 * Permet d'initialiser les composants
	 */
	private void initComponents(){
		this.nomObj = new JTextField();
		this.nomObj.setToolTipText("Entrez ici le nom d'un objet");
		try {
			this.dateAjt = new JFormattedTextField(new MaskFormatter("##/##/####"));
			this.dateAjt.setToolTipText("Entrez une date d'ajout maximale au format JJ/MM/AAAA");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.nbp = new JSpinner(new SpinnerNumberModel(0,0,99999999,1));
		this.nbp.setToolTipText("Choisissez le nombre de points max (Si 0 le critère est ignoré)");
		this.nbs = new JSpinner(new SpinnerNumberModel(0,0,99999999,1));
		this.nbs.setToolTipText("Choisissez le nombre de segments max (Si 0 le critère est ignoré)");
		this.nbf = new JSpinner(new SpinnerNumberModel(0,0,99999999,1));
		this.nbf.setToolTipText("Choisissez le nombre de faces max (Si 0 le critère est ignoré)");		
		this.nmauteur = new JTextField();
		this.nmauteur.setToolTipText("Entrez un auteur");

		GridLayout g = new GridLayout(6,2);
		g.setHgap(5);
		g.setVgap(10);
		this.setLayout(g);
		this.add(new JLabel("Nom de l'objet"));
		this.add(this.nomObj);
		this.add(new JLabel("Date d'Ajout"));
		this.add(this.dateAjt);
		this.add(new JLabel("Nombre de points"));
		this.add(this.nbp);
		this.add(new JLabel("Nombre de segments"));
		this.add(this.nbs);
		this.add(new JLabel("Nombre de Faces"));
		this.add(this.nbf);
		this.add(new JLabel("Nom de l'auteur"));
		this.add(this.nmauteur);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.nomObj.setText(model.getNom());
		this.dateAjt.setText(model.getDate());
		this.nbf.setValue(model.getNb_faces());
		this.nbp.setValue(model.getNb_points());
		this.nbs.setValue(model.getNb_segments());
		this.nmauteur.setText(model.getAuteur());
	}
	
	/**
	 * permet de mettre à jour le modèle
	 */
	public void updateController(){
		controller.setNom(this.nomObj.getText());
		controller.setDate(this.dateAjt.getText());
		controller.setNb_points((int) this.nbp.getValue());
		controller.setNb_faces((int) this.nbf.getValue());
		controller.setNb_segments((int) this.nbs.getValue());
		controller.setAuteur(this.nmauteur.getText());
	}
}
