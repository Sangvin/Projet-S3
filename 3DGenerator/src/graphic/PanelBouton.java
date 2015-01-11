package graphic;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import mvc.Model;
import mvc.ObjectController;

/**
 * Contient les différents panel de boutons
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
		JTabbedPane table = new JTabbedPane();
		table.addTab("Informations générales", new PanelInfoGeneral(model));
		table.addTab("Informations complémentaires", new PanelInfoSpe(model));
		
		this.add(zoom);
		this.add(rot);
		this.add(dep);
		this.add(table);
	}
}