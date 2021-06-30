package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import controlador.AppMusic;
import modelo.Cancion;
import modelo.ListaCanciones;
import umu.tds.componente.CancionComponente;
import javax.swing.JTextField;
import java.awt.Font;

@SuppressWarnings("serial")
public class PanelBotonera extends JPanel {

	private JButton prevBtn;
	private JButton playBtn;
	private JButton pauseBtn;
	private JButton nxtBtn;
	private JTable table;
	private List<Cancion> lista;
	private int numCancionActual=-1;
	private JTextField txtHola;

	/**
	 * Create the panel.
	 * 
	 * @param table
	 * @param lista
	 */
	public PanelBotonera() {
		setBackground(new Color(240, 255, 255));
		prevBtn = new JButton("prev");
		prevBtn.setPreferredSize(new Dimension(61, 23));
		prevBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (numCancionActual >= 0){
					if (numCancionActual == 0)
						numCancionActual =lista.size()-1;
					else
						numCancionActual--;
					Cancion cancion = lista.get(numCancionActual);
					txtHola.setVisible(true);
					txtHola.setText("Reproduciendo: "+ cancion.getTitulo());
					actualizarPanel();
					AppMusic.getInstancia().reproducir(cancion);
				}
			}
		});
		this.add(prevBtn);

		playBtn = new JButton("play");
		playBtn.setPreferredSize(new Dimension(61, 23));
		playBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				numCancionActual = table.getSelectedRow();
				if (numCancionActual < 0)
					AppMusic.getInstancia().reanudar();
				else {
					table.clearSelection();
					Cancion cancion = lista.get(numCancionActual);
					System.out.println("URL: "+cancion.getUrl());
					txtHola.setVisible(true);
					txtHola.setText("Reproduciendo: "+ cancion.getTitulo());
					actualizarPanel();;
					AppMusic.getInstancia().reproducir(cancion);
				}

			}
		});
		this.add(playBtn);

		pauseBtn = new JButton("pause");
		pauseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AppMusic.getInstancia().pausar();
			}
		});
		this.add(pauseBtn);

		nxtBtn = new JButton("nxt");
		nxtBtn.setPreferredSize(new Dimension(61, 23));
		nxtBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (numCancionActual >= 0){
					if (numCancionActual+1 == lista.size())
						numCancionActual =0;
					else
						numCancionActual++;
					Cancion cancion = lista.get(numCancionActual);
					txtHola.setVisible(true);
					txtHola.setText("Reproduciendo: "+ cancion.getTitulo());
					actualizarPanel();
					AppMusic.getInstancia().reproducir(cancion);
				}
			}
		});
		this.add(nxtBtn);
		
		txtHola = new JTextField();
		txtHola.setToolTipText("");
		txtHola.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtHola.setText("");
		txtHola.setEditable(false);
		txtHola.setColumns(30);
		txtHola.setVisible(false);
		add(txtHola);
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
