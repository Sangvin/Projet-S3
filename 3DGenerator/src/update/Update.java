package update;

import graphic.Frame;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mvc.Model;
import mvc.ObjectController;

public class Update extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Permet de contenir le model de l'objet
	 */
	private Model model;
	/**
	 * Permet de contenir un controller
	 */
	private ObjectController controller;
	/**
	 * Permet de saisir les tag
	 */
	private JTextArea tag;
	/**
	 * Permet de saisir une description
	 */
	private JTextArea description;
	/**
	 * Permet de saisir une utilisation
	 */
	private JTextField utilisation;
	/**
	 * Permet de saisir une forme
	 */
	private JTextField forme;
	
	/**
	 * Constructeur de la boite de dialogue
	 * @param parent
	 * @param model
	 * @param controller
	 */
	public Update(Frame parent,Model model,ObjectController controller){
		super(parent,"Modifier l'enregistrement",true);
		this.model = model;
		this.controller = controller;
		this.initComponents();
		this.pack();
		this.setResizable(false);
		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-this.getWidth()/2);
		int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-this.getHeight()/2);
		this.setLocation(x, y);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * permet d'initialiser les composants
	 */
	private void initComponents() {
		this.tag = new JTextArea(4,15);
		this.tag.setLineWrap(true);
		this.tag.setText(model.getTags());
		JScrollPane scrollPane = new JScrollPane(this.tag);
		this.tag.setToolTipText("Entrez vos tag ici en les séparant par un espace");
		this.forme = new JTextField(15);
		this.forme.setToolTipText("Entrez ici la forme de l'objet (véhicule,personnage etc)");
		this.forme.setText(model.getForme());
		this.utilisation = new JTextField(15);
		this.utilisation.setToolTipText("Entrez ici une utilisation de l'objet");
		this.utilisation.setText(model.getUtilisation());
		this.description = new JTextArea(6,30);
		this.description.setLineWrap(true);
		this.description.setText(model.getDescription());
		JScrollPane scrollDescription = new JScrollPane(this.description);
		this.description.setToolTipText("Entrez ici une description du fichier");
		
		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				updateData();
				dispose();
			}
		});
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		JLabel titre = new JLabel();
		titre.setFont(new Font("Comic Sans MS", 0, 24));
		titre.setText("Modifier les informations de l'objet");
		
		GridBagLayout bagLayout = new GridBagLayout();
        this.setLayout(bagLayout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(4,2,4,2);	
        c.weightx = 1;
        c.weighty = 0;
        c.fill = GridBagConstraints.NORTH;
        
        c.gridwidth = 4;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        bagLayout.setConstraints(titre, c);
        this.add(titre);
        
        c.gridwidth = 1;
        c.gridy = 1;
        JLabel tagLabel = new JLabel("Tag:");
        bagLayout.setConstraints(tagLabel, c);
        this.add(tagLabel);
		c.gridx = 1;
		bagLayout.setConstraints(scrollPane, c);
		this.add(scrollPane);
		
		JLabel utilisationLabel = new JLabel("Utilisation:");
		c.gridx = 0;
		c.gridy = 2;
		bagLayout.setConstraints(utilisationLabel, c);
		this.add(utilisationLabel);
		c.gridx = 1;
		bagLayout.setConstraints(this.utilisation, c);
		this.add(this.utilisation);
		
		JLabel formeLabel = new JLabel("Forme:");
		c.gridx = 0;
		c.gridy = 3;
		bagLayout.setConstraints(formeLabel, c);
		this.add(formeLabel);
		c.gridx = 1;
		bagLayout.setConstraints(this.forme, c);
		this.add(this.forme);
		
		
		JPanel tmp = new JPanel();
		tmp.setBorder(BorderFactory.createTitledBorder("Description"));
		tmp.add(scrollDescription);
		c.gridx = 3;
		c.gridy = 1;
		c.gridheight = 3;
		bagLayout.setConstraints(tmp, c);
		this.add(tmp);
		
		JPanel tempo = new JPanel();
		tempo.add(valider);
		tempo.add(annuler);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 4;
		bagLayout.setConstraints(tempo, c);
		this.add(tempo);
	}
	
	/**
	 * permet de mettre à jour les données de l'objet
	 */
	private void updateData(){
		Connection con = null;
		try{
			controller.setForme(this.forme.getText());
			controller.setUtilisation(this.utilisation.getText());
			controller.setDescription(this.description.getText());
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:./config/bibliotheque.db");
			PreparedStatement ps = con.prepareStatement("update object set forme=?, utilisation=?, "
					+ "description=? where name='" + model.getName() + "'");
			ps.setString(1, model.getForme());
			ps.setString(2, model.getUtilisation());
			ps.setString(3, model.getDescription());
			ps.executeUpdate();
			
			controller.setTag(this.tag.getText().replaceAll("	", " ").split(" "));
			Statement stmt = con.createStatement();
			stmt.executeUpdate("delete from tag where name='" + model.getName() + "'");
			ps = con.prepareStatement("insert into tag values(?,'" + model.getName() + "')");
			System.out.println(model.getTag().size());
			for(String tmp : model.getTag()){
				ps.setString(1, tmp);
				ps.executeUpdate();
			}
		}catch(Exception e){e.printStackTrace();}
	}
}
