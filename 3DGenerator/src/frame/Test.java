package frame;

import java.awt.Color;
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
	private int zoom;
	private Point updateOrigine;
	
	public Test(Objet3D o){
		this.object = o;
		this.screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.zoom = 20;
		this.updateOrigine = new Point(0,0,0);
		this.setTitle("3DGenerator");
		this.initComponents();
		this.setAlwaysOnTop(true);
		this.setLocation(0,0);
		this.setSize(screen);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void initComponents(){
		this.addMouseWheelListener(new MouseWheelListener(){

			public void mouseWheelMoved(MouseWheelEvent evt) {
				if(evt.getWheelRotation() < 0){
					zoom += 10;
				}
				else{
					zoom -= 10;
				}
				repaint();
			}
		});
		
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent evt){
				if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
					updateOrigine.x += 5;
					repaint();
				}
				if(evt.getKeyCode() == KeyEvent.VK_LEFT){
					updateOrigine.x -= 5;
					repaint();
				}
				if(evt.getKeyCode() == KeyEvent.VK_UP){
					updateOrigine.y -= 5;
					repaint();
				}
				if(evt.getKeyCode() == KeyEvent.VK_DOWN){
					updateOrigine.y += 5;
					repaint();
				}
				
			}
		});
	}
	
	public void paint(Graphics g){
		g.clearRect(0, 0, screen.width, screen.height);
		this.object.calculOrigine(zoom,updateOrigine);
		Iterator<Face> it = object.getFaces().iterator();
		Face tmp;
		int[] xpoints;
		int[] ypoints;
		Polygon p;
		while(it.hasNext()){
			tmp = it.next();
			xpoints = tmp.getAllPosX(object.getOrigine(),zoom);
			ypoints = tmp.getAllPosY(object.getOrigine(),zoom);
			p = new Polygon(xpoints,ypoints,3);
			//g.setColor(Color.YELLOW);
			//g.fillPolygon(p);
			g.setColor(Color.BLACK);
			g.drawPolygon(p);
		}
	}
}
