package graphic;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import mvc.Model;
import mvc.ObjectController;

public class PanelBouton extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9039047326989267376L;

	public PanelBouton(Model model, ObjectController controller){
		this.setLayout(new BoxLayout(this, 1));
		JPanel rot = new PanelRotation(controller);
		JPanel zoom = new PanelZoom(controller);
		JPanel dep = new PanelDep(controller);
		
		this.add(zoom);
		this.add(rot);
		this.add(dep);
	}

}