package launcher;

import graphic.Frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigDecimal;

import javax.swing.JDialog;

/**
 * frame qui montre la progression du chargement d'un fichier
 * @author Alex
 *
 */
public class Launcher extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * bar de chargement du launcher
	 */
	private Launch bar;

	/**
	 * construit la frame
	 */
	public Launcher(Frame f){
		super(f,"",false);
		this.setUndecorated(true);
		this.initComponent();
		this.setSize(new Dimension(600,350));
		Dimension size = this.getSize();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screen.width/2-size.width/2, screen.height/2-size.height/2);
		this.setVisible(true);
	}

	/**
	 * initialise le composant
	 */
	private void initComponent() {
		this.bar = new Launch();
		this.add(bar);
	}
	
	/**
	 * permet d'avancer la progressbar en incr�mentant de 1 pourcent
	 */
	public void setValue(){
		this.bar.setValue();
		repaint();
	}
	
	/**
	 * permet d'avancer la progressbar en lui donnant une position sp�cifique
	 * @param i
	 */
	public void setValue(int i){
		this.bar.setValue(i);
		repaint();
	}
	
	/**
	 * permet de changer le text affich�
	 * @param t
	 */
	public void setText(String t){
		this.bar.setText(t);
		repaint();
	}

	/**
	 * permet de pr�ciser une valeur � incr�menter 
	 * @param b
	 */
	public void setIncrement(BigDecimal b){
		this.bar.setIncrement(b);
	}
	
	/**
	 * incremente le poucentage d'avancement du curseur avec une valeur sp�cifi� per setIncrement
	 */
	public void increment(){
		this.bar.increment();
		repaint();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Window#dispose()
	 */
	public void dispose(){
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.dispose();
	}
	
	public static void main(String[] args) {
		new Launcher(null);
	}
}
