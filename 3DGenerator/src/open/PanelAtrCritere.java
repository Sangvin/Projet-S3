package open;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

	private static final long serialVersionUID = 1L;
	private JTextField nomObj;
	private JFormattedTextField dateAjt;
	private JSpinner nbf;
	private JSpinner nbs;
	private JSpinner nbp;
	private JTextField nmauteur;
	private ControllerRecherche controller;
	private ModelRecherche model;

	public PanelAtrCritere(ModelRecherche model, ControllerRecherche controller){
		this.model = model;
		this.controller = controller;
		this.setBorder(BorderFactory.createTitledBorder("Autres critères de recherche :"));
		this.initComponents();
		this.model.addObserver(this);
	}

	private void initComponents(){
		this.nomObj = new JTextField();
		try {
			this.dateAjt = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.nbp = new JSpinner(new SpinnerNumberModel(0,0,99999999,1));
		this.nbs = new JSpinner(new SpinnerNumberModel(0,0,99999999,1));
		this.nbf = new JSpinner(new SpinnerNumberModel(0,0,99999999,1));		
		this.nmauteur = new JTextField();

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
