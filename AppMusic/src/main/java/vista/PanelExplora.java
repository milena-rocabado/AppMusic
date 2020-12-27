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

@SuppressWarnings("serial")
public class PanelExplora extends JPanel {
	private JTextField interpField;
	private JTextField tituloField;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel btnPanel;
	JComboBox<String> estiloCBox;
	
	/**
	 * Create the panel.
	 */
	public PanelExplora() {
		setPreferredSize(new Dimension(450, 300));
		setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{15, 0, 0, 15, 0, 0, 15, 15, 0, 15, 0};
		gridBagLayout.rowHeights = new int[]{15, 0, 0, 15, 0, 0, 15, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		interpField = new JTextField();
		interpField.setToolTipText("Intérprete");
		GridBagConstraints gbc_interpField = new GridBagConstraints();
		gbc_interpField.gridwidth = 2;
		gbc_interpField.insets = new Insets(0, 0, 5, 5);
		gbc_interpField.fill = GridBagConstraints.HORIZONTAL;
		gbc_interpField.gridx = 1;
		gbc_interpField.gridy = 1;
		add(interpField, gbc_interpField);
		interpField.setColumns(10);
		
		tituloField = new JTextField();
		tituloField.setToolTipText("Título");
		GridBagConstraints gbc_tituloField = new GridBagConstraints();
		gbc_tituloField.gridwidth = 2;
		gbc_tituloField.insets = new Insets(0, 0, 5, 5);
		gbc_tituloField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tituloField.gridx = 4;
		gbc_tituloField.gridy = 1;
		add(tituloField, gbc_tituloField);
		tituloField.setColumns(10);
		
		estiloCBox = new JComboBox<>();
		estiloCBox.setPreferredSize(new Dimension(100, 22));
		GridBagConstraints gbc_estiloCBox = new GridBagConstraints();
		gbc_estiloCBox.gridwidth = 2;
		gbc_estiloCBox.insets = new Insets(0, 0, 5, 5);
		gbc_estiloCBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_estiloCBox.gridx = 7;
		gbc_estiloCBox.gridy = 1;
		
		List<String> estilos = AppMusic.getInstancia().getEstilos();
		//
		estilos.add("Metal");
		estilos.add("Rock");
		estilos.add("Indie");
		estilos.add("Pop");
		//
		estiloCBox.addItem("Estilo");
		for (String e : estilos) {
			estiloCBox.addItem(e);
		}
		add(estiloCBox, gbc_estiloCBox);
		
		JButton cancelarBtn = new JButton("Cancelar");
		cancelarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// borrar la lista de canciones (?
			}
		});
		GridBagConstraints gbc_cancelarBtn = new GridBagConstraints();
		gbc_cancelarBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_cancelarBtn.gridwidth = 4;
		gbc_cancelarBtn.insets = new Insets(0, 0, 5, 5);
		gbc_cancelarBtn.gridx = 1;
		gbc_cancelarBtn.gridy = 2;
		add(cancelarBtn, gbc_cancelarBtn);
		
		JButton buscarBtn = new JButton("Buscar");
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String interprete = interpField.getText();
				String titulo = tituloField.getText();
				String estilo = (String) estiloCBox.getSelectedItem();
				if (estilo.equals("Estilo")) estilo = "";
				List<Cancion> lista = AppMusic.getInstancia().buscarCanciones(interprete, titulo, estilo);
				
				//
				lista.add(new Cancion("Hell Patrol", "Judas Priest", "Heavy Metal", 24230842));
				lista.add(new Cancion("Amnesia", "KAI", "Pop", 25330842));
				//
				
				// crear tabla con los contenios obtenidos
				scrollPane.setVisible(true);
				table = new JTable();
				
				table.setModel(modeloTabla(lista));
				scrollPane.setViewportView(table);
				table.setPreferredSize(new Dimension(200, 15*(lista.size()+1)));
				// ^ establecer en función del n de canciones que coincidan con la busqueda (? o 200
				table.setVisible(true);
				btnPanel.setVisible(true);
				actualizarPanel();
			}
		});
		GridBagConstraints gbc_buscarBtn = new GridBagConstraints();
		gbc_buscarBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_buscarBtn.gridwidth = 4;
		gbc_buscarBtn.insets = new Insets(0, 0, 5, 5);
		gbc_buscarBtn.gridx = 5;
		gbc_buscarBtn.gridy = 2;
		add(buscarBtn, gbc_buscarBtn);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);
		scrollPane.setVisible(false);
		
		btnPanel = new PanelBotonera();
		btnPanel.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridwidth = 6;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 5;
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
			contenido[0] = c.getInterprete();
			contenido[1] = c.getTitulo();
			modelo.addRow(contenido);
		}
		return modelo;
	}
}
