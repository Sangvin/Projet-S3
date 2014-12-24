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

	private static final long serialVersionUID = 1L;
	private JLabel recherche;
	private JList<String> result;
	private JScrollPane jscroll;
	private DefaultListModel<String> res;
	private JButton ouvrir;
	private JDialog parent;
	private ObjectController controller;
	private ModelRecherche modelRecherche;
	private ControllerRecherche controllerRecherche;
	private Frame superFrame;

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
				if(!result.isSelectionEmpty())
					try {
						controller.attachObjet3D(new Objet3D(modelRecherche.getUrl(),Outils.randomColor()));
						double posx = superFrame.getTablette().getSize().getWidth()/2;
						double posy = superFrame.getTablette().getSize().getHeight()/2;
						controller.setVector(new Point(posx,posy,0));
						parent.dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
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
		
		this.ouvrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(result.getSelectedValue() != null){
					
				} else {
					String message = "Erreur aucun fichier sélectionné";
					JOptionPane.showMessageDialog(parent,message, "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
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
		/*String critere = modelRecherche.toString();
		critere = critere.replaceAll("\n", "");
		critere = critere.replaceAll("0", "");
		critere = critere.replaceAll(" ", "");
		critere = critere.replaceAll("/", "");
		if(critere.length() != 0){
			this.executeRecherche();
		}
		else
			this.initResult();*/
	}
	
	private void executeRecherche(){
		this.res.clear();
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con = null;
			try{
				String req = "select object.name from object left join tag on object.name=tag.name where";
				if(modelRecherche.getNom().replaceAll(" ", "").length() != 0)
					req += " name=?";
				if(modelRecherche.getDate().replaceAll(" ", "").length() != 0)
					req += " date=?";
				if(modelRecherche.getAuteur().replaceAll(" ", "").length() != 0)
					req += " auteur=?";
				if(modelRecherche.getUtilisation().replaceAll(" ", "").length() != 0)
					req += " utilisation=?";
				if(modelRecherche.getForme().replaceAll(" ", "").length() != 0)
					req += " forme=?";
				if(modelRecherche.getNb_points() != 0)
					req += " nb_points<=" + modelRecherche.getNb_points();
				if(modelRecherche.getNb_segments() != 0)
					req += " nb_segments<=" + modelRecherche.getNb_segments();
				if(modelRecherche.getNb_faces() != 0)
					req += " nb_faces<=" + modelRecherche.getNb_faces();
				if(modelRecherche.getTag().size() != 0){
					req += " cle in (";
					for(int i = 0; i < modelRecherche.getTag().size(); i++)
						if(i == modelRecherche.getTag().size() - 1)
							req += "? )";
						else
							req += " ? ,";
				}
				//System.out.println(req);	
			}catch(Exception e){e.printStackTrace();}
			con.close();
		}catch(Exception e){e.printStackTrace();}
	}
}
