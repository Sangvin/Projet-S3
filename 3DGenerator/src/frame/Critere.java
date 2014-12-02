package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;

/**
 * Cette class définie le panel de séletion des critères pour une fenêtre de
 * recherche d'un fichier
 * @author Alex
 *
 */
public class Critere extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * contient tous les tag saisis
	 */
	private JList<String> tag;
	/**
	 * contient la liste des tag
	 */
	private JScrollPane jScrollPane1;
	/**
	 * contient les tag saisis par l'utilisateur
	 */
	private DefaultListModel<String> strings;
	/**
	 * Ajoute le tag saisi dans la zone de text addTag
	 */
	private JButton add;
	/**
	 * Supprime le tag sélectionné
	 */
	private JButton remove;
	/**
	 * permet de saisir un tag
	 */
	private JTextField addTag;
	/**
	 * permet de saisir un auteur comme critère de recherche
	 */
	private JTextField auteur;
	/**
	 * permet de choisir des critère de sélection
	 */
	private JSpinner parametreNombre;
	/**
	 * contient les diférents symboles de comparaison
	 */
	private List<String> symbole;
	/**
	 * choisi le nombre avec lequel s'applique le critère
	 */
	private JSpinner nombre;
	/**
	 * permet d'appliquer le critère du nombre sur les points
	 */
	private JRadioButton points;
	/**
	 * permet d'appliquer le critère du nombre sur les segments
	 */
	private JRadioButton segments;
	/**
	 * permet d'appliquer le critère du nombre sur les faces
	 */
	private JRadioButton faces;
	/**
	 * permet de saisir la date d'enregistrement du fichier
	 */
	private JFormattedTextField date;
	/**
	 * permet de saisir le type d'utilisation
	 */
	private JTextField utilisation;
	/**
	 * éxécute la recherche
	 */
	private JButton execute;
	/**
	 * permet de reset des critères
	 */
	private JButton reset;

	/**
	 * Constructeur du panel
	 */
	public Critere(){
		this.initComponents();
	}

	/**
	 * initialise tous les composants
	 */
	private void initComponents(){
		this.tag = new JList<String>();
		this.jScrollPane1 = new JScrollPane(this.tag);
		this.strings = new DefaultListModel<String>();        
		this.tag.setModel(this.strings);
		this.addTag = new JTextField("Entrer un tag à ajouter");
		this.addTag.setForeground(Color.GRAY);
		this.addTag.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				addTag.setForeground(Color.BLACK);
				addTag.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
			}	
		});
		this.addTag.setPreferredSize(new Dimension(240,20));
		this.jScrollPane1.setPreferredSize(new Dimension(this.addTag.getPreferredSize().width,this.addTag.getPreferredSize().height*4));
		this.add = new JButton("Ajouter tag");
		this.add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(addTag.getText().length() != 0){
					boolean vide = true;
					for(int i = 0; i < addTag.getText().length(); i++)
						if(addTag.getText().charAt(i) != ' ')
							vide = false;
					if(!vide)
						strings.addElement(addTag.getText());
					addTag.setForeground(Color.GRAY);
					addTag.setText("Entrer un tag à ajouter");
				}
			}
		});
		this.remove = new JButton("Supprimer tag");
		this.remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(!tag.isSelectionEmpty())
					strings.removeElement(tag.getSelectedValue());
			}
		});

		this.utilisation = new JTextField();		
		this.utilisation.setPreferredSize(new Dimension(240,20));

		this.symbole = new ArrayList<String>();
		this.symbole.add(" ");
		this.symbole.add(" = ");
		this.symbole.add(" > ");
		this.symbole.add(" < ");
		this.symbole.add(" <= ");
		this.symbole.add(" >= ");
		this.parametreNombre = new JSpinner(new SpinnerListModel(this.symbole));
		this.parametreNombre.setPreferredSize(new Dimension(40,this.parametreNombre.getPreferredSize().height));
		this.nombre = new JSpinner(new SpinnerNumberModel(0,0,99999999,1));
		this.nombre.setPreferredSize(new Dimension(80,this.nombre.getPreferredSize().height));
		ButtonGroup bg = new ButtonGroup();
		this.points = new JRadioButton("Points");
		points.setSelected(true);
		this.segments = new JRadioButton("Segments");
		this.faces = new JRadioButton("Faces");
		bg.add(this.points);
		bg.add(this.segments);
		bg.add(this.faces);

		try {
			this.date = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.date.setPreferredSize(new Dimension(75,20));
		this.auteur = new JTextField();	
		this.auteur.setPreferredSize(new Dimension(240,20));
		this.execute = new JButton("Rechercher");
		this.reset = new JButton("Reset");
		this.reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				strings.removeAllElements();
				addTag.setText("");
				utilisation.setText("");
				parametreNombre.setValue(" ");
				nombre.setValue(0);
				date.setText("");
				auteur.setText("");
			}
		});

		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		JPanel p1 = new JPanel();
		p1.setBorder(BorderFactory.createTitledBorder("Recherche par Tag"));
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		p1.add(this.jScrollPane1);
		p1.add(this.addTag);
		JPanel p1_1 = new JPanel();
		p1_1.setLayout(new FlowLayout());
		p1_1.add(this.remove);
		p1_1.add(this.add);
		p1.add(p1_1);
		this.add(p1);

		JPanel p2 = new JPanel();
		p2.setBorder(BorderFactory.createTitledBorder("Recherche par utilisation"));
		p2.add(this.utilisation);
		this.add(p2);

		JPanel p3 = new JPanel();
		p3.setBorder(BorderFactory.createTitledBorder("Recherche sur le nombre de points, segments, faces"));
		JPanel p3_1 = new JPanel();
		p3.setLayout(new FlowLayout());
		p3.add(this.parametreNombre);
		p3.add(this.nombre);
		p3_1.setLayout(new BoxLayout(p3_1,BoxLayout.Y_AXIS));
		p3_1.add(this.points);
		p3_1.add(this.segments);
		p3_1.add(this.faces);
		p3.add(p3_1);
		this.add(p3);

		JPanel p4 = new JPanel();
		p4.setBorder(BorderFactory.createTitledBorder("Recherche par date d'ajout"));
		p4.add(new JLabel("JJ/MM/AAAA"));
		p4.add(this.date);
		this.add(p4);

		JPanel p5 = new JPanel();
		p5.setBorder(BorderFactory.createTitledBorder("Recherche par Auteur"));
		p5.add(this.auteur);
		this.add(p5);

		JPanel p6 = new JPanel();
		p6.setLayout(new FlowLayout());
		p6.setPreferredSize(new Dimension(0,this.execute.getPreferredSize().height+10));
		p6.add(this.execute);
		JPanel tmp = new JPanel();
		tmp.setPreferredSize(new Dimension(20,0));
		p6.add(tmp);
		p6.add(this.reset);
		this.add(p6);
	}
}
