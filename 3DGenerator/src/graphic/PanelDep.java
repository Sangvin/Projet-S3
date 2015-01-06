package graphic;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import mvc.ObjectController;
import objet.Point;

/**
 * Contient les boutons utiles aux déplacements
 * @author Alex
 *
 */
public class PanelDep extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5602993964742282381L;
	/**
	 * contient le controller
	 */
	private ObjectController controller;

	/**
	 * Constructeur du panel
	 * @param controller
	 */
	public PanelDep(ObjectController controller){
		this.controller = controller;
		this.initComponents();
	}

	/**
	 * Permet d'initialiser les composants
	 */
	private void initComponents(){
		JButton haut = new JButton("Haut");

		haut.addMouseListener(new MouseAdapter(){
			private Thread move;

			public void mousePressed(MouseEvent arg0) {
				this.move = new MoveThread(new Point(0,-5,0));
				this.move.start();
			}

			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0) {
				this.move.interrupt();
				if(!this.move.isInterrupted())
					this.move.stop();
			}
		});
		JButton droite = new JButton("Droite");
		droite.addMouseListener(new MouseAdapter(){
			private Thread move;

			public void mousePressed(MouseEvent arg0) {
				this.move = new MoveThread(new Point(5,0,0));
				this.move.start();
			}

			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0) {
				this.move.interrupt();
				if(!this.move.isInterrupted())
					this.move.stop();
			}
		});
		JButton bas = new JButton("Bas");
		bas.addMouseListener(new MouseAdapter(){
			private Thread move;

			public void mousePressed(MouseEvent arg0) {
				this.move = new MoveThread(new Point(0,5,0));
				this.move.start();
			}

			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0) {
				this.move.interrupt();
				if(!this.move.isInterrupted())
					this.move.stop();
			}
		});
		JButton gauche = new JButton("Gauche");
		gauche.addMouseListener(new MouseAdapter(){
			private Thread move;

			public void mousePressed(MouseEvent arg0) {
				this.move = new MoveThread(new Point(-5,0,0));
				this.move.start();
			}

			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0) {
				this.move.interrupt();
				if(!this.move.isInterrupted())
					this.move.stop();
			}
		});

		this.setLayout(new GridLayout(3, 3));
		this.add(Box.createRigidArea(null));
		this.add(haut);
		this.add(Box.createRigidArea(null));
		this.add(gauche);
		this.add(Box.createRigidArea(null));
		this.add(droite);
		this.add(Box.createRigidArea(null));
		this.add(bas);
		this.add(Box.createRigidArea(null));
	}
	
	/**
	 * Cette classe permet de continuer une action tant que un bouton est appuyer
	 * @author Vincent
	 *
	 */
	class MoveThread extends Thread{
		/**
		 * Contient le point d'origine de la figure
		 */
		private Point deplacement;

		/**
		 * Constructeur du thread
		 * @param dep
		 */
		public MoveThread(Point dep){
			this.deplacement = dep;
		}

		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			while(true){
				controller.deplacement(this.deplacement);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
}