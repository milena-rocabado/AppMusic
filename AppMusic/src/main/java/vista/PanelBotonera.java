package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import controlador.AppMusic;
import modelo.Cancion;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class PanelBotonera extends JPanel {

	private JButton prevBtn;
	private JButton playBtn;
	private JButton pauseBtn;
	private JButton nxtBtn;
	private JTable table;
	private List<Cancion> lista;
	private int numCancionActual=-1;
	private JTextField cancionTxt;

	/**
	 * Create the panel.
	 * 
	 * @param table
	 * @param lista
	 */
	public PanelBotonera() {
		setBackground(new Color(240, 255, 255));
		prevBtn = new JButton("");
		prevBtn.setIcon(new ImageIcon(PanelBotonera.class.getResource("/imagenes/previous-24.png")));
		prevBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (numCancionActual >= 0){
					if (numCancionActual == 0)
						numCancionActual =lista.size()-1;
					else
						numCancionActual--;
					Cancion cancion = lista.get(numCancionActual);
					cancionTxt.setVisible(true);
					cancionTxt.setText("Reproduciendo: "+ cancion.getTitulo());
					actualizarPanel();
					AppMusic.getInstancia().reproducir(cancion);
				}
			}
		});
		this.add(prevBtn);

		playBtn = new JButton("");
		playBtn.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		playBtn.setIcon(new ImageIcon(PanelBotonera.class.getResource("/imagenes/play-24.png")));
		playBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				numCancionActual = table.getSelectedRow();
				if (numCancionActual < 0)
					AppMusic.getInstancia().reanudar();
				else {
					table.clearSelection();
					Cancion cancion = lista.get(numCancionActual);
					cancionTxt.setVisible(true);
					cancionTxt.setText("Reproduciendo: "+ cancion.getTitulo());
					actualizarPanel();;
					AppMusic.getInstancia().reproducir(cancion);
				}

			}
		});
		this.add(playBtn);

		pauseBtn = new JButton("");
		pauseBtn.setIcon(new ImageIcon(PanelBotonera.class.getResource("/imagenes/pause-24.png")));
		pauseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AppMusic.getInstancia().pausar();
			}
		});
		this.add(pauseBtn);

		nxtBtn = new JButton("");
		nxtBtn.setIcon(new ImageIcon(PanelBotonera.class.getResource("/imagenes/next-24.png")));
		nxtBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (numCancionActual >= 0){
					if (numCancionActual+1 == lista.size())
						numCancionActual =0;
					else
						numCancionActual++;
					Cancion cancion = lista.get(numCancionActual);
					cancionTxt.setVisible(true);
					cancionTxt.setText("Reproduciendo: "+ cancion.getTitulo());
					actualizarPanel();
					AppMusic.getInstancia().reproducir(cancion);
				}
			}
		});
		this.add(nxtBtn);
		
		cancionTxt = new JTextField();
		cancionTxt.setToolTipText("");
		cancionTxt.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cancionTxt.setText("");
		cancionTxt.setEditable(false);
		cancionTxt.setColumns(30);
		cancionTxt.setVisible(false);
		add(cancionTxt);
	}

	public void actualizarPanelBotonera(JTable table, List<Cancion> lista) {
		this.table = table;
		this.lista = lista;
	}
	
	private void actualizarPanel() {
		this.revalidate();
		this.repaint();
	}
}
