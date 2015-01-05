package save;

import graphic.Frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import mvc.Model;

/**
 * cette classe contient les différents panel permettant la saisie d'information pour les
 * enregistrements d'un objet 3d dans la base de donnée.
 * @author Alex
 *
 */
public class Save extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * contient le modèle à enregistrer
	 */
	private Model model;
	/**
	 * panel permettant la saisie d'infos générales
	 */
	private GeneralInformation info;
	/**
	 * panel permettant la saisie des informations de tri
	 */
	private SortInformation sorted;
	/**
	 * permet de valider la sauvegarde
	 */
	private JButton valider;
	/**
	 * permet d'annuler la sauvegarde
	 */
	private JButton annuler;

	/**
	 * constructeur de la frame
	 * @param object
	 */
	public Save(Frame parent,Model model){
		super(parent,"Enregistrer",true);
		this.model = model;
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
	 * permet de placer les composants
	 */
	private void initComponents(){
		this.info = new GeneralInformation(this.model.getObject().getFichier().getAbsolutePath());
		this.sorted = new SortInformation();

		this.valider = new JButton("Valider");
		final JDialog parent = this;
		this.valider.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0){
				if(info.getInfo() != null){

					int confirm = JOptionPane.showConfirmDialog(parent, "Confirmer l'ajout dans la base","Confirmation",
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(confirm == JOptionPane.OK_OPTION){
						String[] information = info.getInfo();
						String[][] triInformation = sorted.getInfo();
						try{
							Class.forName("org.sqlite.JDBC");
							Connection con = null;
							try{
								con = DriverManager.getConnection("jdbc:sqlite:./config/bibliotheque.db");
								PreparedStatement ps = con.prepareStatement("insert into object values(?,?,?,?,?,?,?,?,?)");
								int j = 0;
								for(; j < information.length; j++)
									ps.setString(j+1,information[j]);
								for(int i = 1; i < triInformation.length; i++){
									ps.setString(++j, triInformation[i][0]);
								}
								ps.setString(++j, model.getObject().getPoints().size()+"");
								ps.setString(++j, model.getObject().getSegments().size()+"");
								ps.setString(++j, model.getObject().getFaces().size()+"");
								ps.executeUpdate();
								if(triInformation[0] != null){
									ps = con.prepareStatement("insert into tag values(?,?)");
									for(int i = 0; i < triInformation[0].length; i++){
										ps.setString(1, triInformation[0][i]);
										ps.setString(2, information[0]);
										ps.executeUpdate();
									}
								}
								dispose();
							}catch(Exception e){e.printStackTrace();}
							con.close();
						}catch(Exception e){e.printStackTrace();}
					}
				}
				else{
					String msg = "Impossible d'effectuer l'enregistrement des informations sont manqantes";
					JOptionPane.showMessageDialog(parent,msg,"Erreur",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.annuler = new JButton("Annuler");
		this.annuler.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		GridBagLayout bagLayout = new GridBagLayout();
		this.setLayout(bagLayout);
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(5,2,5,2);
		c.weightx = 1;
		c.weighty = 0;
		c.fill = GridBagConstraints.NORTHEAST;
		c.gridwidth = 1;
		c.gridheight = 1;

		c.gridx = 0;
		c.gridy = 0;
		bagLayout.setConstraints(this.info, c);
		this.getContentPane().add(this.info);

		c.gridx = 1;
		bagLayout.setConstraints(this.sorted, c);
		this.getContentPane().add(this.sorted);

		c.gridx = 0;
		c.gridy = 1;
		bagLayout.setConstraints(this.valider, c);
		this.getContentPane().add(this.valider);

		c.gridx = 1;
		bagLayout.setConstraints(this.annuler, c);
		this.getContentPane().add(this.annuler);
	}
}
