package frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Iterator;

import javax.swing.JFrame;

import objet.Face;
import objet.Objet3D;
import objet.Point;

public class Test extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * objet à afficher
	 */
	private Objet3D object;
	/**
	 * dimension de la fenètre
	 */
	private Dimension screen;
	/**
	 * zoom de la figure
	 */
	private double zoom;
	
	/**
	 * construit la frame et défini les actions
	 * @param o
	 */
	public Test(Objet3D o){
		this.object = o;
		this.screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.zoom = this.object.getZoomOrigine();
		this.setTitle("3DGenerator");
		this.initComponents();
		this.setAlwaysOnTop(true);
		this.setLocation(0,0);
		this.setSize(screen);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * initialise tous les composant de la frame
	 */
	private void initComponents(){
		this.addMouseWheelListener(new MouseWheelListener(){

			public void mouseWheelMoved(MouseWheelEvent evt) {
				if(evt.getWheelRotation() < 0){
					zoom += zoom*0.03;
				}
				else{
					zoom -= zoom*0.03;
				}
				repaint();
			}
		});
		
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent evt){
				Point vector = object.getVector();
				if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
					vector.x += 5;
					object.setVector(vector);
					repaint();
				}
				if(evt.getKeyCode() == KeyEvent.VK_LEFT){
					vector.x -= 5;
					object.setVector(vector);
					repaint();
				}
				if(evt.getKeyCode() == KeyEvent.VK_UP){
					vector.y -= 5;
					object.setVector(vector);
					repaint();
				}
				if(evt.getKeyCode() == KeyEvent.VK_DOWN){
					vector.y += 5;
					object.setVector(vector);
					repaint();
				}
				
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		g.clearRect(0, 0, screen.width, screen.height);
		Iterator<Face> it = object.getFaces().iterator();
		Face tmp;
		int[] xpoints;
		int[] ypoints;
		Polygon p;
		while(it.hasNext()){
			tmp = it.next();
			xpoints = tmp.getAllPosX(zoom,object.getVector());
			ypoints = tmp.getAllPosY(zoom,object.getVector());
			p = new Polygon(xpoints,ypoints,3);
			g.setColor(tmp.getColor());
			g.fillPolygon(p);
//			g.setColor(Color.BLACK);
//			g.drawPolygon(p);
		}
	}
}
