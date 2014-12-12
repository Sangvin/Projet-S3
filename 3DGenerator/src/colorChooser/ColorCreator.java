package colorChooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * class qui créer un créateur de couleur
 * @author Alex Dalencourt
 * @author Yoann Lameire
 */
public class ColorCreator extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * text repèrant la partie rouge du créateur
	 */
	private JLabel red;
	/**
	 * Slider pour le choix de la couleur rouge
	 */
	private JSlider sliderR;
	/**
	 * Spinner pour le choix numérique de la couleur rouge
	 */
	private JSpinner textR;
	/**
	 * text repèrant la partie verte du créateur
	 */
	private JLabel green;
	/**
	 * Slider pour le choix de la couleur verte
	 */
	private JSlider sliderG;
	/**
	 * Spinner pour le choix numérique de la couleur verte
	 */
	private JSpinner textG;	
	/**
	 * text repèrant la partie bleu du créateur
	 */
	private JLabel blue;
	/**
	 * Slider pour le choix de la couleur bleu
	 */
	private JSlider sliderB;
	/**
	 * Spinner pour le choix numérique de la couleur bleu
	 */
	private JSpinner textB;
	/**
	 * enregistre le colorChooser qui encapsule le créator
	 */
	private MyColorChooser c;
	
	/**
	 * initialise et positionne les composants, enregistre le colorChooser
	 * @param c
	 */
	public ColorCreator(MyColorChooser c){
		this.c = c;
		initComponent();
	}
	
	/**
	 * initialise et positionne les composants
	 */
	private void initComponent(){
		red = new JLabel();
    	red.setFont(new Font("Comic Sans MS", 0, 15));
    	red.setText("Rouge");
    	
    	green = new JLabel();
    	green.setFont(new Font("Comic Sans MS", 0, 15));
    	green.setText("Vert");
    	
    	blue = new JLabel();
    	blue.setFont(new Font("Comic Sans MS", 0, 15));
    	blue.setText("Bleu");
    	
    	sliderR = new JSlider(0,255);
    	sliderR.setMajorTickSpacing(50);
    	sliderR.setMinorTickSpacing(50);
    	sliderR.setPaintTicks(true);
    	sliderR.setPaintLabels(true);
    	sliderR.setValue(c.getCurentColor().getRed());

    	
    	sliderG = new JSlider(0,255);    	
    	sliderG.setMajorTickSpacing(50);
    	sliderG.setMinorTickSpacing(50);
    	sliderG.setPaintTicks(true);
    	sliderG.setPaintLabels(true);
    	sliderG.setValue(c.getCurentColor().getGreen());

    	
    	sliderB = new JSlider(0,255);
    	sliderB.setMajorTickSpacing(50);
    	sliderB.setMinorTickSpacing(50);
    	sliderB.setPaintTicks(true);
    	sliderB.setPaintLabels(true);
    	sliderB.setValue(c.getCurentColor().getBlue());

    	
    	SpinnerNumberModel model1 = new SpinnerNumberModel(0, 0, 255, 1);
        textR = new JSpinner(model1);
        textR.setValue(sliderR.getValue());

        
    	SpinnerNumberModel model2 = new SpinnerNumberModel(0, 0, 255, 1);
        textG = new JSpinner(model2);
        textG.setValue(sliderG.getValue());

        
    	SpinnerNumberModel model3 = new SpinnerNumberModel(0, 0, 255, 1);
        textB = new JSpinner(model3);
        textB.setValue(sliderB.getValue());

        
        GridBagLayout bagLayout = new GridBagLayout();
        setLayout(bagLayout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1.0;
        c.weighty = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NORTHEAST;
        
        c.gridx = 0;
        bagLayout.setConstraints(red, c);
        add(red);
        c.gridx = 1;
        bagLayout.setConstraints(sliderR, c);
        add(sliderR);
        c.gridx = 2;
        bagLayout.setConstraints(textR, c);
        add(textR);
        
        c.gridy = 1;
        c.gridx = 0;
        bagLayout.setConstraints(green, c);
        add(green);
        c.gridx = 1;
        bagLayout.setConstraints(sliderG, c);
        add(sliderG);
        c.gridx = 2;
        bagLayout.setConstraints(textG, c);
        add(textG);
        
        c.gridy = 2;
        c.gridx = 0;
        bagLayout.setConstraints(blue, c);
        add(blue);
        c.gridx = 1;
        bagLayout.setConstraints(sliderB, c);
        add(sliderB);
        c.gridx = 2;
        bagLayout.setConstraints(textB, c);
        add(textB);
	}
	
	/**
	 * permet de changer les valeurs du colorCréator pour les spinner
	 * @param c
	 */
	public void setColor(Color c){
		textR.setValue(c.getRed());
		textG.setValue(c.getGreen());
		textB.setValue(c.getBlue());
	}
	
	/**
	 * permet de changer les valeurs du colorCréator pour les Sliders
	 * @param c
	 */
	public void actualiseSlider(Color c){
		sliderR.setValue(c.getRed());
		sliderG.setValue(c.getGreen());
		sliderB.setValue(c.getBlue());
	}
}
