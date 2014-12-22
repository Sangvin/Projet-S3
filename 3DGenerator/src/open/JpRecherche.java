package open;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
/**
 * Cette classe contient le panel de recherche par mots clé et le panel de recherche avancée
 * 
 *  @autor Douae
 *
 */
public class JpRecherche extends JPanel {

	private static final long serialVersionUID = 1L;

	public JpRecherche(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        PanelMcle pmc = new PanelMcle();
        PanelAtrCritere pac = new PanelAtrCritere(); 
        this.add(pmc);
        this.add(pac);
	}
}
