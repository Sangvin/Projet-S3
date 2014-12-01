package graphic;

import java.awt.GridLayout;

import javax.swing.*;

public class PanelRotation extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -126940328392104128L;

	public PanelRotation(){
		
		JLabel textRotation = new JLabel("Rotation");
		JButton xpos = new JButton("x+");
		JButton xneg = new JButton("x-");
		JButton ypos = new JButton("y+");
		JButton yneg = new JButton("y-");
		JButton zpos = new JButton("z+");
		JButton zneg = new JButton("z-");
		
		JPanel x = new JPanel();
		JPanel y = new JPanel();
		JPanel z = new JPanel();
		
		x.add(xpos);
		x.add(xneg);
		y.add(ypos);
		y.add(yneg);
		z.add(zpos);
		z.add(zneg);
		
		this.setLayout(new BoxLayout(this, 1));
		
		this.add(textRotation);
		this.add(x);
		this.add(y);
		this.add(z);
	}
}
