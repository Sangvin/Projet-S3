package graphic;

import java.awt.GridLayout;

import javax.swing.*;

public class PanelBouton extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9039047326989267376L;
	public PanelBouton(){
		this.setLayout(new GridLayout(3, 1));
		JPanel rot = new PanelRotation();
		JPanel zoom = new PanelZoom();
		JPanel dep = new PanelDep();
		
		this.add(zoom);
		this.add(rot);
		this.add(dep);
	}

}
