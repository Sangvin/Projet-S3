package open;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * Cette classe  contient le panel d'affichage des resultats de la recherche
 * 
 * @autor Douae
 *
 */
public class JpResultats extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel recherche;
	private JList<String> result;
	private JScrollPane jscroll;
	private DefaultListModel<String> res;
	private JButton ouvrir;

	public JpResultats(){
		this.initComponents();
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
	}
	
	private void initComponents(){
		int x;
		int y;
		this.recherche = new JLabel("Résultats de la recherche:");
		this.ouvrir = new JButton("Ouvrir");
		this.ouvrir.setBackground(Color.WHITE);	
		this.ouvrir.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		x = (int) this.ouvrir.getPreferredSize().getWidth();
		y = (int) this.ouvrir.getPreferredSize().getHeight();
		this.ouvrir.setPreferredSize(new Dimension(x+6,y+6));
		
		this.result = new JList<String>();
		this.jscroll = new JScrollPane(this.result);
		this.res = new DefaultListModel<String>();
		this.result.setModel(this.res);
		this.jscroll.setPreferredSize(new Dimension(100,400));
		
		JPanel tmp1 = new JPanel();
		tmp1 .setBackground(Color.LIGHT_GRAY);
		tmp1.setPreferredSize(new Dimension(0,10));
		JPanel tmp2 = new JPanel();
		tmp2 .setBackground(Color.LIGHT_GRAY);
		tmp2.setPreferredSize(new Dimension(0,10));
		JPanel centrerOuvrir = new JPanel();
		centrerOuvrir.setBackground(Color.LIGHT_GRAY);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(recherche);
		this.add(jscroll);
		this.add(tmp1);
		centrerOuvrir.add(ouvrir);
		this.add(centrerOuvrir);
		this.add(tmp2);
	}
}
