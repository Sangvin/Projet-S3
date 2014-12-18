package graphic;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PanelBouton extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9039047326989267376L;
	
	public PanelBouton(PanelObjet tablette){
		this.setLayout(new BoxLayout(this, 1));
		JPanel rot = new PanelRotation(tablette);
		JPanel zoom = new PanelZoom(tablette);
		JPanel dep = new PanelDep(tablette);
		
		this.add(zoom);
		this.add(rot);
		this.add(dep);
	}

}