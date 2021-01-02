package vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controlador.AppMusic;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class VentanaSignIn {

	private JFrame frame;
	private JPanel contentPane;
	private JLabel nombreLabel;
	private JLabel apellidosLabel;
	private JTextField nombreField;
	private JTextField apellidosField;
	private JLabel usuarioLabel;
	private JTextField usuarioField;
	private JLabel contrLabel;
	private JPasswordField contrField;
	private JLabel repetirLabel;
	private JPasswordField repetirField;
	private JLabel fechaLabel;
	private JDateChooser dateChooser;
	private JLabel emailLabel;
	private JTextField emailField;
	private JButton cancelarButton;
	private JButton registrarButton;
	
	private JFrame invocante;
	private AppMusic controlador;
	
	// mensajes de error
	private JLabel tituloLabel;
	private JLabel camposErrorLabel;
	private JLabel usuarioErrorLabel;
	private JLabel contrErrorLabel;

	/**
	 * Create the application.
	 */
	public VentanaSignIn(JFrame invocante) {
		this.invocante = invocante;
		initialize();
	}
	
	public void mostrarVentana() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Registro AppMusic");
		frame.setBounds(100, 100, 499, 341);
		controlador = AppMusic.getInstancia();
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (invocante != null) invocante.setVisible(true);
			}
		});
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(null);
		frame.setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{15, 0, 0, 0, 0, 15, 0};
		gbl_contentPane.rowHeights = new int[]{15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		tituloLabel = new JLabel("Crea tu cuenta AppMusic");
		tituloLabel.setForeground(SystemColor.activeCaptionBorder);
		tituloLabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
		gbc_tituloLabel.gridwidth = 4;
		gbc_tituloLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLabel.gridx = 1;
		gbc_tituloLabel.gridy = 1;
		contentPane.add(tituloLabel, gbc_tituloLabel);
		
		nombreLabel = new JLabel("Nombre:");
		nombreLabel.setForeground(SystemColor.textInactiveText);
		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
		gbc_nombreLabel.anchor = GridBagConstraints.EAST;
		gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLabel.gridx = 1;
		gbc_nombreLabel.gridy = 2;
		contentPane.add(nombreLabel, gbc_nombreLabel);
		
		nombreField = new JTextField();
		GridBagConstraints gbc_nombreField = new GridBagConstraints();
		gbc_nombreField.gridwidth = 3;
		gbc_nombreField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreField.gridx = 2;
		gbc_nombreField.gridy = 2;
		contentPane.add(nombreField, gbc_nombreField);
		nombreField.setColumns(10);
		
		apellidosLabel = new JLabel("Apellidos:");
		apellidosLabel.setForeground(SystemColor.textInactiveText);
		GridBagConstraints gbc_apellidosLabel = new GridBagConstraints();
		gbc_apellidosLabel.anchor = GridBagConstraints.EAST;
		gbc_apellidosLabel.insets = new Insets(0, 0, 5, 5);
		gbc_apellidosLabel.gridx = 1;
		gbc_apellidosLabel.gridy = 3;
		contentPane.add(apellidosLabel, gbc_apellidosLabel);
		
		apellidosField = new JTextField();
		GridBagConstraints gbc_apellidosField = new GridBagConstraints();
		gbc_apellidosField.gridwidth = 3;
		gbc_apellidosField.insets = new Insets(0, 0, 5, 5);
		gbc_apellidosField.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellidosField.gridx = 2;
		gbc_apellidosField.gridy = 3;
		contentPane.add(apellidosField, gbc_apellidosField);
		apellidosField.setColumns(10);
		
		fechaLabel = new JLabel("Fecha de nacimiento:");
		fechaLabel.setForeground(SystemColor.textInactiveText);
		GridBagConstraints gbc_fechaLabel = new GridBagConstraints();
		gbc_fechaLabel.fill = GridBagConstraints.VERTICAL;
		gbc_fechaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaLabel.gridx = 1;
		gbc_fechaLabel.gridy = 4;
		contentPane.add(fechaLabel, gbc_fechaLabel);
		
		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 3;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 4;
		contentPane.add(dateChooser, gbc_dateChooser);
		
		emailLabel = new JLabel("E-mail:");
		emailLabel.setForeground(SystemColor.textInactiveText);
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 1;
		gbc_emailLabel.gridy = 5;
		contentPane.add(emailLabel, gbc_emailLabel);
		
		emailField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 5;
		contentPane.add(emailField, gbc_textField);
		emailField.setColumns(10);
		
		usuarioLabel = new JLabel("Usuario:");
		usuarioLabel.setForeground(SystemColor.textInactiveText);
		GridBagConstraints gbc_usuarioLabel = new GridBagConstraints();
		gbc_usuarioLabel.anchor = GridBagConstraints.EAST;
		gbc_usuarioLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usuarioLabel.gridx = 1;
		gbc_usuarioLabel.gridy = 6;
		contentPane.add(usuarioLabel, gbc_usuarioLabel);
		
		usuarioField = new JTextField();
		GridBagConstraints gbc_usuarioField = new GridBagConstraints();
		gbc_usuarioField.gridwidth = 3;
		gbc_usuarioField.insets = new Insets(0, 0, 5, 5);
		gbc_usuarioField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usuarioField.gridx = 2;
		gbc_usuarioField.gridy = 6;
		contentPane.add(usuarioField, gbc_usuarioField);
		usuarioField.setColumns(10);
		
		contrLabel = new JLabel("Contraseña:");
		contrLabel.setForeground(SystemColor.textInactiveText);
		GridBagConstraints gbc_contrLabel = new GridBagConstraints();
		gbc_contrLabel.anchor = GridBagConstraints.EAST;
		gbc_contrLabel.insets = new Insets(0, 0, 5, 5);
		gbc_contrLabel.gridx = 1;
		gbc_contrLabel.gridy = 7;
		contentPane.add(contrLabel, gbc_contrLabel);
		
		contrField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 7;
		contentPane.add(contrField, gbc_passwordField);
		
		repetirLabel = new JLabel("Repetir:");
		repetirLabel.setForeground(SystemColor.textInactiveText);
		GridBagConstraints gbc_repetirLabel = new GridBagConstraints();
		gbc_repetirLabel.anchor = GridBagConstraints.EAST;
		gbc_repetirLabel.insets = new Insets(0, 0, 5, 5);
		gbc_repetirLabel.gridx = 3;
		gbc_repetirLabel.gridy = 7;
		contentPane.add(repetirLabel, gbc_repetirLabel);
		
		repetirField = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 4;
		gbc_passwordField_1.gridy = 7;
		contentPane.add(repetirField, gbc_passwordField_1);
		
		cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				invocante.setVisible(true);
			}
		});
		GridBagConstraints gbc_cancelarButton = new GridBagConstraints();
		gbc_cancelarButton.anchor = GridBagConstraints.WEST;
		gbc_cancelarButton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelarButton.gridx = 2;
		gbc_cancelarButton.gridy = 8;
		contentPane.add(cancelarButton, gbc_cancelarButton);
		
		registrarButton = new JButton("Registrar");
		registrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean ok = checkFields();
				if (ok) {
					boolean registrado = controlador.registrarUsuario(
							usuarioField.getText(),
							new String(contrField.getPassword()), 
							nombreField.getText(),
							apellidosField.getText(),
							emailField.getText(),
							toLocalDate(dateChooser.getDate()));
					if (registrado) {
						frame.dispose();
						invocante.dispose();
						VentanaPrincipal vp = new VentanaPrincipal();
						vp.mostrarVentana();
					} else {
						JOptionPane.showMessageDialog(frame, "No se ha podido realizar el registro.", "Error en el registro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		GridBagConstraints gbc_RegistrarButton = new GridBagConstraints();
		gbc_RegistrarButton.anchor = GridBagConstraints.EAST;
		gbc_RegistrarButton.insets = new Insets(0, 0, 5, 5);
		gbc_RegistrarButton.gridx = 4;
		gbc_RegistrarButton.gridy = 8;
		contentPane.add(registrarButton, gbc_RegistrarButton);
		
		camposErrorLabel = new JLabel("* Campos obligatorios");
		camposErrorLabel.setForeground(Color.RED);
		camposErrorLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_camposErrorLabel = new GridBagConstraints();
		gbc_camposErrorLabel.gridwidth = 4;
		gbc_camposErrorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_camposErrorLabel.gridx = 1;
		gbc_camposErrorLabel.gridy = 9;
		contentPane.add(camposErrorLabel, gbc_camposErrorLabel);
		
		usuarioErrorLabel = new JLabel("* Usuario ya existe");
		usuarioErrorLabel.setForeground(Color.RED);
		usuarioErrorLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_userErrorLabel = new GridBagConstraints();
		gbc_userErrorLabel.gridwidth = 4;
		gbc_userErrorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_userErrorLabel.gridx = 1;
		gbc_userErrorLabel.gridy = 10;
		contentPane.add(usuarioErrorLabel, gbc_userErrorLabel);
		
		contrErrorLabel = new JLabel("* Contraseñas no coinciden");
		contrErrorLabel.setForeground(Color.RED);
		contrErrorLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_contrErrorLabel = new GridBagConstraints();
		gbc_contrErrorLabel.gridwidth = 4;
		gbc_contrErrorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_contrErrorLabel.gridx = 1;
		gbc_contrErrorLabel.gridy = 11;
		contentPane.add(contrErrorLabel, gbc_contrErrorLabel);
		
		ocultarMensajesError();
	}
	
	private LocalDate toLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private void ocultarMensajesError() {
		usuarioErrorLabel.setVisible(false);
		contrErrorLabel.setVisible(false);
		camposErrorLabel.setVisible(false);
		
		nombreLabel.setForeground(SystemColor.textInactiveText);
		apellidosLabel.setForeground(SystemColor.textInactiveText);
		fechaLabel.setForeground(SystemColor.textInactiveText);
		emailLabel.setForeground(SystemColor.textInactiveText);
		usuarioLabel.setForeground(SystemColor.textInactiveText);
		contrLabel.setForeground(SystemColor.textInactiveText);
		repetirLabel.setForeground(SystemColor.textInactiveText);
		
	}
	
	private boolean checkFields() {
		ocultarMensajesError();
		boolean ok = true;
		
		if (nombreField.getText().isEmpty()) {
			camposErrorLabel.setVisible(true);
			nombreLabel.setForeground(Color.RED);
			ok = false;
		}
		if (apellidosField.getText().isEmpty()) { 
			camposErrorLabel.setVisible(true);
			apellidosLabel.setForeground(Color.RED);
			ok = false;
		}
		if (dateChooser.getDate() == null) {
			camposErrorLabel.setVisible(true);
			fechaLabel.setForeground(Color.RED);
			ok = false;
		}
		if (emailField.getText().isEmpty()) {
			camposErrorLabel.setVisible(true);
			emailLabel.setForeground(Color.RED);
			ok = false;
		}
		if (usuarioField.getText().isEmpty()) {
			camposErrorLabel.setVisible(true);
			usuarioLabel.setForeground(Color.RED);
			ok = false;
		}
		String contr1 = new String(contrField.getPassword());
		String contr2 = new String(repetirField.getPassword());
		if (contr1.isEmpty()) {
			camposErrorLabel.setVisible(true);
			contrLabel.setForeground(Color.RED);
			ok = false;
		}
		if (contr2.isEmpty()) {
			camposErrorLabel.setVisible(true);
			repetirLabel.setForeground(Color.RED);
			ok = false;
		}
		if (!contr1.equals(contr2)) {
			contrErrorLabel.setVisible(true);
			contrLabel.setForeground(Color.RED);
			repetirLabel.setForeground(Color.RED);
			ok = false;
		}
		/*
		if (usuario not empty && controlador.existeUsuario(usuario)) {
			usuarioErrorLabel.setVisible(true);
			usuarioLabel.setForeground(Color.RED);
			ok = false;
		}
		*/
		return ok;
	}

}
