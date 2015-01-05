package graphic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mvc.ObjectController;

/**
 * Contient les différents boutons de rotation
 * @author Vincent
 *
 */
public class PanelRotation extends JPanel {

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
		JLabel textRotation = new JLabel("Rotation");
		JButton xpos = new JButton("x+");
		xpos.addMouseListener(new MouseAdapter(){
			private Thread rotation;
			
			public void mousePressed(MouseEvent arg0){
				this.rotation = new RotationThread('X', Math.PI/100);
				this.rotation.start();
			}
			
			public void mouseReleased(MouseEvent arg0){
				this.rotation.interrupt();
			}
		});
		JButton xneg = new JButton("x-");
		xneg.addMouseListener(new MouseAdapter(){
			private Thread rotation;
			
			public void mousePressed(MouseEvent arg0){
				this.rotation = new RotationThread('X', -Math.PI/100);
				this.rotation.start();
			}
			
			public void mouseReleased(MouseEvent arg0){
				this.rotation.interrupt();
			}
		});
		JButton ypos = new JButton("y+");
		ypos.addMouseListener(new MouseAdapter(){
			private Thread rotation;
			
			public void mousePressed(MouseEvent arg0){
				this.rotation = new RotationThread('Y', Math.PI/100);
				this.rotation.start();
			}
			
			public void mouseReleased(MouseEvent arg0){
				this.rotation.interrupt();
			}
		});
		JButton yneg = new JButton("y-");
		yneg.addMouseListener(new MouseAdapter(){
			private Thread rotation;
			
			public void mousePressed(MouseEvent arg0){
				this.rotation = new RotationThread('Y', -Math.PI/100);
				this.rotation.start();
			}
			
			public void mouseReleased(MouseEvent arg0){
				this.rotation.interrupt();
			}
		});
		JButton zpos = new JButton("z+");
		zpos.addMouseListener(new MouseAdapter(){
			private Thread rotation;
			
			public void mousePressed(MouseEvent arg0){
				this.rotation = new RotationThread('Z', Math.PI/100);
				this.rotation.start();
			}
			
			public void mouseReleased(MouseEvent arg0){
				this.rotation.interrupt();
			}
		});
		JButton zneg = new JButton("z-");
		zneg.addMouseListener(new MouseAdapter(){
			private Thread rotation;
			
			public void mousePressed(MouseEvent arg0){
				this.rotation = new RotationThread('Z', -Math.PI/100);
				this.rotation.start();
			}
			
			public void mouseReleased(MouseEvent arg0){
				this.rotation.interrupt();
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

		this.setLayout(new BoxLayout(this, 1));

		this.add(textRotation);
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