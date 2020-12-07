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
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PanelExplora extends JPanel {
	private JTextField interpField;
	private JTextField tituloField;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel btnPanel;
	private JButton prevBtn;
	private JButton playBtn;
	private JButton pauseBtn;
	private JButton nxtBtn;

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
		GridBagConstraints gbc_interpField = new GridBagConstraints();
		gbc_interpField.gridwidth = 2;
		gbc_interpField.insets = new Insets(0, 0, 5, 5);
		gbc_interpField.fill = GridBagConstraints.HORIZONTAL;
		gbc_interpField.gridx = 1;
		gbc_interpField.gridy = 1;
		add(interpField, gbc_interpField);
		interpField.setColumns(10);
		
		tituloField = new JTextField();
		GridBagConstraints gbc_tituloField = new GridBagConstraints();
		gbc_tituloField.gridwidth = 2;
		gbc_tituloField.insets = new Insets(0, 0, 5, 5);
		gbc_tituloField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tituloField.gridx = 4;
		gbc_tituloField.gridy = 1;
		add(tituloField, gbc_tituloField);
		tituloField.setColumns(10);
		
		// habria que llamar al controlador para llenar esto
		JComboBox estiloCBox = new JComboBox();
		estiloCBox.setPreferredSize(new Dimension(100, 22));
		GridBagConstraints gbc_estiloCBox = new GridBagConstraints();
		gbc_estiloCBox.gridwidth = 2;
		gbc_estiloCBox.insets = new Insets(0, 0, 5, 5);
		gbc_estiloCBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_estiloCBox.gridx = 7;
		gbc_estiloCBox.gridy = 1;
		add(estiloCBox, gbc_estiloCBox);
		
		JButton cancelarBtn = new JButton("Cancelar");
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
				// leer lo que haya en los campos
				// llamar al controlador etc
				// crear tabla con los contenios obtenidos
				scrollPane.setVisible(true);
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Título", "Intérprete"
					}
				));
				scrollPane.setViewportView(table);
				table.setPreferredSize(new Dimension(200, 200));
				// ^ establecer en función del n de canciones que coincidan con la busqueda
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
}
