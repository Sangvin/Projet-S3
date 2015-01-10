package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import mvc.ObjectController;

/**
 * Contient les boutons permettant de zoomer la figure
 * @author Vincent
 *
 */
public class PanelZoom extends JPanel{
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
		JButton zoom50 = new JButton();
		zoom50.setIcon(new ImageIcon(this.getClass().getResource("zoomMore50.png")));
		zoom50.setPreferredSize(new Dimension(40,40));
		zoom50.setBackground(Color.WHITE);
		zoom50.addMouseListener(new MouseAdapter(){
			private Thread zoom;
			
			public void mousePressed(MouseEvent arg0){
				this.zoom = new ZoomThread(1.5);
				this.zoom.start();
			}
			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0){
				this.zoom.interrupt();
				if(!this.zoom.isInterrupted())
					this.zoom.stop();
			}
		});
		
		JButton zoom10 = new JButton();
		zoom10.setIcon(new ImageIcon(this.getClass().getResource("zoomMore10.png")));
		zoom10.setPreferredSize(new Dimension(40,40));
		zoom10.setBackground(Color.WHITE);
		zoom10.addMouseListener(new MouseAdapter(){
			private Thread zoom;
			
			public void mousePressed(MouseEvent arg0){
				this.zoom = new ZoomThread(1.1);
				this.zoom.start();
			}
			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0){
				this.zoom.interrupt();
				if(!this.zoom.isInterrupted())
					this.zoom.stop();
			}
		});
		
		JButton zoom5 = new JButton();
		zoom5.setIcon(new ImageIcon(this.getClass().getResource("zoomMore5.png")));
		zoom5.setPreferredSize(new Dimension(40,40));
		zoom5.setBackground(Color.WHITE);
		zoom5.addMouseListener(new MouseAdapter(){
			private Thread zoom;
			
			public void mousePressed(MouseEvent arg0){
				this.zoom = new ZoomThread(1.05);
				this.zoom.start();
			}
			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0){
				this.zoom.interrupt();
				if(!this.zoom.isInterrupted())
					this.zoom.stop();
			}
		});
		
		JButton zoomMoins5 = new JButton();
		zoomMoins5.setIcon(new ImageIcon(this.getClass().getResource("zoomLess5.png")));
		zoomMoins5.setPreferredSize(new Dimension(40,40));
		zoomMoins5.setBackground(Color.WHITE);
		zoomMoins5.addMouseListener(new MouseAdapter(){
			private Thread zoom;
			
			public void mousePressed(MouseEvent arg0){
				this.zoom = new ZoomThread(0.95);
				this.zoom.start();
			}
			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0){
				this.zoom.interrupt();
				if(!this.zoom.isInterrupted())
					this.zoom.stop();
			}
		});
		
		JButton zoomMoins10 = new JButton();
		zoomMoins10.setIcon(new ImageIcon(this.getClass().getResource("zoomLess10.png")));
		zoomMoins10.setPreferredSize(new Dimension(40,40));
		zoomMoins10.setBackground(Color.WHITE);
		zoomMoins10.addMouseListener(new MouseAdapter(){
			private Thread zoom;
			
			public void mousePressed(MouseEvent arg0){
				this.zoom = new ZoomThread(0.9);
				this.zoom.start();
			}
			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0){
				this.zoom.interrupt();
				if(!this.zoom.isInterrupted())
					this.zoom.stop();
			}
		});
		
		JButton zoomMoins50 = new JButton();
		zoomMoins50.setIcon(new ImageIcon(this.getClass().getResource("zoomLess50.png")));
		zoomMoins50.setPreferredSize(new Dimension(40,40));
		zoomMoins50.setBackground(Color.WHITE);
		zoomMoins50.addMouseListener(new MouseAdapter(){
			private Thread zoom;
			
			public void mousePressed(MouseEvent arg0){
				this.zoom = new ZoomThread(0.5);
				this.zoom.start();
			}
			
			@SuppressWarnings("deprecation")
			public void mouseReleased(MouseEvent arg0){
				this.zoom.interrupt();
				if(!this.zoom.isInterrupted())
					this.zoom.stop();
			}
		});
		
		JPanel panelZoom = new JPanel();
		panelZoom.add(zoom50);
		panelZoom.add(zoom10);
		panelZoom.add(zoom5);
		panelZoom.add(zoomMoins5);
		panelZoom.add(zoomMoins10);
		panelZoom.add(zoomMoins50);
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