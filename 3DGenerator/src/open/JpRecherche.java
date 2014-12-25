package open;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Cette classe contient le panel de recherche par mots clé et le panel de recherche avancée
 * 
 *  @autor Douae
 *
 */
public class JpRecherche extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private PanelMcle pmc;
	private PanelAtrCritere pac;
	private JButton valider;
	private JButton reset;
	private ModelRecherche model;
	private ControllerRecherche controller;
	
	public JpRecherche(ModelRecherche model, ControllerRecherche controller){
		this.model = model;
		this.controller = controller;
		this.initComponents();
		this.setBorder(BorderFactory.createTitledBorder("Critères de Recherche"));
	}
	
	private void initComponents(){
		ControllerRecherche pmcController = new ControllerRecherche(this.model);
		ControllerRecherche pacController = new ControllerRecherche(this.model);
        this.pmc = new PanelMcle(this.model, pmcController);
        this.pac = new PanelAtrCritere(this.model, pacController);
        
		this.valider = new JButton("Rechercher");
		this.valider.setBackground(Color.WHITE);	
		this.valider.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.valider.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pmc.updateController();
				pac.updateController();
				String res = model.toString();
				res = res.replaceAll("\n", "");
				res = res.replaceAll(" ", "");
				res = res.replaceAll("0", "");
				res = res.replaceAll("/", "");
				if(res.length() != 0);
					controller.recharger();
			}
		});
		
		this.reset = new JButton("Reset");
		this.reset.setBackground(Color.WHITE);	
		this.reset.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.reset();
			}
		});
		
		int sizex = (int) (this.valider.getPreferredSize().getWidth()+6);
		int sizey = (int) (this.valider.getPreferredSize().getHeight()+6);
		this.valider.setPreferredSize(new Dimension(sizex,sizey));
		
		sizex = (int) (this.reset.getPreferredSize().getWidth()+6);
		sizey = (int) (this.reset.getPreferredSize().getHeight()+6);
		this.reset.setPreferredSize(new Dimension(sizex,sizey));
		
		GridBagLayout bagLayout = new GridBagLayout();
		this.setLayout(bagLayout);
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty = 0;
        c.insets = new Insets(2,2,2,2);	
        c.fill = GridBagConstraints.CENTER;
        
        c.gridheight = 1;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        bagLayout.setConstraints(pmc, c);
        this.add(pmc, c);
        
        c.gridy = 1;
        bagLayout.setConstraints(pac, c);
        this.add(pac);
        
        c.insets = new Insets(15, 2, 15, 2);
        
        c.gridwidth = 1;
        c.gridy = 2;
        bagLayout.setConstraints(valider, c);
        this.add(valider);
        
        c.gridx = 1;
        bagLayout.setConstraints(reset, c);
        this.add(reset);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		pac.update(arg0, arg1);
		pmc.update(arg0, arg1);
	}
}
