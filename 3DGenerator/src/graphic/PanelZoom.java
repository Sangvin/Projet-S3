package graphic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mvc.ObjectController;

/**
 * Contient les boutons permettant de zoomer la figure
 * @author Vincent
 *
 */
public class PanelZoom extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1240814111067203130L;
	/**
	 * Contient le controller
	 */
	private ObjectController controller;

	/**
	 * Constructeur du panel
	 * @param controller
	 */
	public PanelZoom(ObjectController controller){
		this.controller = controller;
		initComponents();
	}
	
	/**
	 * Permet d'initialiser les composants
	 */
	public void initComponents(){
		JLabel textZoom = new JLabel("Zoom");
		
		JButton zoom50 = new JButton("+50%");
		zoom50.addMouseListener(new MouseAdapter(){
			private Thread zoom;
			
			public void mousePressed(MouseEvent arg0){
				this.zoom = new ZoomThread(1.5);
				this.zoom.start();
			}
			
			public void mouseReleased(MouseEvent arg0){
				this.zoom.interrupt();
			}
		});
		
		JButton zoom10 = new JButton("+10%");
		zoom10.addMouseListener(new MouseAdapter(){
			private Thread zoom;
			
			public void mousePressed(MouseEvent arg0){
				this.zoom = new ZoomThread(1.1);
				this.zoom.start();
			}
			
			public void mouseReleased(MouseEvent arg0){
				this.zoom.interrupt();
			}
		});
		
		JButton zoom5 = new JButton("+5%");
		zoom5.addMouseListener(new MouseAdapter(){
			private Thread zoom;
			
			public void mousePressed(MouseEvent arg0){
				this.zoom = new ZoomThread(1.05);
				this.zoom.start();
			}
			
			public void mouseReleased(MouseEvent arg0){
				this.zoom.interrupt();
			}
		});
		
		JButton zoomMoins5 = new JButton("-5%");
		zoomMoins5.addMouseListener(new MouseAdapter(){
			private Thread zoom;
			
			public void mousePressed(MouseEvent arg0){
				this.zoom = new ZoomThread(0.95);
				this.zoom.start();
			}
			
			public void mouseReleased(MouseEvent arg0){
				this.zoom.interrupt();
			}
		});
		
		JButton zoomMoins10 = new JButton("-10%");
		zoomMoins10.addMouseListener(new MouseAdapter(){
			private Thread zoom;
			
			public void mousePressed(MouseEvent arg0){
				this.zoom = new ZoomThread(0.9);
				this.zoom.start();
			}
			
			public void mouseReleased(MouseEvent arg0){
				this.zoom.interrupt();
			}
		});
		
		JButton zoomMoins50 = new JButton("-50%");
		zoomMoins50.addMouseListener(new MouseAdapter(){
			private Thread zoom;
			
			public void mousePressed(MouseEvent arg0){
				this.zoom = new ZoomThread(0.5);
				this.zoom.start();
			}
			
			public void mouseReleased(MouseEvent arg0){
				this.zoom.interrupt();
			}
		});
		
		JPanel panelZoom = new JPanel();
		panelZoom.add(zoom50);
		panelZoom.add(zoom10);
		panelZoom.add(zoom5);
		panelZoom.add(zoomMoins5);
		panelZoom.add(zoomMoins10);
		panelZoom.add(zoomMoins50);
		
		this.setLayout(new BoxLayout(this, 1));
		this.add(textZoom);
		this.add(panelZoom);
	}
	
	/**
	 * Permet de continuer une action tant qu'un bouton est appuyé
	 * @author Vincent
	 *
	 */
	class ZoomThread extends Thread{
		/**
		 * Permet d'enregistrer le pourcentage de zoom ou dézoom
		 */
		private double zoom;

		/**
		 * Constructeur du thread
		 * @param zoom
		 */
		public ZoomThread(double zoom){
			this.zoom = zoom;
		}

		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			while(true){
				controller.zoom(this.zoom);;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
}