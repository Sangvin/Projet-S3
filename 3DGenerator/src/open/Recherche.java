package open;
import graphic.Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import mvc.Model;
import mvc.ObjectController;
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
    private JButton annuler;
    private ObjectController controller;
    private Frame parent;
    
	public Recherche(Frame f,ObjectController controller){
		super(f,"Recherche de fichiers",true);
		this.parent = f;
		this.controller = controller;
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
		ModelRecherche modelRecherche = new ModelRecherche();
		ControllerRecherche jpResController = new ControllerRecherche(modelRecherche);
		ControllerRecherche jpRecController = new ControllerRecherche(modelRecherche);
		this.jpResultats = new JpResultats(parent,this,controller,modelRecherche,jpResController);
		this.jpRecherche = new JpRecherche(modelRecherche,jpRecController);
		jpResController.addView(jpResultats);
		
		this.annuler = new JButton("Annuler");
		this.annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		this.annuler.setBackground(Color.WHITE);	
		this.annuler.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		int sizex = (int) (this.annuler.getPreferredSize().getWidth()+6);
		int sizey = (int) (this.annuler.getPreferredSize().getHeight()+6);
		this.annuler.setPreferredSize(new Dimension(sizex,sizey));
		
		GridBagLayout bagLayout = new GridBagLayout();
		this.setLayout(bagLayout);
		GridBagConstraints c = new GridBagConstraints();
		
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(2,2,2,2);	
        c.weightx = 1;
        c.weighty = 0;
        c.fill = GridBagConstraints.CENTER;
        
        c.gridwidth = 1;
        c.gridheight = 2;
        c.gridx = 0;
        c.gridy = 0;
        bagLayout.setConstraints(jpResultats, c);
        this.add(jpResultats);
        
        c.gridheight = 1;
        c.gridx = 1;
        bagLayout.setConstraints(jpRecherche, c);
        this.add(jpRecherche);
        
        c.gridy = 1;
        bagLayout.setConstraints(annuler, c);
        this.add(annuler);
	}
	
	public static void main(String[] argv){
		new Recherche(null, new ObjectController(new Model()));
	}
}

