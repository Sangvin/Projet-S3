package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import mvc.ObjectController;

/**
 * Contient les différents boutons de rotation
 * @author Vincent
 *
 */
public class PanelRotation extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -126940328392104128L;
	/**
	 * Contient le controller
	 */
	private ObjectController controller;

	/**
	 * Constucteur du panel
	 * @param controller
	 */
	public PanelRotation(ObjectController controller){
		this.controller = controller;
		initComponents();
	}
	
	/**
	 * Initialise les composants
	 */
	private void initComponents(){
		JButton xpos = new JButton();
		xpos.setIcon(new ImageIcon(this.getClass().getResource("rotationXMore.png")));
		xpos.setPreferredSize(new Dimension(120,40));
		xpos.setBackground(Color.WHITE);
		xpos.addMouseListener(new MouseAdapter(){
			private Thread rotation;
			
			public void mousePressed(MouseEvent arg0){
				this.rotation = new RotationThread('X', Math.PI/100);
				this.rotation.start();
			}
			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0){
				this.rotation.interrupt();
				if(!this.rotation.isInterrupted())
					this.rotation.stop();
			}
		});
		JButton xneg = new JButton();
		xneg.setIcon(new ImageIcon(this.getClass().getResource("rotationXLess.png")));
		xneg.setPreferredSize(new Dimension(120,40));
		xneg.setBackground(Color.WHITE);
		xneg.addMouseListener(new MouseAdapter(){
			private Thread rotation;
			
			public void mousePressed(MouseEvent arg0){
				this.rotation = new RotationThread('X', -Math.PI/100);
				this.rotation.start();
			}
			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0){
				this.rotation.interrupt();
				if(!this.rotation.isInterrupted())
					this.rotation.stop();
			}
		});
		JButton ypos = new JButton();
		ypos.setIcon(new ImageIcon(this.getClass().getResource("rotationYMore.png")));
		ypos.setPreferredSize(new Dimension(120,40));
		ypos.setBackground(Color.WHITE);
		ypos.addMouseListener(new MouseAdapter(){
			private Thread rotation;
			
			public void mousePressed(MouseEvent arg0){
				this.rotation = new RotationThread('Y', Math.PI/100);
				this.rotation.start();
			}
			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0){
				this.rotation.interrupt();
				if(!this.rotation.isInterrupted())
					this.rotation.stop();
			}
		});
		JButton yneg = new JButton();
		yneg.setIcon(new ImageIcon(this.getClass().getResource("rotationYLess.png")));
		yneg.setPreferredSize(new Dimension(120,40));
		yneg.setBackground(Color.WHITE);
		yneg.addMouseListener(new MouseAdapter(){
			private Thread rotation;
			
			public void mousePressed(MouseEvent arg0){
				this.rotation = new RotationThread('Y', -Math.PI/100);
				this.rotation.start();
			}
			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0){
				this.rotation.interrupt();
				if(!this.rotation.isInterrupted())
					this.rotation.stop();
			}
		});
		JButton zpos = new JButton();
		zpos.setIcon(new ImageIcon(this.getClass().getResource("rotationZMore.png")));
		zpos.setPreferredSize(new Dimension(120,40));
		zpos.setBackground(Color.WHITE);
		zpos.addMouseListener(new MouseAdapter(){
			private Thread rotation;
			
			public void mousePressed(MouseEvent arg0){
				this.rotation = new RotationThread('Z', Math.PI/100);
				this.rotation.start();
			}
			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0){
				this.rotation.interrupt();
				if(!this.rotation.isInterrupted())
					this.rotation.stop();
			}
		});
		JButton zneg = new JButton();
		zneg.setIcon(new ImageIcon(this.getClass().getResource("rotationZLess.png")));
		zneg.setPreferredSize(new Dimension(120,40));
		zneg.setBackground(Color.WHITE);
		zneg.addMouseListener(new MouseAdapter(){
			private Thread rotation;
			
			public void mousePressed(MouseEvent arg0){
				this.rotation = new RotationThread('Z', -Math.PI/100);
				this.rotation.start();
			}
			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0){
				this.rotation.interrupt();
				if(!this.rotation.isInterrupted())
					this.rotation.stop();
			}
		});

		JPanel x = new JPanel();
		JPanel y = new JPanel();
		JPanel z = new JPanel();

		x.add(xneg);
		x.add(xpos);
		y.add(yneg);
		y.add(ypos);
		z.add(zneg);
		z.add(zpos);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(x);
		this.add(y);
		this.add(z);
	}
	
	/**
	 * Permet de continuer l'action tant qu'un bouton est appuyé 
	 * @author Vincent
	 *
	 */
	class RotationThread extends Thread{
		/**
		 * permet d'enregistrer l'axe de rotation
		 */
		private char axe;
		/**
		 * permet d'enregistrer le degré de rotation
		 */
		private double degree;

		/**
		 * Constructeur d'un thread
		 * @param axe
		 * @param degree
		 */
		public RotationThread(char axe, double degree){
			this.axe = axe;
			this.degree = degree;
		}

		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			while(true){
				if(this.axe == 'X')
					controller.rotationX(this.degree);

				if(this.axe == 'Y')
					controller.rotationY(this.degree);

				if(this.axe == 'Z')
					controller.rotationZ(this.degree);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
}