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
	 * Contient les informations de la figure
	 */
	private JPanel info;

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
		this.info = new PanelInfo(model);
		
		this.add(zoom);
		this.add(rot);
		this.add(dep);
		this.add(this.info);
	}
}