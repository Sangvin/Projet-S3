package open;
import graphic.Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mvc.ObjectController;
import objet.Objet3D;
import objet.Point;
import autre.Outils;
/**
 * Cette classe  contient le panel d'affichage des resultats de la recherche
 * 
 * @autor Douae
 *
 */
public class JpResultats extends JPanel implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Contient le titre du panel
	 */
	private JLabel recherche;
	/**
	 * Contient une Jlist pour l'affichage des résultats
	 */
	private JList<String> result;
	/**
	 * Contient le conteneur de la JList
	 */
	private JScrollPane jscroll;
	/**
	 * Contient la liste des résultats
	 */
	private DefaultListModel<String> res;
	/**
	 * Permet d'ouvrir le fichier sélectionné
	 */
	private JButton ouvrir;
	/**
	 * Contient la fenêtre pricipale, elle doit être du type JDialog
	 */
	private JDialog parent;
	/**
	 * Contient un controller mvc de la fenêtre
	 */
	private ObjectController controller;
	/**
	 * Contient le modèle mvc de la fenêtre principale
	 */
	private ModelRecherche modelRecherche;
	/**
	 * Contient un controller mvc de la fenêtre de recherche
	 */
	private ControllerRecherche controllerRecherche;
	/**
	 * Contient la frame principale
	 */
	private Frame superFrame;

	/**
	 * Constructeur du panel
	 * @param superFrame
	 * @param f
	 * @param controller
	 * @param modelRecherche
	 * @param controllerRecherche
	 */
	public JpResultats(Frame superFrame,JDialog f,ObjectController controller,
			ModelRecherche modelRecherche, ControllerRecherche controllerRecherche){
		this.controller = controller;
		this.superFrame = superFrame;
		this.parent = f;
		this.modelRecherche = modelRecherche;
		this.controllerRecherche = controllerRecherche;
		this.initComponents();
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		this.modelRecherche.addObserver(this);
	}
	
	/**
	 * Permet d'initialiser les composants
	 */
	private void initComponents(){
		this.recherche = new JLabel("Résultats de la recherche:");
		this.ouvrir = new JButton("Ouvrir");
		this.ouvrir.setBackground(Color.WHITE);	
		this.ouvrir.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		int x = (int) this.ouvrir.getPreferredSize().getWidth();
		int y = (int) this.ouvrir.getPreferredSize().getHeight();
		this.ouvrir.setPreferredSize(new Dimension(x+6,y+6));
		this.ouvrir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(result.getSelectedValue() != null)
					try {
						controller.attachObjet3D(new Objet3D(modelRecherche.getUrl(),Outils.randomColor()));
						double posx = superFrame.getTablette().getSize().getWidth()/2;
						double posy = superFrame.getTablette().getSize().getHeight()/2;
						controller.setVector(new Point(posx,posy,0));
						parent.dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				else{
					String message = "Erreur aucun fichier sélectionné";
					JOptionPane.showMessageDialog(parent,message, "Erreur", JOptionPane.ERROR_MESSAGE);
				}
					
				
			}
		});
		
		this.result = new JList<String>();
		this.jscroll = new JScrollPane(this.result);
		this.res = new DefaultListModel<String>();
		this.result.setModel(this.res);
		this.jscroll.setPreferredSize(new Dimension(100,400));
		this.result.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controllerRecherche.setInformation(result.getSelectedValue());
			}
		});
		
		this.initResult();
		
		JPanel tmp1 = new JPanel();
		tmp1 .setBackground(Color.LIGHT_GRAY);
		tmp1.setPreferredSize(new Dimension(0,10));
		JPanel tmp2 = new JPanel();
		tmp2 .setBackground(Color.LIGHT_GRAY);
		tmp2.setPreferredSize(new Dimension(0,10));
		JPanel centrerOuvrir = new JPanel();
		centrerOuvrir.setBackground(Color.LIGHT_GRAY);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(recherche);
		this.add(jscroll);
		this.add(tmp1);
		centrerOuvrir.add(ouvrir);
		this.add(centrerOuvrir);
		this.add(tmp2);
	}

	/**
	 * Permet d'initialiser les résultats
	 */
	private void initResult() {
		this.res.clear();
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con = null;
			try{
				con = DriverManager.getConnection("jdbc:sqlite:./config/Bibliotheque.db");
				String req = "Select name from object";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(req);
				while(rs.next())
					this.res.addElement(rs.getString(1));
			}catch(Exception e){e.printStackTrace();}
			con.close();
		}catch(Exception e){e.printStackTrace();}
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable arg0, Object arg1) {
		String critere = modelRecherche.toString();
		critere = critere.replaceAll("\n", "");
		critere = critere.replaceAll("0", "");
		critere = critere.replaceAll(" ", "");
		critere = critere.replaceAll("/", "");
		if(critere.length() != 0){
			this.executeRecherche();
		}
		else
			this.initResult();
	}
	
	/**
	 * Permet d'exécuter une recherche
	 */
	private void executeRecherche(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con = null;
			try{
				con = DriverManager.getConnection("jdbc:sqlite:./config/bibliotheque.db");
				
				String req = "select distinct object.name from object left join tag on object.name=tag.name where 1=1";
				if(modelRecherche.getNom().replaceAll(" ", "").length() != 0)
					req += " and object.name=?";
				if(modelRecherche.getDate().replaceAll(" ", "").replaceAll("/", "").length() != 0)
					req += " and date=?";
				if(modelRecherche.getAuteur().replaceAll(" ", "").length() != 0)
					req += " and auteur=?";
				if(modelRecherche.getUtilisation().replaceAll(" ", "").length() != 0)
					req += " and utilisation=?";
				if(modelRecherche.getForme().replaceAll(" ", "").length() != 0)
					req += " and forme=?";
				if(modelRecherche.getNb_points() != 0)
					req += " and nb_points<=" + modelRecherche.getNb_points();
				if(modelRecherche.getNb_segments() != 0)
					req += " and nb_segments<=" + modelRecherche.getNb_segments();
				if(modelRecherche.getNb_faces() != 0)
					req += " and nb_faces<=" + modelRecherche.getNb_faces();
				if(modelRecherche.getTag().size() != 0){
					req += " and cle in (";
					for(int i = 0; i < modelRecherche.getTag().size(); i++)
						if(i == modelRecherche.getTag().size() - 1)
							req += "?)";
						else
							req += "?,";
				}
				System.out.println(req);
				PreparedStatement ps = con.prepareStatement(req);
				int i = 1;
				if(modelRecherche.getNom().replaceAll(" ", "").length() != 0){
					ps.setString(i, modelRecherche.getNom());
					i++;
				}
				if(modelRecherche.getDate().replaceAll(" ", "").replaceAll("/", "").length() != 0){
					ps.setString(i, modelRecherche.getDate());
					i++;
				}
				if(modelRecherche.getAuteur().replaceAll(" ", "").length() != 0){
					ps.setString(i, modelRecherche.getAuteur());
					i++;
				}
				if(modelRecherche.getUtilisation().replaceAll(" ", "").length() != 0){
					ps.setString(i, modelRecherche.getUtilisation());
					i++;
				}
				if(modelRecherche.getForme().replaceAll(" ", "").length() != 0){
					ps.setString(i, modelRecherche.getForme());
					i++;
				}
				if(modelRecherche.getTag().size() != 0){
					for(int j = 0; j < modelRecherche.getTag().size(); j++){
						ps.setString(i, modelRecherche.getTag().get(j));
						i++;
					}
				}
				ResultSet rs = ps.executeQuery();
				res.clear();
				while(rs.next()){
					res.addElement(rs.getString(1));
				}
			}catch(Exception e){e.printStackTrace();}
			con.close();
		}catch(Exception e){e.printStackTrace();}
	}
}
