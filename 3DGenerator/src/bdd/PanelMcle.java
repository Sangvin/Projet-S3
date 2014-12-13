package bdd;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Cette classe contient le panel de recherche par mots clé dans la base de donnee
 * 
 * @autor Douae
 *
 */
public class PanelMcle extends JPanel{

	private static final long serialVersionUID = 1L;
	protected JLabel rechMCle =new JLabel("Recherche par mots-clés");
	protected JLabel motCle = new JLabel("Mots-clés");
	protected JLabel rechMCleMcr= new JLabel("Mots-clés multicritères:");
	protected JLabel forme= new JLabel("Forme ");
	protected JLabel utilisation= new JLabel("Utilisation ");
	protected JLabel typeVolume= new JLabel("Type de Volume");
	protected JTextField  un= new JTextField(30);
	protected JTextField  deu= new JTextField(30);
	protected JTextField  troi= new JTextField(30);
	protected JTextField  quatre= new JTextField(30);
	
	public PanelMcle(){
		this.setBorder(BorderFactory.createTitledBorder("Recherche Par mots clés"));
		this.setLayout(null);
		this.add(motCle);
        this.add(un);
    	this.add(rechMCleMcr);
    	this.add(deu);
    	this.add(forme);
    	this.add(troi);
    	this.add(utilisation);
    	this.add(quatre);  
    	this.add(typeVolume);
    	this.rechMCle.setBounds(70, 40, 200, 30);
 	    this.motCle.setBounds(100, 70, 100, 30);
 	    this.un.setBounds(180, 70, 100, 25);
 	    this.rechMCleMcr.setBounds(100, 140, 300, 30);
 	    this.forme.setBounds(100, 170, 190, 30);
 	    this.deu.setBounds(220, 170, 100, 25);	    
 	    this.utilisation.setBounds(100, 200, 100, 30);
 	    this.troi.setBounds(220, 200, 100, 25);
 	    this.typeVolume.setBounds(100, 230, 150, 30);
 	    this.quatre.setBounds(220, 230, 100, 25);
	}
}
