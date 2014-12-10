package graphic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import objet.Point;

public class PanelDep extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5602993964742282381L;
	/**
	 * contrient le panel de dessin
	 */
	private PanelObjet tablette;

	public PanelDep(PanelObjet tablette){
		this.tablette = tablette;
		this.initComponents();
	}

	private void initComponents(){
		JButton haut = new JButton("Haut");

		haut.addMouseListener(new MouseAdapter(){
			private Thread move;

			public void mousePressed(MouseEvent arg0) {
				this.move = new MoveThread(new Point(0,-5,0));
				this.move.start();
			}

			public void mouseReleased(MouseEvent arg0) {
				this.move.interrupt();
			}
		});
		JButton droite = new JButton("Droite");
		droite.addMouseListener(new MouseAdapter(){
			private Thread move;

			public void mousePressed(MouseEvent arg0) {
				this.move = new MoveThread(new Point(5,0,0));
				this.move.start();
			}

			public void mouseReleased(MouseEvent arg0) {
				this.move.interrupt();
			}
		});
		JButton bas = new JButton("Bas");
		bas.addMouseListener(new MouseAdapter(){
			private Thread move;

			public void mousePressed(MouseEvent arg0) {
				this.move = new MoveThread(new Point(0,5,0));
				this.move.start();
			}

			public void mouseReleased(MouseEvent arg0) {
				this.move.interrupt();
			}
		});
		JButton gauche = new JButton("Gauche");
		gauche.addMouseListener(new MouseAdapter(){
			private Thread move;

			public void mousePressed(MouseEvent arg0) {
				this.move = new MoveThread(new Point(-5,0,0));
				this.move.start();
			}

			public void mouseReleased(MouseEvent arg0) {
				this.move.interrupt();
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
	
	class MoveThread extends Thread{
		private Point deplacement;

		public MoveThread(Point dep){
			this.deplacement = dep;
		}

		public void run() {
			while(true){
				tablette.deplacement(this.deplacement);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
}
