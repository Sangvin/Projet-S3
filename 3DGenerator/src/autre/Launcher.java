package autre;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * frame qui montre la progression du chargement d'un fichier
 * @author Alex
 *
 */
public class Launcher extends JFrame{
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
	public Launcher(){
		this.setUndecorated(true);
		this.setVisible(true);
		this.setSize(new Dimension(600,350));
		Dimension size = this.getSize();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screen.width/2-size.width/2, screen.height/2-size.height/2);
		this.setAlwaysOnTop(true);
		this.initComponent();
		try{
			URL icone = this.getClass().getResource("Logo.png");
			BufferedImage tmp = ImageIO.read(icone);
			this.setIconImage(tmp);
		} catch(Exception e){}
	}

	/**
	 * initialise le composant
	 */
	private void initComponent() {
		this.bar = new Launch();
		this.add(bar);
	}
	
	/**
	 * permet d'avancer la progressbar en incrémentant de 1 pourcent
	 */
	public void setValue(){
		this.bar.setValue();
		repaint();
	}
	
	/**
	 * permet d'avancer la progressbar en lui donnant une position spécifique
	 * @param i
	 */
	public void setValue(int i){
		this.bar.setValue(i);
		repaint();
	}

	public static void main(String[] args) throws InterruptedException{
		Launcher e = new Launcher();
		for(int i = 0; i <= 100; i++){
			e.setValue();
			Thread.sleep(10);
		}
		e.dispose();
	}
}
