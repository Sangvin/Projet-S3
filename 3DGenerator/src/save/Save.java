package save;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;

import objet.Objet3D;

/**
 * cette classe contient les différents panel permettant la saisie d'information pour les
 * enregistrements d'un objet 3d dans la base de donnée.
 * @author Alex
 *
 */
public class Save extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * contient l'objet à enregistrer
	 */
	private Objet3D object;
	/**
	 * panel permettant la saisie d'infos générales
	 */
	private GeneralInformation info;
	/**
	 * panel permettant la saisie des informations de tri
	 */
	private SortInformation sorted;
	/**
	 * permet de valider la sauvegarde
	 */
	private JButton valider;
	/**
	 * permet d'annuler la sauvegarde
	 */
	private JButton annuler;
	
	/**
	 * constructeur de la frame
	 * @param object
	 */
	public Save(Objet3D object){
		this.object = object;
		this.initComponents();
		this.setTitle("Enregistrer");
		this.pack();
		this.setResizable(false);
		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-this.getWidth()/2);
		int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-this.getHeight()/2);
		this.setLocation(x, y);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	/**
	 * permet de placer les composants
	 */
	private void initComponents(){
		this.info = new GeneralInformation(this.object.getFichier().getAbsolutePath());
		this.sorted = new SortInformation();
		
		this.valider = new JButton("Valider");
		this.annuler = new JButton("Annuler");
		
		GridBagLayout bagLayout = new GridBagLayout();
        this.setLayout(bagLayout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1;
        c.weighty = 0;
        c.fill = GridBagConstraints.NORTHEAST;
        c.gridwidth = 1;
        c.gridheight = 1;
        
        c.gridx = 0;
        c.gridy = 0;
        bagLayout.setConstraints(this.info, c);
        this.getContentPane().add(this.info);
        
        c.gridx = 1;
        bagLayout.setConstraints(this.sorted, c);
        this.getContentPane().add(this.sorted);
        
        c.gridx = 0;
        c.gridy = 1;
        bagLayout.setConstraints(this.valider, c);
        this.getContentPane().add(this.valider);
        
        c.gridx = 1;
        bagLayout.setConstraints(this.annuler, c);
        this.getContentPane().add(this.annuler);
	}
	
	public static void main(String[] args) throws Exception {
		new Save(new Objet3D("./drone_dead_orbit_LP.gts",Color.RED));
	}
}
