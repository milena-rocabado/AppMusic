package vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import controlador.AppMusic;
import modelo.Cancion;
import modelo.Estilo;

@SuppressWarnings("serial")
public class PanelExplora extends JPanel implements BusquedaListener {
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel btnPanel;
	private JPanel busquedaPanel;
	
	/**
	 * Create the panel.
	 */
	public PanelExplora() {
		setPreferredSize(new Dimension(450, 300));
		setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 15, 0, 0, 15, 15, 0, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 15, 0, 0, 15, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		//
		busquedaPanel = new PanelBusqueda(this);
		GridBagConstraints gbc_busquedaPanel = new GridBagConstraints();
		gbc_busquedaPanel.gridwidth = 8;
		gbc_busquedaPanel.insets = new Insets(0, 0, 5, 5);
		gbc_busquedaPanel.fill = GridBagConstraints.BOTH;
		gbc_busquedaPanel.gridx = 1;
		gbc_busquedaPanel.gridy = 1;
		add(busquedaPanel, gbc_busquedaPanel);
		
		//panel de búsqueda fin
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		scrollPane.setVisible(false);
		
		btnPanel = new PanelBotonera();
		btnPanel.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridwidth = 6;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 4;
		add(btnPanel, gbc_panel);
		btnPanel.setVisible(false);
	}
	
	private void actualizarPanel() {
		this.revalidate();
		this.repaint();
	}
	
	private DefaultTableModel modeloTabla(List<Cancion> lista) {
		String[] titulos = {"Intérprete", "Título"};
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		String[] contenido = new String[2];
		for (Cancion c : lista) {
			contenido[0] = c.getInterprete().getNombre();//Esto no se si es asi
			contenido[1] = c.getTitulo();
			modelo.addRow(contenido);
		}
		return modelo;
	}
	
	@Override
	public void handleBusqueda(List<Cancion> busqueda) {
		scrollPane.setVisible(true);
		table = new JTable();
		
		table.setModel(modeloTabla(busqueda));
		scrollPane.setViewportView(table);
		table.setVisible(true);
		btnPanel.setVisible(true);
		actualizarPanel();
	}
}
