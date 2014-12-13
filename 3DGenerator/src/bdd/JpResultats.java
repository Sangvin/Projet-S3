package bdd;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
/**
 * Cette classe  contient le panel d'affichage des resultats de la recherche
 * 
 * @autor Douae
 *
 */
public class JpResultats extends JPanel {

	private static final long serialVersionUID = 1L;
	protected JLabel rec =new JLabel("Résultats de la recherche:");
	protected JButton importe=new JButton("Importer");

	public JpResultats(){
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		this.importe.setBackground(Color.WHITE);	
		this.importe.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.add(rec);
		this.add(importe);
		this.rec.setBounds(150, 5, 250, 30);
		this.importe.setBounds(200, 610, 100, 30);
	}
}
