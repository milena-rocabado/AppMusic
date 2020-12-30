package vista;

import java.awt.Color;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Cancion;
import modelo.ListaCanciones;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class PanelMisListas extends JPanel {
	private JTable table;
	private ListaCanciones lista;

	/**
	 * Create the panel.
	 */
	public PanelMisListas() {
		setPreferredSize(new Dimension(450, 300));
		setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{15, 0, 15, 0};
		gridBagLayout.rowHeights = new int[]{15, 0, 0, 15, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 1;
		add(table, gbc_table);
		
		JPanel botonera = new PanelBotonera();
		GridBagConstraints gbc_botonera = new GridBagConstraints();
		gbc_botonera.insets = new Insets(0, 0, 5, 5);
		gbc_botonera.fill = GridBagConstraints.BOTH;
		gbc_botonera.gridx = 1;
		gbc_botonera.gridy = 2;
		add(botonera, gbc_botonera);
	}
	
	private DefaultTableModel actualizarTabla() {
		String[] titulos = {"Intérprete", "Título"};
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		String[] contenido = new String[2];
		for (Cancion c : lista.getCanciones()) {
			contenido[0] = c.getInterprete().getNombre();
			contenido[1] = c.getTitulo();
			modelo.addRow(contenido);
		}
		return modelo;
	}
	
	public void setModeloTabla(ListaCanciones lista) {
		this.lista = lista;
		table.setModel(actualizarTabla());
	}
}
