package colorChooser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * class qui créer une table contenant 216 couleurs différentes
 * @author Alex Dalencourt
 * @author Yoann Lameire
 */
public class ColorTable extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * tableau de panel chacun contenant une couleur differente
	 */
	private JPanel[] p;
	/**
	 * récupère le colorChooser qui encapsule la table
	 */
	private MyColorChooser c;
	
	/**
	 * initie la table et le positionnement, enregistre le colorChooser
	 * @param c
	 */
	public ColorTable(MyColorChooser c){
		this.c = c;
		initComponent();
	}
	
	/**
	 * initie la table et le positionnement
	 */
	private void initComponent(){
		ArrayList<Color> liste = new ArrayList<Color>();
		for(int i = 0; i < 256; i = i + 51)
			for(int j = 0; j < 256; j = j + 51)
				for(int k = 0; k < 256; k = k +51){
					liste.add(new Color(i,j,k));
				}
		
		class MouseListenerPanel extends MouseAdapter{
			 JPanel tmp;
			 public MouseListenerPanel(JPanel origine){
				 super();
				 tmp = origine;	
			 }
	        	
			 public void mouseClicked(MouseEvent evt) {
				 c.setColor(tmp.getBackground());
			 }
	    }
		
		p = new JPanel[liste.size()];
		for(int i = 0; i < p.length; i++){
			p[i] = new JPanel();
			p[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			p[i].setPreferredSize(new Dimension(15,15));
			p[i].setBackground(liste.get(i));
			p[i].addMouseListener(new MouseListenerPanel(p[i]));
		}
		
		GridBagLayout bagLayout = new GridBagLayout();
		setLayout(bagLayout);
		GridBagConstraints c = new GridBagConstraints();
	        
	    c.anchor = GridBagConstraints.NORTH;
	    c.weightx = 1.0;
	    c.weighty = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.fill = GridBagConstraints.NORTHEAST;
		
    	int x = 0;
    	int y = 0;
	    for(int i = 0; i < p.length; i++){
	    	c.gridx = x;
	    	c.gridy = y;
			bagLayout.setConstraints(p[i], c);
	    	add(p[i]);
	    	if(x < 35)
	    		x++;
	    	else{
	    		x = 0;
	    		y++;
	    	}
		}
	}
}
