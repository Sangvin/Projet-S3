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
	private PanelMcle pmc;
	private PanelAtrCritere pac;
	
	public JpRecherche(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.pmc = new PanelMcle();
        this.pac = new PanelAtrCritere(); 
        this.add(this.pmc);
        this.add(this.pac);
	}
}
