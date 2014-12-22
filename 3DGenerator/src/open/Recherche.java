package open;
import graphic.Frame;

import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
/**
 * Cette class contient tout les panels c'est celle la qui est instancié lors de la compilation
 * elle sert a chercher un fichier dans la base de donnée et l'importer dans le logiciel (IG pricipale) , 
 * elle appelé depuis la premiere fenetre en choisissant " ouvrir " dans le menu;
 * 
 * @autor Douae
 *
 */
public class Recherche extends JDialog {
    private static final long serialVersionUID = 1L;
    private JpResultats jpResultats;
    private JpRecherche jpRecherche;
    
	public Recherche(Frame f){
		super(f,"Recherche de fichiers",true);
		this.initComponents();
		this.pack();	
		this.setResizable(false);
		int screen_x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
		int screen_y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
		this.setLocation(screen_x-this.getWidth()/2, screen_y-this.getHeight()/2);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setVisible(true);
	}
	
	private void initComponents(){
		this.jpResultats = new JpResultats();
		this.jpRecherche = new JpRecherche();
		this.setLayout(new FlowLayout());
		this.add(this.jpResultats);
		this.add(this.jpRecherche);
	}
}

