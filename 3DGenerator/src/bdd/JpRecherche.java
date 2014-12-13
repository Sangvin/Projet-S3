package bdd;
import java.awt.GridLayout;
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
		this.setLayout(new GridLayout(2,1));
        PanelMcle pmc= new PanelMcle();
        PanelAtrCritere pac=new PanelAtrCritere(); 
        this.add(pmc);
        this.add(pac);
	}
}
