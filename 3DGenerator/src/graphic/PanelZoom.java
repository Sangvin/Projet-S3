package graphic;

import javax.swing.*;

public class PanelZoom extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1240814111067203130L;

	public PanelZoom(){
		
		JLabel textZoom = new JLabel("Zoom");
		JButton zoom50 = new JButton("+50");
		JButton zoom10 = new JButton("+10");
		JButton zoom1 = new JButton("+");
		JButton zoomMoins1 = new JButton("-");
		JButton zoomMoins50 = new JButton("-50");
		JButton zoomMoins10 = new JButton("-10");
		
		JPanel panelZoom = new JPanel();
		panelZoom.add(zoom50);
		panelZoom.add(zoom10);
		panelZoom.add(zoom1);
		panelZoom.add(zoomMoins1);
		panelZoom.add(zoomMoins10);
		panelZoom.add(zoomMoins50);
		
		this.setLayout(new BoxLayout(this, 1));
		this.add(textZoom);
		this.add(panelZoom);
	}
}
