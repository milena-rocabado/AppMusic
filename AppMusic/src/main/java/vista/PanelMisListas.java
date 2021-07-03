package vista;

import java.awt.Color;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Cancion;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import java.awt.Dimension;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class PanelMisListas extends JPanel {
	private JTable table;
	private List<Cancion> lista;
	private JPanel botonera;

	/**
	 * Create the panel.
	 */
	public PanelMisListas() {
		setPreferredSize(new Dimension(450, 300));
		setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{15, 0, 15, 0};
		gridBagLayout.rowHeights = new int[]{15, 0, 0, 0, 0, 0, 0, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		 
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	
		table.setModel(new DefaultTableModel(null, new String[] {"Intérprete", "Título"}));
		botonera = new PanelBotonera();
		GridBagConstraints gbc_botonera = new GridBagConstraints();
		gbc_botonera.insets = new Insets(0, 0, 5, 5);
		gbc_botonera.fill = GridBagConstraints.BOTH;
		gbc_botonera.gridx = 1;
		gbc_botonera.gridy = 6;
		add(botonera, gbc_botonera);
	}
	
	private DefaultTableModel actualizarTabla() {
		String[] titulos = {"Intérprete", "Título"};
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		String[] contenido = new String[2];
		for (Cancion c : lista) {
			contenido[0] = c.getInterprete();
			contenido[1] = c.getTitulo();
			modelo.addRow(contenido);
		}
		return modelo;
	}
	
	public void setModeloTabla(List<Cancion> lista) {
		this.lista = lista;
		((PanelBotonera) botonera).actualizarPanelBotonera(table, lista);
		table.setModel(actualizarTabla());
	}
}
