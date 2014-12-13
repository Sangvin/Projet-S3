package bdd;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Cette classe contient le panel de recherche avancée dans la base de donnee
 * 
 * @autor Douae
 *
 */
public class PanelAtrCritere extends JPanel{

	private static final long serialVersionUID = 1L;
	protected JLabel idObj= new JLabel("Identifiant de l'objet");
	protected JLabel dateAjt= new JLabel("Date d'Ajout");
	protected JLabel cmplxmdl= new JLabel("Complexité du modèle");
	protected JLabel nbf= new JLabel("Nombre de Faces");
	protected JLabel nbs= new JLabel("Nombre de segments");
	protected JLabel nbp= new JLabel("Nombre de points");
	protected JLabel nmauteur= new JLabel("Nom de l'auteur");
	protected JTextField  cinq= new JTextField(30);
	protected JTextField  six= new JTextField(30);
	protected JTextField  sept= new JTextField(15);
	protected JTextField  huit= new JTextField(15);
	protected JTextField  neuf= new JTextField(15);
	protected JTextField  dix= new JTextField(30);
	protected JButton annuler=new JButton("Annuler");
	protected JButton valider=new JButton("Valider");
	
	public PanelAtrCritere(){
		this.setLayout(null);
		this.setBorder(BorderFactory.createTitledBorder("Autres critères de recherche :"));
		this.annuler.setBackground(Color.WHITE);
		this.annuler.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.valider.setBackground(Color.WHITE);
		this.valider.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.add(annuler);
		this.add(valider);
    	this.add(idObj);
    	this.add(dateAjt);
    	this.add(cmplxmdl);
	    this.add(nbf);
	    this.add(nbs);
	    this.add(nbp);
	    this.add(nmauteur);
	    this.add(cinq);
	    this.add(six);
	    this.add(sept);
	    this.add(huit);
	    this.add(neuf);
	    this.add(dix);
	    this.annuler.setBounds(370, 285, 100, 30);
		this.valider.setBounds(20, 285, 100, 30);
	    this.idObj.setBounds(100, 75, 150, 30);
	    this.cinq.setBounds(265, 75, 100, 25);
	    this.dateAjt.setBounds(100, 105, 150, 30);
	    this.six.setBounds(265, 105, 100, 25);
	    this.cmplxmdl.setBounds(100, 135, 200, 30);   
	    this.nbf.setBounds(150, 165, 200, 30);
	    this.sept.setBounds(310, 165, 50, 20);
	    this.nbs.setBounds(150, 185, 200, 30);
	    this.huit.setBounds(310, 185, 50, 20);
	    this.nbp.setBounds(150, 205, 200, 30);
	    this.neuf.setBounds(310, 205, 50, 20);
	    this.nmauteur.setBounds(100, 245, 200, 30);
	    this.dix.setBounds(220, 245, 100, 25);
	}
}
