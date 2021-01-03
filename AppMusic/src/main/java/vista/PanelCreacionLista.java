package vista;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Cancion;
import modelo.ListaCanciones;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class PanelCreacionLista extends JPanel implements BusquedaListener {

	private JTable busqueda;
	private JTable tablaLC;
	private DefaultTableModel modeloTablaLC;
	private ListaCanciones listaCanciones;
	private List<Cancion> bc;

	/**
	 * Create the panel.
	 */
	public PanelCreacionLista(ListaCanciones listaCanciones) {
		this.listaCanciones = listaCanciones;
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
		
		//panel de búsqueda
		JPanel busquedaPanel = new PanelBusqueda(this);
		GridBagConstraints gbc_busquedaPanel = new GridBagConstraints();
		gbc_busquedaPanel.gridwidth = 3;
		gbc_busquedaPanel.insets = new Insets(0, 0, 5, 0);
		gbc_busquedaPanel.fill = GridBagConstraints.BOTH;
		gbc_busquedaPanel.gridx = 0;
		gbc_busquedaPanel.gridy = 0;
		add(busquedaPanel, gbc_busquedaPanel);
		//panel de búsqueda fin
		
		JScrollPane scrollPaneBusqueda = new JScrollPane();
		scrollPaneBusqueda.setBorder(new TitledBorder(null, "B\u00FAsqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_scrollPaneBusqueda = new GridBagConstraints();
		gbc_scrollPaneBusqueda.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneBusqueda.gridheight = 4;
		gbc_scrollPaneBusqueda.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneBusqueda.gridx = 0;
		gbc_scrollPaneBusqueda.gridy = 2;
		add(scrollPaneBusqueda, gbc_scrollPaneBusqueda);
		
		busqueda = new JTable();
		busqueda.setModel(inicializarTabla(new LinkedList<Cancion>())); // para que salgan los headers
		scrollPaneBusqueda.setViewportView(busqueda);
		
		JScrollPane scrollPaneCanciones = new JScrollPane();
		scrollPaneCanciones.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Lista", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPaneCanciones.setPreferredSize(new Dimension(100, 225));
		GridBagConstraints gbc_scrollPaneCanciones = new GridBagConstraints();
		gbc_scrollPaneCanciones.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneCanciones.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneCanciones.gridheight = 4;
		gbc_scrollPaneCanciones.gridx = 2;
		gbc_scrollPaneCanciones.gridy = 2;
		add(scrollPaneCanciones, gbc_scrollPaneCanciones);
		
		tablaLC = new JTable();
		List<Cancion> canciones = listaCanciones.getCanciones();
		modeloTablaLC = inicializarTabla(canciones);
		tablaLC.setModel(modeloTablaLC);
		scrollPaneCanciones.setViewportView(tablaLC);
		
		JButton addBtn = new JButton(">>");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = busqueda.getSelectedRow();
				if (index > -1) {
					Cancion c = bc.get(index);
					listaCanciones.addCancion(c);
					modeloTablaLC.addRow(new String[]{c.getInterprete(), c.getTitulo()});
					busqueda.clearSelection();
				}
			}
		});
		GridBagConstraints gbc_addBtn = new GridBagConstraints();
		gbc_addBtn.insets = new Insets(0, 0, 5, 5);
		gbc_addBtn.gridx = 1;
		gbc_addBtn.gridy = 3;
		add(addBtn, gbc_addBtn);
		
		JButton rmvBtn = new JButton("<<");
		rmvBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tablaLC.getSelectedRow();
				if (index > -1) {
					Cancion c = listaCanciones.getCanciones().get(index);
					listaCanciones.removeCancion(c);
					modeloTablaLC.removeRow(tablaLC.getSelectedRow());
					busqueda.clearSelection();
				}
			}
		});
		GridBagConstraints gbc_rmvBtn = new GridBagConstraints();
		gbc_rmvBtn.insets = new Insets(0, 0, 5, 5);
		gbc_rmvBtn.gridx = 1;
		gbc_rmvBtn.gridy = 4;
		add(rmvBtn, gbc_rmvBtn);
	}

	@Override
	public void handleBusqueda(List<Cancion> busqueda) {
		bc = busqueda;
		this.busqueda.setModel(inicializarTabla(busqueda));
	}
	
	private DefaultTableModel inicializarTabla(List<Cancion> lista) {
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
}
