package open;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
/**
 * Cette class contient tout les panels c'est celle la qui est instancié lors de la compilation
 * elle sert a chercher un fichier dans la base de donnée et l'importer dans le logiciel (IG pricipale) , 
 * elle appelé depuis la premiere fenetre en choisissant " ouvrir " dans le menu;
 * 
 * @autor Douae
 *
 */
public class Recherche extends JFrame {
    private static final long serialVersionUID = 1L;
    
	public Recherche(){
		super("Recherche de fichiers");
		this.initComponents();
		this.pack();
		this.setVisible(true);	
		this.setResizable(false);
		int screen_x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
		int screen_y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
		this.setLocation(screen_x-this.getWidth()/2, screen_y-this.getHeight()/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	private void initComponents(){
		JpResultats jpResultats=new JpResultats();
		JpRecherche jpRecherche=new JpRecherche();
		this.setLayout(new FlowLayout());
		this.add(jpResultats);
		this.add(jpRecherche);
	}
	
	public static void main(String[] args){
		new Recherche();
	}
}

