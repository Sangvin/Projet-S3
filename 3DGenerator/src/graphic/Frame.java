package graphic;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import mvc.Model;
import mvc.ObjectController;
import objet.Point;

/**
 * Contient la fenêtre principale du logiciel
 * @author Vincent
 *
 */
public class Frame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * tablette ou sera dessiné la figure
	 */
	private PanelObjet tablette;
	/**
	 * panel qui contient ts les boutons de control
	 */
	private PanelBouton button;
	/**
	 * Contient la bare de menu
	 */
	private MenuBar menu;
	/**
	 * contient le model
	 */
	private Model model;
	/**
	 * controller du modèle
	 */
	private ObjectController controller;

	/**
	 * Constructeur de la fenêtre
	 * @param model
	 * @param controller
	 */
	public Frame(Model model, ObjectController controller){
		this.model = model;
		this.controller = controller;
		this.setTitle("3DGenerator");
		this.initComponents();
		this.controller.addView(this.tablette);
		this.setExtendedState(MAXIMIZED_HORIZ | MAXIMIZED_VERT | MAXIMIZED_BOTH);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		try {
			this.setIconImage(ImageIO.read(this.getClass().getResource("Logo.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * permet d'initialiser les composants
	 */
	private void initComponents(){
		ObjectController controlTablette = new ObjectController(this.model);
		this.tablette = new PanelObjet(this.model, controlTablette);
		controlTablette.addView(this.tablette);
		
		ObjectController controlMenu = new ObjectController(this.model);
		this.menu = new MenuBar(this,this.model,controlMenu);
		this.setJMenuBar(this.menu);
		controlMenu.addView(this.tablette);
		
		ObjectController controlButton = new ObjectController(this.model);
		this.button = new PanelBouton(this.model, controlButton);
		controlButton.addView(this.tablette);

		this.initKeyPad();

		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;

		this.add(this.tablette,gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10,10,50,10);

		this.add(this.button,gbc);

	}

	/**
	 * permet d'initialiser les racourcis claviers
	 */
	private void initKeyPad(){
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("A"), "A" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("Z"), "Z" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("E"), "E" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("D"), "D" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("S"), "S" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("Q"), "Q" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("J"), "J" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("K"), "K" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("I"), "I" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("L"), "L" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("V"), "V" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("B"), "B" );

		this.getRootPane().getActionMap().put("A", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				controller.rotationZ(-Math.PI/100);
			}
		});
		this.getRootPane().getActionMap().put("Z", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				controller.rotationX(Math.PI/100);
			}
		});
		this.getRootPane().getActionMap().put("E", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				controller.rotationZ(Math.PI/100);
			}
		});
		this.getRootPane().getActionMap().put("Q", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				controller.rotationY(-Math.PI/100);
			}
		});
		this.getRootPane().getActionMap().put("S", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				controller.rotationX(-Math.PI/100);
			}
		});
		this.getRootPane().getActionMap().put("D", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				controller.rotationY(Math.PI/100);
			}
		});
		this.getRootPane().getActionMap().put("I", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				controller.deplacement(new Point(0,-5,0));
			}
		});
		this.getRootPane().getActionMap().put("K", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
					controller.deplacement(new Point(0,5,0));
			}
		});
		this.getRootPane().getActionMap().put("J", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
					controller.deplacement(new Point(-5,0,0));
			}
		});
		this.getRootPane().getActionMap().put("L", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
					controller.deplacement(new Point(5,0,0));
			}
		});
		this.getRootPane().getActionMap().put("B", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
					controller.zoom(1.05);
			}
		});
		this.getRootPane().getActionMap().put("V", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
					controller.zoom(0.95);
			}
		});
	}

	/**
	 * retourne la tablette de dessin
	 * @return
	 */
	public PanelObjet getTablette(){
		return this.tablette;
	}

	/**
	 * récupère l'arrière plan de la tablette
	 * @return
	 */
	public Color getbackground(){
		return this.tablette.getbackground();
	}

	/**
	 * modifie le background
	 * @param c
	 */
	public void setbackground(Color c){
		this.tablette.setbackground(c);
	}
}

