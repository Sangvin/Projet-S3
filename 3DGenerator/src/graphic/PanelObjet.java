package graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import autre.Outils;
import mvc.Model;
import mvc.ObjectController;
import objet.Face;
import objet.Point;

public class PanelObjet extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * pile des boutons qui sont actuellement pressï¿½s
	 */
	private List<Integer> button;
	/**
	 * position en x de la souris après un clique
	 */
	private int cursorx;
	/**
	 * position en y de la souris après un clique
	 */
	private int cursory;
	/**
	 * contient la couleur du background du panel
	 */
	private Color background;
	/**
	 * contient le model
	 */
	private Model model;
	/**
	 * contient le controller
	 */
	private ObjectController controller;

	public PanelObjet(Model model, ObjectController controller){
		this.model = model;
		this.controller = controller;
		this.initComponents();
		model.addObserver(this);
		//this.setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/2,Toolkit.getDefaultToolkit().getScreenSize().height));
	}

	private void initComponents(){
		this.button = new ArrayList<Integer>();
		this.background = Outils.randomColor();

		this.addMouseWheelListener(new MouseWheelListener(){

			public void mouseWheelMoved(MouseWheelEvent evt) {
				if(evt.getWheelRotation() < 0){
					controller.zoom(1.05);
				}
				else{
					controller.zoom(0.95);
				}
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
				if(button.size() != 0){
					if(button.size() == 1 && button.get(0) == MouseEvent.BUTTON1){
						int movex = (cursorx - e.getY());
						int movey = -(cursory - e.getX());
						cursorx = e.getY();
						cursory = e.getX();
						controller.rotationX(movex * Math.PI/200);
						controller.rotationY(movey * Math.PI/200);
						controller.setColor(model.getColor());
						
					}
					if(button.size() == 1 && button.get(0) == MouseEvent.BUTTON3){
						Point vector = new Point(e.getX(),e.getY(),0);
						controller.setVector(vector);
					}
				}
			}
		});	
	}

	public void paint(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(this.background);
		g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(this.model.getObject() != null){
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
			g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
			g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
			g2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
			g2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
			g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
			g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
			g2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
			g2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
			for(Face tmp : this.model.getObject().getFaces()){
				g2D.setColor(tmp.getColor());
				g2D.fillPolygon(tmp.getAllPosX(this.model.getObject().getVector()),tmp.getAllPosY(this.model.getObject().getVector()),3);
			}
		}
		g.dispose();
	}

	/**
	 * modifie le background
	 * @param c
	 */
	public void setbackground(Color c){
		this.background = c;
		this.repaint();
	}

	/**
	 * récupère le background de la figure
	 * @return
	 */
	public Color getbackground(){
		return this.background;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}
}