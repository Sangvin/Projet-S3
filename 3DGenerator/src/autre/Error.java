package autre;

import graphic.Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * Permet d'afficher une liste de message d'erreur
 * @author Alex
 *
 */
public class Error extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Contient la liste des erreurs à afficher
	 */
	private Set<FileError> errorList;
	/**
	 * contient le message à afficher
	 */
	private String message;
	/**
	 * contient la liste qui permet d'afficher les details
	 */
	private JList<String> detail;
	/**
	 * contient la scroll permetant de faire défiler les messages
	 */
	private JScrollPane scroll;
	/**
	 * contient les messages d'erreur
	 */
	private DefaultListModel<String> list;
	/**
	 * permet de cacher les details
	 */
	private JLabel less;
	/**
	 * permet d'afficher les details
	 */
	private JLabel more;
	
	/**
	 * Constructeur de la boite de dialogue
	 * @param parent
	 * @param errorList
	 * @param message
	 */
	public Error(Frame parent,Set<FileError> errorList,String message){
		super(parent,"Attention",true);
		this.errorList = errorList;
		this.message = message;
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
	 * Permet d'initialiser les composants
	 */
	private void initComponents() {
		JLabel libelle = new JLabel(this.message);
		Border tmp = BorderFactory.createTitledBorder(null, "Attention", TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION, new Font("tmp", Font.BOLD, 20));
		libelle.setBorder(tmp);
		
		this.more = new JLabel("Afficher les détails");
		this.more.setForeground(Color.BLUE);
		this.more.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				more.setVisible(false);
				scroll.setVisible(true);
				less.setVisible(true);
				pack();
			}
		});
		
		this.detail = new JList<String>();
		this.scroll = new JScrollPane(detail);
		this.scroll.setVisible(false);
		this.list = new DefaultListModel<String>();
		Iterator<FileError> it = this.errorList.iterator();
		while(it.hasNext())
			this.list.addElement(it.next().message());
		this.detail.setModel(this.list);
		
		this.less = new JLabel("Afficher moins");
		this.less.setForeground(Color.BLUE);
		this.less.setVisible(false);
		this.less.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				more.setVisible(true);
				scroll.setVisible(false);
				less.setVisible(false);
				pack();
			}
		});
		
		JButton close = new JButton("Continuer");
		close.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		GridBagLayout bagLayout = new GridBagLayout();
		this.setLayout(bagLayout);
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(10,30,10,30);
		c.weightx = 1;
		c.weighty = 0;
		c.fill = GridBagConstraints.NORTHEAST;
		c.gridwidth = 1;
		c.gridheight = 1;

		c.gridx = 0;
		c.gridy = 0;
		bagLayout.setConstraints(libelle, c);
		this.add(libelle);
		
		c.gridy = 1;
		bagLayout.setConstraints(this.more, c);
		this.add(this.more);
		
		c.gridy = 2;
		bagLayout.setConstraints(this.scroll, c);
		this.add(this.scroll);
		
		c.gridy = 3;
		bagLayout.setConstraints(this.less, c);
		this.add(this.less);
		
		c.gridy = 4;
		bagLayout.setConstraints(close, c);
		this.add(close);
	}
}
