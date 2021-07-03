package vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.AppMusic;
import modelo.Cancion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelBusqueda extends JPanel {

	private JTextField tituloField;
	private JTextField interpField;
	private JComboBox<String> estiloCBox;
	BusquedaListener listener;

	/**
	 * Create the panel.
	 */
	public PanelBusqueda(BusquedaListener contenedor) {
		listener = contenedor;
		setBackground(new Color(240, 255, 255));

		tituloField = new JTextField();
		tituloField.setToolTipText("Título");
		tituloField.setPreferredSize(new Dimension(100, 20));
		add(tituloField);
		tituloField.setColumns(10);

		interpField = new JTextField();
		interpField.setToolTipText("Intérprete");
		interpField.setPreferredSize(new Dimension(100, 20));
		add(interpField);
		interpField.setColumns(10);

		estiloCBox = new JComboBox<>();
		estiloCBox.setPreferredSize(new Dimension(100, 22));
		List<String> estilos = AppMusic.getInstancia().getEstilos();
		
		estiloCBox.addItem("Estilo");
		for (String e : estilos) {
			estiloCBox.addItem(e);
		}
		add(estiloCBox);

		JButton buscarBtn = new JButton("Buscar");
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String interprete = interpField.getText();
				String titulo = tituloField.getText();
				String estilo = (String) estiloCBox.getSelectedItem();
				if (estilo.equals("Estilo"))
					estilo = "";
				List<Cancion> lista = AppMusic.getInstancia().buscarCanciones(interprete, titulo, estilo);

				listener.handleBusqueda(lista);
			}
		});
		add(buscarBtn);

	}

}
