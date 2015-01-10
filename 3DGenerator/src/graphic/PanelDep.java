package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import mvc.ObjectController;
import objet.Point;

/**
 * Contient les boutons utiles aux d�placements
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
		JButton haut = new JButton();
		haut.setIcon(new ImageIcon(this.getClass().getResource("flechehaut.png")));
		haut.setPreferredSize(new Dimension(40,40));
		haut.setBackground(Color.WHITE);
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
		JButton droite = new JButton();
		droite.setIcon(new ImageIcon(this.getClass().getResource("flechedroite.png")));
		droite.setPreferredSize(new Dimension(40,40));
		droite.setBackground(Color.WHITE);
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
		JButton bas = new JButton();
		bas.setIcon(new ImageIcon(this.getClass().getResource("flechebas.png")));
		bas.setPreferredSize(new Dimension(40,40));
		bas.setBackground(Color.WHITE);
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
		JButton gauche = new JButton();
		gauche.setIcon(new ImageIcon(this.getClass().getResource("flechegauche.png")));
		gauche.setPreferredSize(new Dimension(40,40));
		gauche.setBackground(Color.WHITE);
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
		
		JPanel tmp = new JPanel();
		
		GridBagLayout bagLayout = new GridBagLayout();
		tmp.setLayout(bagLayout);
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(2,2,2,2);
		c.weightx = 1.0;
		c.weighty = 0;
		c.fill = GridBagConstraints.NORTHEAST;
		c.gridwidth = 1;
		c.gridheight = 1;
		
		c.gridx = 1;
		c.gridy = 0;
		bagLayout.setConstraints(haut, c);
		tmp.add(haut);
		
		c.gridx = 0;
		c.gridy = 1;
		bagLayout.setConstraints(gauche, c);
		tmp.add(gauche);
		c.gridx = 2;
		bagLayout.setConstraints(droite, c);
		tmp.add(droite);
		
		c.gridy = 2;
		c.gridx = 1;
		bagLayout.setConstraints(bas, c);
		tmp.add(bas);
		
		this.add(tmp);
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