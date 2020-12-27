package vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelNuevaLista extends JPanel {
	private JTextField nombreField;
	private PanelCreacionLista panelCLista;
	private JButton crearBtn;

	/**
	 * Create the panel.
	 */
	public PanelNuevaLista() {
		setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{15, 0, 0, 0, 15, 0};
		gridBagLayout.rowHeights = new int[]{15, 0, 0, 15, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		nombreField = new JTextField();
		GridBagConstraints gbc_nombreField = new GridBagConstraints();
		gbc_nombreField.gridwidth = 2;
		gbc_nombreField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreField.gridx = 1;
		gbc_nombreField.gridy = 1;
		add(nombreField, gbc_nombreField);
		nombreField.setColumns(10);
		
		crearBtn = new JButton("Crear");
		crearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = nombreField.getText();
				boolean existe = false;
				// ^ comprobar si hay algun otro playlist con el mismo nombre
				
				if (existe) {					
					int opt = JOptionPane.showConfirmDialog(crearBtn, "¿Deseas crear una nueva lista?", "Nueva Lista", JOptionPane.YES_NO_OPTION);
					
					if (opt == JOptionPane.YES_OPTION) {
						panelCLista = new PanelCreacionLista(nombre);
						
						panelCLista.setBackground(new Color(240, 255, 255));
						GridBagConstraints gbc_panel = new GridBagConstraints();
						gbc_panel.gridwidth = 3;
						gbc_panel.insets = new Insets(0, 0, 5, 5);
						gbc_panel.fill = GridBagConstraints.BOTH;
						gbc_panel.gridx = 1;
						gbc_panel.gridy = 2;
						add(panelCLista, gbc_panel);
						actualizarPanel();
						// manejo de creacion de playlist (llamando al controlador) lo hace el panelClista
						
					} else {
						// no hacer nada
					}
				} else {
					int opt = JOptionPane.showConfirmDialog(crearBtn, "¿Deseas crear editar la lista "+nombre+"?", "Nueva Lista", JOptionPane.YES_NO_OPTION);
					
					if (opt == JOptionPane.YES_OPTION) {
						
					}
				}
			}
		});
		GridBagConstraints gbc_crearBtn = new GridBagConstraints();
		gbc_crearBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_crearBtn.insets = new Insets(0, 0, 5, 5);
		gbc_crearBtn.gridx = 3;
		gbc_crearBtn.gridy = 1;
		add(crearBtn, gbc_crearBtn);

	}
	
	private void actualizarPanel() {
		this.revalidate();
		this.repaint();
	}

}
