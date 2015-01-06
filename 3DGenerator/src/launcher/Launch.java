package launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Contient les différents composants du launcher
 * @author Alex
 *
 */
public class Launch extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * poucentage de la progress bar (de 0 à 225)
	 */
	private int progress;
	/**
	 * pourcentage de progression
	 */
	private int percent;
	/**
	 * Contient le text qui décrit l'action en cours
	 */
	private String text;
	/**
	 * contient le background
	 */
	private Image img;
	/**
	 * contient la valeur à incrémenter
	 */
	private BigDecimal increment;
	/**
	 * contient la valeur actuelle de la valeur à incrementer
	 */
	private BigDecimal posIncrement;

	/**
	 * constructeur du launcher
	 */
	public Launch(){
		this.percent = 0;
		this.progress = 0;
		JFrame out = new JFrame("outLineLauncher");
		out.setVisible(true);
		out.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		out.add(new JLabel(this.getClass().getResource("Launcher.jpg").toString()));
		out.pack();
		try{
			this.img = ImageIO.read(this.getClass().getResource("Launcher.jpg"));
		} catch(Exception e){}
		this.text = "Initialisation";
		this.posIncrement = new BigDecimal(0,MathContext.DECIMAL128);
	}

	/**
	 * incrémente la bare de 1%
	 */
	public void setValue(){
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
	
	/**
	 * permet de changer le text affiché
	 * @param t
	 */
	public void setText(String t){
		this.text = t;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);
		g.setColor(Color.BLACK);
		g.drawRect(175, 245, 225, 20);
		g.fillRect(175, 245, progress, 20);
		g.drawString(this.text, 175, 280);
		g.dispose();
	}

	/**
	 * permet de préciser une valeur à incrémenter 
	 * @param b
	 */
	public void setIncrement(BigDecimal b) {
		this.increment = b;
	}
	
	/**
	 * incremente le poucentage d'avancement du curseur avec une valeur spécifié per setIncrement
	 */
	public void increment(){
		this.posIncrement = this.posIncrement.add(this.increment);
		this.setValue(this.posIncrement.intValue());
	}
}
