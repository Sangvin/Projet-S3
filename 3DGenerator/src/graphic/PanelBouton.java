package graphic;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import mvc.Model;
import mvc.ObjectController;

/**
 * Contient les diff�rents panel de boutons
 * @author Vincent
 *
 */
public class PanelBouton extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9039047326989267376L;

	/**
	 * Constructeur du panel
	 * @param model
	 * @param controller
	 */
	public PanelBouton(Model model, ObjectController controller){
		this.setLayout(new BoxLayout(this, 1));
		JPanel rot = new PanelRotation(controller);
		JPanel zoom = new PanelZoom(controller);
		JPanel dep = new PanelDep(controller);
		JPanel info = new PanelInfo();
		
		this.add(zoom);
		this.add(rot);
		this.add(dep);
		this.add(info);
	}

}