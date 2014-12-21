package bdd;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * Cette classe contient le panel de recherche par mots clé dans la base de donnee
 * 
 * @autor Douae
 *
 */
public class PanelMcle extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextArea motCle;
	private JTextField forme;
	private JTextField utilisation;
	private JTextField typeVolume;
	
	public PanelMcle(){
		this.initComponents();
		this.setBorder(BorderFactory.createTitledBorder("Recherche Par mots clés"));
		
	}

	private void initComponents(){
		this.motCle = new JTextArea(5,15);
		this.motCle.setLineWrap(true);
		JScrollPane p = new JScrollPane(this.motCle);
		this.forme = new JTextField(15);
		this.utilisation = new JTextField(15);
		this.typeVolume = new JTextField(15);
		
		JPanel tmp1 = new JPanel();
		JPanel tmp2 = new JPanel();
		JPanel tmp3 = new JPanel();
		JPanel tmp4 = new JPanel();
		
        tmp1.add(new JLabel("Mots-clés"));
        tmp1.add(p);
    	tmp2.add(new JLabel("Forme "));
    	tmp2.add(forme);
    	tmp3.add(new JLabel("Utilisation "));
    	tmp3.add(utilisation);
    	tmp4.add(new JLabel("Type de Volume"));  
    	tmp4.add(typeVolume);
    	
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(tmp1);
		this.add(tmp2);
		this.add(tmp3);
		this.add(tmp4);
	}
}
