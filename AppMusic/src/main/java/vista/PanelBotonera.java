package vista;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelBotonera extends JPanel {

	private JButton prevBtn;
	private JButton playBtn;
	private JButton pauseBtn;
	private JButton nxtBtn;
	/**
	 * Create the panel.
	 */
	public PanelBotonera() {
		setBackground(new Color(240, 255, 255));
		prevBtn = new JButton("prev");
		prevBtn.setPreferredSize(new Dimension(61, 23));
		this.add(prevBtn);
		
		playBtn = new JButton("play");
		playBtn.setPreferredSize(new Dimension(61, 23));
		this.add(playBtn);
		
		pauseBtn = new JButton("pause");
		this.add(pauseBtn);
		
		nxtBtn = new JButton("nxt");
		nxtBtn.setPreferredSize(new Dimension(61, 23));
		this.add(nxtBtn);
	}

}
