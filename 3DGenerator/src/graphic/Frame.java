package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import objet.Objet3D;
import objet.Point;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * objet à afficher
	 */
	private Objet3D object;
	/**
	 * tablette ou sera dessiné la figure
	 */
	private PanelObjet tablette;

	public Frame(Objet3D o){
		this.object = o;
		this.setTitle("3DGenerator");
		this.initComponents();
		this.setMinimumSize(new Dimension(800, 800));
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setExtendedState(this.MAXIMIZED_BOTH);	
	}

	private void initComponents(){
		this.setJMenuBar(new MenuBar());
		this.tablette = new PanelObjet(object);

		this.setLayout(new GridLayout(1, 2));
		
		this.add(this.tablette);
		this.add(new PanelBouton());
	}

	public static void main(String[] args){
		Objet3D o = new Objet3D("cube.gts",Color.green);
		JFrame f = new Frame(o);
	}
}


