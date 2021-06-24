package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import modelo.Cancion;
import modelo.ListaCanciones;
import umu.tds.componente.CancionComponente;

@SuppressWarnings("serial")
public class PanelBotonera extends JPanel {

	private JButton prevBtn;
	private JButton playBtn;
	private JButton pauseBtn;
	private JButton nxtBtn;
	private JTable table;
	private List<Cancion> lista;

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
		this.add(prevBtn);

		playBtn = new JButton("play");
		playBtn.setPreferredSize(new Dimension(61, 23));
		playBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int num = table.getSelectedRow();
				if (num < 0)
					System.out.println("No hay columna seleccionada");
				else {
					System.out.println("tamaño lista"+ lista.size());
					Cancion cancion =lista.get(num);
					System.out.println("La cancion señalada es: "+cancion.getId()+cancion.getInterprete());
					System.out.println("Esta es la cancion en la fila: " + num);
					String titulo = (String) table.getValueAt(num, 1);
					System.out.println("Este es el titulo a reproducir: " + titulo);
				}

			}
		});
		this.add(playBtn);

		pauseBtn = new JButton("pause");
		this.add(pauseBtn);

		nxtBtn = new JButton("nxt");
		nxtBtn.setPreferredSize(new Dimension(61, 23));
		this.add(nxtBtn);
	}
	
	
	public void actualizarPanelBotonera(JTable table, List<Cancion> lista) {
		this.table = table;
		this.lista = lista;
	}
}
