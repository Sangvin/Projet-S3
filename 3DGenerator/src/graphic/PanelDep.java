package graphic;

import java.awt.GridLayout;

import javax.swing.*;

public class PanelDep extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5602993964742282381L;

	public PanelDep(){
		JButton haut = new JButton("Haut");
		JButton droite = new JButton("Droite");
		JButton bas = new JButton("Bas");
		JButton gauche = new JButton("Gauche");
		this.setLayout(new GridLayout(3, 3));
		this.add(Box.createRigidArea(null));
		this.add(haut);
		this.add(Box.createRigidArea(null));
		this.add(gauche);
		this.add(Box.createRigidArea(null));
		this.add(droite);
		this.add(Box.createRigidArea(null));
		this.add(bas);
		this.add(Box.createRigidArea(null));
	}
}
