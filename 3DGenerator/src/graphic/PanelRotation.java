package graphic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelRotation extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -126940328392104128L;
	/**
	 * contrient le panel de dessin
	 */
	private PanelObjet tablette;

	public PanelRotation(PanelObjet tablette){
		this.tablette = tablette;
		initComponents();
	}
	
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
	
	class RotationThread extends Thread{
		private char axe;
		private double degree;

		public RotationThread(char axe, double degree){
			this.axe = axe;
			this.degree = degree;
		}

		public void run() {
			while(true){
				if(this.axe == 'X')
					tablette.rotationX(this.degree);

				if(this.axe == 'Y')
					tablette.rotationY(this.degree);

				if(this.axe == 'Z')
					tablette.rotationZ(this.degree);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
}