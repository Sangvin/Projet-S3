package frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Cette class définie la fenêtre pour ouvrir un fichier
 * @author Alex
 *
 */
public class OpenFile extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Contient la liste des résultat de la recherche
	 */
	private JList<String> resultSearch;
	/**
	 * Contient la JList
	 */
	private JScrollPane jScrollPane;
	/**
	 * Liste qui contient ts les fichiers trouvés
	 */
	private DefaultListModel<String> fichiers;
	/**
	 * Contient les différents champs de saisie
	 */
	private Critere standard;
	/**
	 * permet la saisie du non d'un fichier
	 */
	private JTextField file;
	/**
	 * permet de lancer une recherche pour un nom donné
	 */
	private JButton find;
	/**
	 * permet d'ouvrir un fichier
	 */
	private JButton open;
	
	/**
	 * Constructeur de la frame
	 */
	public OpenFile(){
		this.setTitle("Ouvrir un fichier");
		this.initComponents();
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		this.pack();
		this.setResizable(false);
		Dimension size = this.getSize();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screen.width/2-size.width/2, screen.height/2-size.height/2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * initialise les composants
	 */
	private void initComponents() {
		JPanel p0 =new JPanel();
		p0.setBorder(BorderFactory.createTitledBorder("Résultat de la recherche"));
		this.resultSearch = new JList<String>();
		this.jScrollPane = new JScrollPane(this.resultSearch);
		this.fichiers = new DefaultListModel<String>();
		this.resultSearch.setModel(this.fichiers);
		p0.add(this.jScrollPane);
		
		this.standard = new Critere();
		this.standard.setBorder(BorderFactory.createTitledBorder("Critères de recherche"));
		
		this.file = new JTextField();
		this.file.setPreferredSize(new Dimension(240,20));
		this.find = new JButton("Rechercher");
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.setBorder(BorderFactory.createTitledBorder("Rechercher un fichier"));
        p1.add(this.file);
        p1.add(find);
		
        p0.setPreferredSize(new Dimension(p1.getPreferredSize().width,this.standard.getPreferredSize().height-p1.getPreferredSize().height));
		this.jScrollPane.setPreferredSize(new Dimension(p0.getPreferredSize().width-20,p0.getPreferredSize().height-40));
        
        this.open = new JButton("Ouvrir");
		
		GridBagLayout bagLayout = new GridBagLayout();
        setLayout(bagLayout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1;
        c.weighty = 0;
        c.fill = GridBagConstraints.NORTHEAST;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        bagLayout.setConstraints(p0, c);
        this.getContentPane().add(p0);
        
        c.gridy = 1;
        bagLayout.setConstraints(p1, c);
        this.getContentPane().add(p1);
        
        c.gridheight = 2;
        c.gridx = 1;
        c.gridy = 0;
        bagLayout.setConstraints(this.standard, c);
        this.getContentPane().add(this.standard);
        
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridy = 2;
        c.gridx = 0;
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(0,20));
        bagLayout.setConstraints(p2, c);
        this.getContentPane().add(p2);
        
        c.gridy = 3;
        bagLayout.setConstraints(this.open, c);
        this.getContentPane().add(this.open);
	}
	
	public static void main(String[] args){
		new OpenFile();
	}
}
