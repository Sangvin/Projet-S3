package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import objet.Face;
import objet.Objet3D;
import objet.Point;

public class PanelObjet extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Stock l'objet � imprimer
	 */
	private Objet3D object;
	/**
	 * pile des boutons qui sont actuellement press�s
	 */
	private List<Integer> button;
	/**
	 * position en x de la souris apr�s un clique
	 */
	private int cursorx;
	/**
	 * position en y de la souris apr�s un clique
	 */
	private int cursory;
	
	public PanelObjet(){
		this.initComponents();
		this.setVisible(true);
		this.setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/2,Toolkit.getDefaultToolkit().getScreenSize().height));
	}
	
	private void initComponents(){
		this.button = new ArrayList<Integer>();
		
		this.addMouseWheelListener(new MouseWheelListener(){

			public void mouseWheelMoved(MouseWheelEvent evt) {
				if(evt.getWheelRotation() < 0){
					object.zoom(1.05);
				}
				else{
					object.zoom(0.95);
				}
				repaint();
			}
		});

		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override

			public void mouseReleased(MouseEvent arg0) {
				button.remove((Integer) arg0.getButton());
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
					if(button.size() == 1 && button.get(0) == MouseEvent.BUTTON1){
						object.rotationX((movex*(Math.PI/6))/1000);
						object.rotationY((movey*(Math.PI/6))/1000);
						object.setColor(object.getColor());
						repaint();
					}
					if(button.size() == 1 && button.get(0) == MouseEvent.BUTTON3){
						Point vector = new Point(e.getX(),e.getY(),0);
						object.setVector(vector);
						repaint();
					}
				}
			}
		});	
	}
	
	public void paint(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.BLUE);
		g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
		g2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
		g2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
		g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
		g2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
		g2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
		for(Face tmp : this.object.getFaces()){
			g2D.setColor(tmp.getColor());
			g2D.fillPolygon(tmp.getAllPosX(this.object.getVector()),tmp.getAllPosY(this.object.getVector()),3);
		}
		g.dispose();
	}

	/**
	 * permet une rotation en x par une action externe au panel
	 * @param d
	 */
	public void rotationX(double d) {
		this.object.rotationX(d);
		this.object.setColor(this.object.getColor());
		this.repaint();
	}

	/**
	 * permet une rotation en y par une action externe au panel
	 * @param d
	 */
	public void rotationY(double d) {
		this.object.rotationY(d);
		this.object.setColor(this.object.getColor());
		this.repaint();
	}

	/**
	 * permet une rotation en z par une action externe au panel
	 * @param d
	 */
	public void rotationZ(double d) {
		this.object.rotationZ(d);
		this.object.setColor(this.object.getColor());
		this.repaint();
	}

	public void zoom(double d) {
		this.object.zoom(d);
		this.repaint();
	}
	
	public void deplacement(Point p){
		this.object.getVector().add(p);
		this.repaint();
	}

	public void attachObjet3D(Objet3D o) {
		this.object = o;
		this.repaint();
	}
}