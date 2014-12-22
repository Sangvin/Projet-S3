package open;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
public class PanelAtrCritere extends JPanel{

	private static final long serialVersionUID = 1L;
	protected JTextField nomObj;
	protected JFormattedTextField dateAjt;
	protected JSpinner nbf;
	protected JSpinner nbs;
	protected JSpinner nbp;
	protected JTextField nmauteur;
	protected JTextField  six= new JTextField(30);
	protected JTextField  sept= new JTextField(15);
	protected JTextField  huit= new JTextField(15);
	protected JTextField  neuf= new JTextField(15);
	protected JTextField  dix= new JTextField(30);
	protected JButton annuler;
	protected JButton valider;
	
	public PanelAtrCritere(){
		this.setBorder(BorderFactory.createTitledBorder("Autres critères de recherche :"));
		this.initComponents();
	}
	
	private void initComponents(){
		this.annuler = new JButton("Annuler");
		this.annuler.setBackground(Color.WHITE);	
		this.annuler.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.valider = new JButton("Valider");
		this.valider.setBackground(Color.WHITE);	
		this.valider.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
		
//		JPanel tmp2 = new JPanel();
//		JPanel tmp3 = new JPanel();
//		JPanel tmp4 = new JPanel();
		
		GridLayout g = new GridLayout(7,2);
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
		g.setHgap(5);
		g.setVgap(10);
		this.add(this.valider);
		this.add(this.annuler);
	}
}
