package vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.AppMusic;
import modelo.Cancion;
import modelo.Estilo;
import modelo.Interprete;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
		tituloField.setPreferredSize(new Dimension(100, 20));
		add(tituloField);
		tituloField.setColumns(10);
		
		interpField = new JTextField();
		interpField.setPreferredSize(new Dimension(100, 20));
		add(interpField);
		interpField.setColumns(10);
		
		
		estiloCBox = new JComboBox<>();
		estiloCBox.setPreferredSize(new Dimension(100, 22));
		List<String> estilos = AppMusic.getInstancia().getEstilos();
		/*
		Estilo estilo1= new Estilo("Metal");
		Estilo estilo2= new Estilo("Rock");
		Estilo estilo3= new Estilo("Indie");
		Estilo estilo4= new Estilo("Pop");
		
		estilos.add(estilo1);
		estilos.add(estilo2);
		estilos.add(estilo3);
		estilos.add(estilo4);
		*/
		estilos.add("Metal");
		estilos.add("Rock");
		estilos.add("Indie");
		estilos.add("Pop");
		//
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
				if (estilo.equals("Estilo")) estilo = "";
				List<Cancion> lista = AppMusic.getInstancia().buscarCanciones(interprete, titulo, estilo);
				
				Interprete interprete1 = new Interprete("Judas Priest");
				Interprete interprete2 = new Interprete("KAI");
				/*
				Estilo estilo1= new Estilo("Metal");
				Estilo estilo4= new Estilo("Pop");
				//
				lista.add(new Cancion("Hell Patrol", interprete1, estilo1, 24230842));
				lista.add(new Cancion("Amnesia", interprete2, estilo4, 25330842));
				lista.add(new Cancion("Monster", new Interprete("EXO"), estilo4, 54542215));
				lista.add(new Cancion("XS", new Interprete("Rina Sawayama"), estilo1, 54542215));
				lista.add(new Cancion("Neon Knights", new Interprete("Black Sabbath"), estilo1, 54542215));
				lista.add(new Cancion("Ride or Die", interprete2, estilo4, 54542215));
				*/
				lista.add(new Cancion("Hell Patrol", "Judas Priest", "Heavy Metal", 24230842));
				lista.add(new Cancion("Amnesia", "KAI", "Pop", 25330842));
				//
				
				listener.handleBusqueda(lista);
			}
		});
		add(buscarBtn);


	}

}
