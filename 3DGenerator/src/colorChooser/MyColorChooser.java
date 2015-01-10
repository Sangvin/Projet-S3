package colorChooser;

import graphic.Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mvc.Model;

/**
 * Classe permettant de creer un color chooser fonctionne avec ColorTable et ColorCreator
 * @author Alex
 */
public class MyColorChooser extends JDialog{
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
	 * Contient la frame principale
	 */
	private Frame f;
	/**
	 * Conserve la couleur d'origine
	 */
	private Color save;
	/**
	 * Contient le modèle
	 */
	private Model model;
	/**
	 * Permet de choisir entre le background et l'objet
	 */
	private char option;

	/**
	 * instancie les différents éléments et enregistre la frame appelante
	 * @param i
	 */
	public MyColorChooser(Frame f,Model model,char option){
		super(f,"ColorChooser",true);
		this.model = model;
		this.f = f;
		this.option = option;
		initComponent();
		initProperties();
	}

	/**
	 * inittialise les propriété de la frame
	 */
	private void initProperties(){
		pack();
		int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()-this.getWidth()-10);
		int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3-this.getHeight()/2); 
		this.setLocation(width,height);
		this.setResizable(false);

		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				if(model != null){
					if(option == 'o'){
						model.setColor(save);
					}
					else{
						f.setbackground(save);
					}
				}
				dispose();
			}
		});
		this.setVisible(true);
	}

	/**
	 * initialise et position les éléments
	 */
	private void initComponent(){
		titre = new JLabel();
		titre.setFont(new Font("Comic Sans MS", 0, 24));
		titre.setText("Choisi ta propre couleur");

		color = new JPanel();
		if(this.model != null){
			if(this.option == 'o'){
				color.setBackground(this.model.getColor());
				save = color.getBackground();
			}
			else{
				color.setBackground(this.f.getbackground());
				save = color.getBackground();
			}
		}
		color.setPreferredSize(new Dimension(100,100));
		color.setBorder(BorderFactory.createLineBorder(Color.black));


		appliquer = new JButton("Appliquer");
		appliquer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model != null){
					if(option == 'o'){
						model.setColor(save);
					}
					else{
						f.setbackground(save);
					}
				}
				dispose();
			}
		});

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

		JPanel tmp = new JPanel();
		tmp.add(appliquer);
		tmp.add(annuler);
		c.gridwidth = 2;
		c.gridy = 3;
		c.gridx = 0;
		bagLayout.setConstraints(tmp, c);
		add(tmp);
	}

	/**
	 * permet de récupérer la couleur courante
	 * @return
	 */
	public Color getCurentColor(){
		return color.getBackground();
	}

	/**
	 * change la couleur actuelle temporairement
	 * @param color
	 */
	public void setColor(Color color) {
		this.color.setBackground(color);
		this.creator.actualiseSlider(color);
		if(this.model != null){
			if(this.option == 'o'){
				this.model.setColor(color);
				this.f.getTablette().repaint();
			}
			else
				this.f.setbackground(color);
		}
	}
}
