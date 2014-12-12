package colorChooser;

import graphic.PanelObjet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe permettant de creer un color chooser fonctionne avec ColorTable et ColorCreator
 * @author Alex Dalencourt
 * @author Yoan Lamaire
 */
public class MyColorChooser extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * titre du colorChooser
	 */
	private JLabel titre;
	/**
	 * permet l'aperçu de la couleur
	 */
	private JPanel color;
	/**
	 * titre de la partie rouge du colorCreator
	 */
	private JButton appliquer;
	/**
	 * contient une table avec 216 couleurs différentes
	 */
	private ColorTable table;
	/**
	 * contient une module permettant de creer sa propre couleur
	 */
	private ColorCreator creator;
	/**
	 * Contient la tablette de dessin
	 */
	private PanelObjet tablette;
	
	/**
	 * instancie les différents éléments et enregistre la frame appelante
	 * @param i
	 */
	public MyColorChooser(/*PanelObjet tablette*/){
		//this.tablette = tablette;
		this.setTitle("ColorChooser");
		initComponent();
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * initialise et position les éléments
	 */
	private void initComponent(){
	  	titre = new JLabel();
    	titre.setFont(new Font("Comic Sans MS", 0, 24));
    	titre.setText("Choisi ta propre couleur");
    	
    	color = new JPanel();
    	color.setBackground(new Color(255,255,255));
    	color.setPreferredSize(new Dimension(100,100));
    	color.setBorder(BorderFactory.createLineBorder(Color.black));
    	
        appliquer = new JButton("Appliquer");
        
        table = new ColorTable(this);
        creator = new ColorCreator(this);
        
        GridBagLayout bagLayout = new GridBagLayout();
        setLayout(bagLayout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1.0;
        c.weighty = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NORTHEAST;
        bagLayout.setConstraints(titre, c);
        add(titre);
        
        c.gridwidth = 1;
        c.gridy = 1;
        bagLayout.setConstraints(color, c);
        add(color);
        
        c.gridx = 1;
        c.gridwidth = 1;
        bagLayout.setConstraints(creator, c);
        add(creator);
        
        c.gridwidth = 2;
        c.gridy = 2;
        c.gridx = 0;
        bagLayout.setConstraints(table, c);
        add(table);
        
        c.gridwidth = 2;
        c.gridy = 3;
        c.gridx = 0;
        bagLayout.setConstraints(appliquer, c);
        add(appliquer);
	}
	
	/**
	 * permet de récupérer la couleur courante
	 * @return
	 */
	public Color getCurentColor(){
		return color.getBackground();
	}
	
	public static void main(String[] args) {
		new MyColorChooser();
	}
}
