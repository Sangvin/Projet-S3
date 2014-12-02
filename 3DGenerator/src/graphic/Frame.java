package graphic;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -272179185205729057L;

	public Frame(String titre){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(new MenuBar());
		this.setMinimumSize(new Dimension(800, 800));
		this.pack();
		this.setLayout(new GridLayout(1, 2));
		this.add(new PanelObjet());
		this.add(new PanelBouton());
		this.setVisible(true);
		this.setResizable(false);
		this.setExtendedState(this.MAXIMIZED_BOTH);
	}
	
	public static void main(String[] args){
		JFrame f = new Frame("Test");
	}
}


