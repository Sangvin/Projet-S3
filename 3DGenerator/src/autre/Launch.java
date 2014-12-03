package autre;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Launch extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * poucentage de la progress bar
	 */
	private int progress;
	private int percent;
	/**
	 * contient le background
	 */
	private Image img;

	/**
	 * constructeur du launcher
	 */
	public Launch(){
		this.percent = 0;
		this.progress = 0;
		this.img = null;
	}

	/**
	 * incrémente la bare de 1%
	 */
	public void setValue() {
		if(this.percent<100)
			this.percent++;
		this.progress = (int) (225*this.percent*0.01);
	}
	
	/**
	 * change la valeur de la progress bar
	 * @param p
	 */
	public void setValue(int p){
		if(p > 100)
			p=1;
		this.progress = (int)(225*(p*0.01));
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		try{
			this.img = ImageIO.read(this.getClass().getResource("Launcher.jpg"));
		} catch(Exception e){}
		g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);
		g.setColor(Color.BLACK);
		g.drawRect(175, 245, 225, 20);
		g.fillRect(175, 245, progress, 20);
		g.dispose();
	}
}
