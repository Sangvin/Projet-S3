package graphic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mvc.Model;

public class PanelInfoSpe extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * contient une description
	 */
	private JTextArea description;
	/**
	 * contient une liste de mot cle
	 */
	private JTextArea tag;
	/**
	 * contient l'utilisation de l'objet
	 */
	private JTextField utilisation;
	/**
	 * contient la forme de l'objet
	 */
	private JTextField forme;
	/**
	 * contient le modèle
	 */
	private Model model;
	
	public PanelInfoSpe(Model model){
		this.model = model;
		this.initComponents();
		this.model.addObserver(this);
	}
	
	private void initComponents(){
		this.description = new JTextArea(4,15);
		JScrollPane scrollDescription = new JScrollPane(this.description);
		this.description.setFocusable(false);
		this.tag = new JTextArea(4,15);
		JScrollPane scrollTag = new JScrollPane(this.tag);
		this.tag.setFocusable(false);
		this.utilisation = new JTextField(15);
		this.utilisation.setFocusable(false);
		this.forme = new JTextField(15);
		this.forme.setFocusable(false);
		
		GridBagLayout bagLayout = new GridBagLayout();
		this.setLayout(bagLayout);
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(2,2,2,2);
		c.weightx = 1;
		c.weighty = 0;
		c.fill = GridBagConstraints.NORTHEAST;
		c.gridwidth = 1;
		c.gridheight = 1;
		
		JLabel descrip = new JLabel("Description");
		c.gridx = 0;
		c.gridy = 0;
		bagLayout.setConstraints(descrip, c);
		this.add(descrip);
		c.gridx = 1;
		bagLayout.setConstraints(scrollDescription, c);
		this.add(scrollDescription);
		
		JLabel tags = new JLabel("Tags");
		c.gridx = 0;
		c.gridy = 1;
		bagLayout.setConstraints(tags, c);
		this.add(tags);
		c.gridx = 1;
		bagLayout.setConstraints(scrollTag, c);
		this.add(scrollTag);
		
		JLabel utili = new JLabel("Utilisation");
		c.gridx = 0;
		c.gridy = 2;
		bagLayout.setConstraints(utili, c);
		this.add(utili);
		c.gridx = 1;
		bagLayout.setConstraints(utilisation, c);
		this.add(this.utilisation);
		
		JLabel form = new JLabel("Forme");
		c.gridx = 0;
		c.gridy = 3;
		bagLayout.setConstraints(form, c);
		this.add(form);
		c.gridx = 1;
		bagLayout.setConstraints(forme, c);
		this.add(this.forme);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
}
