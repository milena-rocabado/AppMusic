package vista;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import modelo.ListaCanciones;

@SuppressWarnings("serial")
public class PanelCreacionLista extends JPanel {
	private JTable busqueda;
	private JTextField tituloField;
	private JTextField interpField;
	private JTable canciones;
	private ListaCanciones lista;

	/**
	 * Create the panel.
	 */
	public PanelCreacionLista(ListaCanciones listaCanciones) {
		lista = listaCanciones;
		inicializarPanel();
	}
	
	public PanelCreacionLista(String nombre) {
		lista = new ListaCanciones(nombre);
		inicializarPanel();
	}
	
	private void inicializarPanel() {
		setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 15, 0, 0, 0, 0, 15, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel busquedaPanel = new JPanel();
		busquedaPanel.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_busquedaPanel = new GridBagConstraints();
		gbc_busquedaPanel.gridwidth = 3;
		gbc_busquedaPanel.insets = new Insets(0, 0, 5, 0);
		gbc_busquedaPanel.fill = GridBagConstraints.BOTH;
		gbc_busquedaPanel.gridx = 0;
		gbc_busquedaPanel.gridy = 0;
		add(busquedaPanel, gbc_busquedaPanel);
		
		tituloField = new JTextField();
		tituloField.setPreferredSize(new Dimension(100, 20));
		busquedaPanel.add(tituloField);
		tituloField.setColumns(10);
		
		interpField = new JTextField();
		interpField.setPreferredSize(new Dimension(100, 20));
		busquedaPanel.add(interpField);
		interpField.setColumns(10);
		
		
		JComboBox estiloCBox = new JComboBox();
		estiloCBox.setPreferredSize(new Dimension(100, 22));
		busquedaPanel.add(estiloCBox);
		
		JButton buscarBtn = new JButton("Buscar");
		busquedaPanel.add(buscarBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		busqueda = new JTable();
		busqueda.setPreferredSize(new Dimension(100, 225));
		scrollPane.setViewportView(busqueda);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(null, "Playlist", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setPreferredSize(new Dimension(100, 225));
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridheight = 4;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 2;
		add(scrollPane_1, gbc_scrollPane_1);
		
		canciones = new JTable();
		canciones.setPreferredSize(new Dimension(100, 225));
		scrollPane_1.setViewportView(canciones);
		
		JButton addBtn = new JButton(">>");
		GridBagConstraints gbc_addBtn = new GridBagConstraints();
		gbc_addBtn.insets = new Insets(0, 0, 5, 5);
		gbc_addBtn.gridx = 1;
		gbc_addBtn.gridy = 3;
		add(addBtn, gbc_addBtn);
		
		JButton rmvBtn = new JButton("<<");
		GridBagConstraints gbc_rmvBtn = new GridBagConstraints();
		gbc_rmvBtn.insets = new Insets(0, 0, 5, 5);
		gbc_rmvBtn.gridx = 1;
		gbc_rmvBtn.gridy = 4;
		add(rmvBtn, gbc_rmvBtn);
		
		JPanel confirmarPanel = new JPanel();
		confirmarPanel.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_confirmarPanel = new GridBagConstraints();
		gbc_confirmarPanel.gridwidth = 3;
		gbc_confirmarPanel.insets = new Insets(0, 0, 0, 5);
		gbc_confirmarPanel.fill = GridBagConstraints.BOTH;
		gbc_confirmarPanel.gridx = 0;
		gbc_confirmarPanel.gridy = 7;
		add(confirmarPanel, gbc_confirmarPanel);
		
		JButton cancelarBtn = new JButton("Cancelar");
		confirmarPanel.add(cancelarBtn);
		
		JButton aceptar = new JButton("Aceptar");
		confirmarPanel.add(aceptar);
	}

}
