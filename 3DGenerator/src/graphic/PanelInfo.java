package graphic;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelInfo extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6457681512937973805L;
	JTextField textNom;
	JTextField textAuteur;
	JTextField textNbPoints;
	JTextField textNbSeg;
	JTextField textNbFaces;

	public PanelInfo() {
		this.setLayout(new GridLayout(5, 2, 2, 2));

		this.textNom = new JTextField();
		this.textAuteur = new JTextField();
		this.textNbPoints = new JTextField();
		this.textNbSeg = new JTextField();
		this.textNbFaces = new JTextField();

		this.add(new JLabel("Nom"));
		this.add(textNom);
		this.add(new JLabel("Auteur"));
		this.add(textAuteur);
		this.add(new JLabel("Nombre de points"));
		this.add(textNbPoints);
		this.add(new JLabel("Nombre de segments"));
		this.add(textNbSeg);
		this.add(new JLabel("Nombre de faces"));
		this.add(textNbFaces);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Stub de la méthode généré automatiquement

	}

}
