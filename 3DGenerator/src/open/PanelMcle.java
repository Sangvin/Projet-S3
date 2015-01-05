package open;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * Cette classe contient le panel de recherche par mots clé dans la base de donnee
 * 
 * @autor Douae
 *
 */
public class PanelMcle extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Permet de saisir une liste de mots clés séparés par des espaces
	 */
	private JTextArea motCle;
	/**
	 * Permet de saisir une forme
	 */
	private JTextField forme;
	/**
	 * Permet de saisir une utilisation
	 */
	private JTextField utilisation;
	/**
	 * Contient le modèle mvc de la fenêtre
	 */
	private ModelRecherche model;
	/**
	 * Contient un controller mvc de la fenêtre
	 */
	private ControllerRecherche controller;

	/**
	 * Constructeur du panel
	 * @param model
	 * @param controller
	 */
	public PanelMcle(ModelRecherche model, ControllerRecherche controller){
		this.model = model;
		this.controller = controller;
		this.initComponents();
		this.setBorder(BorderFactory.createTitledBorder("Recherche Par mots clés"));
		this.model.addObserver(this);
	}

	/**
	 * Permet d'initialiser les composants
	 */
	private void initComponents(){
		this.motCle = new JTextArea(5,15);
		this.motCle.addKeyListener(new KeyAdapter() {		
			@Override
			public void keyTyped(KeyEvent e) {
				if(motCle.getText().replaceAll(" ", "").length() != 0)
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
						controller.setTag(motCle.getText());
			}
		});
		this.motCle.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(this.motCle);
		this.forme = new JTextField(15);
		this.forme.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e){
				if(forme.getText().replaceAll(" ", "").length() != 0)
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
						controller.setForme(forme.getText());
			}
		});
		this.utilisation = new JTextField(15);
		this.utilisation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e){
				if(utilisation.getText().replaceAll(" ", "").length() != 0)
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
						controller.setUtilisation(utilisation.getText());
			}
		});

		GridBagLayout bagLayout = new GridBagLayout();
		this.setLayout(bagLayout);
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(2,2,2,2);	
		c.weightx = 1;
		c.weighty = 0;
		c.fill = GridBagConstraints.WEST;
		c.gridwidth = 1;
		c.gridheight = 1;

		c.gridx = 0;
		c.gridy = 0;
		JLabel motLabel = new JLabel("Mots-clés:");
		bagLayout.setConstraints(motLabel, c);
		this.add(motLabel);
		c.gridx = 1;
		bagLayout.setConstraints(scroll, c);
		this.add(scroll);

		c.gridx = 0;
		c.gridy= 1;
		JLabel formeLabel = new JLabel("Forme:");
		bagLayout.setConstraints(formeLabel, c);
		this.add(formeLabel);
		c.gridx = 1;
		bagLayout.setConstraints(forme, c);
		this.add(forme);

		c.gridx = 0;
		c.gridy = 2;
		JLabel utilisationLabel = new JLabel("Utilisation:");
		bagLayout.setConstraints(utilisationLabel, c);
		this.add(utilisationLabel);
		c.gridx = 1;
		bagLayout.setConstraints(utilisation, c);
		this.add(utilisation);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		this.forme.setText(model.getForme());
		this.utilisation.setText(model.getUtilisation());
		this.motCle.setText("");
		if(model.getTag().size() != 0){
			List<String> tag = model.getTag();
			this.motCle.setText(tag.get(0));
			for(int i = 1; i < tag.size(); i++)
				this.motCle.setText(this.motCle.getText()+" "+tag.get(i));
		}
	}
	
	/**
	 * Permet de mettre à jour le modèle
	 */
	public void updateController(){
		controller.setForme(this.forme.getText());
		controller.setUtilisation(this.utilisation.getText());
		System.out.println(this.motCle.getText());
		controller.setTag(this.motCle.getText());
	}
}
