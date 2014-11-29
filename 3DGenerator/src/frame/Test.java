package frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	 * position en x de la souris après un clique
	 */
	private int cursorx;
	/**
	 * position en y de la souris après un clique
	 */
	private int cursory;
	/**
	 * pile des boutons qui sont actuellement pressés
	 */
	private List<Integer> button;

	/**
	 * construit la frame et défini les actions
	 * @param o
	 */
	public Test(Objet3D o){
		this.object = o;
		this.screen = Toolkit.getDefaultToolkit().getScreenSize();
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
					object.zoom(0.95);
				}
				else{
					object.zoom(1.05);
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

		this.button = new ArrayList<Integer>();
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			
			public void mouseReleased(MouseEvent arg0) {
				button.remove(0);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				cursorx = arg0.getY();
				cursory = arg0.getX();
				button.add(0,arg0.getButton());
			}
		});
		this.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseMoved(MouseEvent e) {}

			@Override
			public void mouseDragged(MouseEvent e) {
				int movex = (cursorx - e.getY());
				int movey = -(cursory - e.getX());
				if(button.size() != 0){
					if(button.get(0) == MouseEvent.BUTTON1){
						object.rotationX((movex*(Math.PI/6))/1000);
						object.rotationY((movey*(Math.PI/6))/1000);
						object.setColor(object.getColor());
						repaint();
					}
					if(button.get(0) == MouseEvent.BUTTON3){
						object.rotationZ((movex*(Math.PI/3))/1000);
						repaint();
					}
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
			xpoints = tmp.getAllPosX(object.getVector());
			ypoints = tmp.getAllPosY(object.getVector());
			p = new Polygon(xpoints,ypoints,3);
			g.setColor(tmp.getColor());
			g.fillPolygon(p);
			//			g.setColor(Color.BLACK);
			//			g.drawPolygon(p);
		}
	}
}
