package bdd;
import java.awt.GridLayout;
import javax.swing.JFrame;
/**
 * Cette class contient tout les panels c'est celle la qui est instancié lors de la compilation
 * elle sert a chercher un fichier dans la base de donnée et l'importer dans le logiciel (IG pricipale) , 
 * elle appelé depuis la premiere fenetre en choisissant " chercher " dans le menu;
 * 
 * @autor Douae
 *
 */
public class Recherche extends JFrame {
    private static final long serialVersionUID = 1L;
	protected int x=1250;
	protected int y=700;
	
	public Recherche(){
		super("Recherche de fichiers");
		this.setVisible(true);	
		this.setResizable(false);
		this.setLocation(100,20);
		this.setLayout(new GridLayout(1,2));
		JpResultats jPanel=new JpResultats();
		JpRecherche jPanel3=new JpRecherche();
		this.add(jPanel);
		this.add(jPanel3);
		this.setSize(1000,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		System.out.println(jPanel.getSize());
	}
	public static void main(String[] args){
		new Recherche();
	}
}

