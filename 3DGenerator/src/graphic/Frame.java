package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

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
	 * objet à afficher
	 */
	private Objet3D object;
	/**
	 * tablette ou sera dessiné la figure
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
		this.setMinimumSize(new Dimension(800, 800));
		this.pack();
		this.object.setVector(new Point(this.tablette.getWidth()/2,this.tablette.getHeight()/2,0));
		this.tablette.attachObjet3D(this.object);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setExtendedState(this.MAXIMIZED_BOTH);	
	}

	private void initComponents(){
		this.tablette = new PanelObjet();
		this.setJMenuBar(new MenuBar(this.tablette));
		this.button = new PanelBouton(this.tablette);

		this.setLayout(new GridLayout(1, 2));

		this.add(this.tablette);
		this.add(this.button);
		
		this.initKeyPad();
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

	public static void main(String[] args){
		Random r = new Random();
		Objet3D o = new Objet3D("cube.gts",new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		new Frame(o);
	}
}


