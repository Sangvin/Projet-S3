package save;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SortInformation extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SortInformation(){
		this.setBorder(BorderFactory.createTitledBorder("Champs facultatifs"));
		this.initComponents();
	}
	
	private void initComponents(){
		GridLayout g = new GridLayout(3,2);
		this.setLayout(g);
		g.setHgap(10);
		g.setVgap(5);
		this.add(new JLabel("Tag:"));
		this.add(new JTextField());
		this.add(new JLabel("Utilisation:"));
		this.add(new JTextField());
		this.add(new JLabel("Forme:"));
		this.add(new JTextField());
	}
}
