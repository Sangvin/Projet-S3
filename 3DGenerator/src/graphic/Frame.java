package graphic;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import objet.Objet3D;
import objet.Point;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * objet ï¿½ afficher
	 */
	private Objet3D object;
	/**
	 * tablette ou sera dessinï¿½ la figure
	 */
	private PanelObjet tablette;
	/**
	 * panel qui contient ts les boutons de control
	 */
	private PanelBouton button;

	public Frame(Objet3D o){
		this.object = o;
		this.setTitle("3DGenerator");
		this.initComponents();
		this.pack();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setAlwaysOnTop(true);
		this.object.setVector(new Point(this.getSize().width/3,this.getSize().height/2,0));
		this.tablette.attachObjet3D(this.object);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	private void initComponents(){
		this.tablette = new PanelObjet();
		this.setJMenuBar(new MenuBar(this));
		this.button = new PanelBouton(this.tablette);

		/*this.setLayout(new GridLayout(1, 2));

		this.add(this.tablette);
		this.add(this.button);*/
		
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
	
	private void initKeyPad(){
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("A"), "A" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("Z"), "Z" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("E"), "E" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("D"), "D" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("S"), "S" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("Q"), "Q" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("LEFT"), "LEFT" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("DOWN"), "DOWN" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("UP"), "UP" );
		this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("RIGHT"), "RIGHT");
		
		this.getRootPane().getActionMap().put("A", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				object.rotationZ(-Math.PI/100);
				object.setColor(object.getColor());
				repaint();
			}
		});
		this.getRootPane().getActionMap().put("Z", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				object.rotationX(Math.PI/100);
				object.setColor(object.getColor());
				repaint();
			}
		});
		this.getRootPane().getActionMap().put("E", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				object.rotationZ(Math.PI/100);
				object.setColor(object.getColor());
				repaint();
			}
		});
		this.getRootPane().getActionMap().put("Q", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				object.rotationY(-Math.PI/100);
				object.setColor(object.getColor());
				repaint();
			}
		});
		this.getRootPane().getActionMap().put("S", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				object.rotationX(-Math.PI/100);
				object.setColor(object.getColor());
				repaint();
			}
		});
		this.getRootPane().getActionMap().put("D", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				object.rotationY(Math.PI/100);
				object.setColor(object.getColor());
				repaint();
			}
		});
		this.getRootPane().getActionMap().put("UP", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				object.getVector().add(new Point(0,-5,0));
				repaint();
			}
		});
		this.getRootPane().getActionMap().put("DOWN", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				object.getVector().add(new Point(0,5,0));
				repaint();
			}
		});
		this.getRootPane().getActionMap().put("LEFT", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				object.getVector().add(new Point(-5,0,0));
				repaint();
			}
		});
		this.getRootPane().getActionMap().put("RIGHT", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9;

			public void actionPerformed(ActionEvent e) {
				object.getVector().add(new Point(5,0,0));
				repaint();
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

	public static void main(String[] args){
		try{
			Objet3D o = new Objet3D("cube.gts",Color.YELLOW);
			new Frame(o);
		}catch(Exception e){System.out.println(e.getMessage());}
	}
}

