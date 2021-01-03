package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Color;

import controlador.AppMusic;

import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.eclipse.persistence.internal.core.sessions.CoreAbstractRecord;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.EventObject;
import java.awt.event.ActionEvent;

import pulsador.IEncendidoListener;
import pulsador.Luz;

public class VentanaLogin {

	private JFrame frame;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JPanel loginPanel;
	private JLabel userLabel;
	private JTextField userField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton signInButton;
	private JButton logInButton;

	private AppMusic controlador;
	private Luz luz;

	/**
	 * Create the application.
	 */
	public VentanaLogin() {
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
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frame = new JFrame("AppMusic");
		frame.getContentPane().setBackground(new Color(0, 0, 51));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		controlador = AppMusic.getInstancia();

		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(240, 255, 255));
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);

		titleLabel = new JLabel("AppMusic");
		titleLabel.setForeground(new Color(128, 128, 128));
		titleLabel.setFont(new Font("Cooper Black", Font.PLAIN, 35));
		titlePanel.add(titleLabel);

		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(240, 255, 255));
		frame.getContentPane().add(loginPanel, BorderLayout.CENTER);
		GridBagLayout gbl_loginPanel = new GridBagLayout();
		gbl_loginPanel.columnWidths = new int[] { 25, 0, 0, 25, 0 };
		gbl_loginPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 10, 0 };
		gbl_loginPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_loginPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		loginPanel.setLayout(gbl_loginPanel);

		luz = new Luz();
		GridBagConstraints gbc_luz = new GridBagConstraints();
		gbc_luz.insets = new Insets(0, 0, 5, 0);
		luz.setColor(Color.YELLOW);
		gbc_luz.gridx = 3;
		gbc_luz.gridy = 0;
		loginPanel.add(luz, gbc_luz);
		luz.addEncendidoListener(new IEncendidoListener() {
			public void enteradoCambioEncendido(EventObject e) {
				if (luz.isEncendido()) {
					File archivo = abrirArchivo();
					if (archivo !=null)
						controlador.cargarCanciones(archivo);
					luz.setEncendido(false);
				}
				
			}
		});

		userLabel = new JLabel("Usuario:");
		userLabel.setBackground(SystemColor.window);
		userLabel.setForeground(SystemColor.textInactiveText);
		userLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.anchor = GridBagConstraints.WEST;
		gbc_userLabel.insets = new Insets(0, 0, 5, 5);
		gbc_userLabel.gridx = 1;
		gbc_userLabel.gridy = 1;
		loginPanel.add(userLabel, gbc_userLabel);

		userField = new JTextField();
		GridBagConstraints gbc_userField = new GridBagConstraints();
		gbc_userField.gridwidth = 2;
		gbc_userField.insets = new Insets(0, 0, 5, 5);
		gbc_userField.fill = GridBagConstraints.HORIZONTAL;
		gbc_userField.gridx = 1;
		gbc_userField.gridy = 2;
		loginPanel.add(userField, gbc_userField);
		userField.setColumns(10);

		passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setBackground(SystemColor.textInactiveText);
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		passwordLabel.setForeground(SystemColor.textInactiveText);
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.WEST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 3;
		loginPanel.add(passwordLabel, gbc_passwordLabel);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 4;
		loginPanel.add(passwordField, gbc_passwordField);

		signInButton = new JButton("Sign In");
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaSignIn vs = new VentanaSignIn(frame);
				vs.mostrarVentana();
				frame.setVisible(false);
			}
		});

		GridBagConstraints gbc_signInButton = new GridBagConstraints();
		gbc_signInButton.anchor = GridBagConstraints.WEST;
		gbc_signInButton.insets = new Insets(0, 0, 5, 5);
		gbc_signInButton.gridx = 1;
		gbc_signInButton.gridy = 5;
		loginPanel.add(signInButton, gbc_signInButton);

		logInButton = new JButton("Log In");
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = userField.getText();
				String clave = new String(passwordField.getPassword());

				boolean loginSuccess = controlador.login(usuario, clave);
				if (loginSuccess) {
					VentanaPrincipal vp = new VentanaPrincipal();
					vp.mostrarVentana();
					frame.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(logInButton, "Usuario o contraseña incorrectos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_logInButton = new GridBagConstraints();
		gbc_logInButton.insets = new Insets(0, 0, 5, 5);
		gbc_logInButton.gridx = 2;
		gbc_logInButton.gridy = 5;
		loginPanel.add(logInButton, gbc_logInButton);
		frame.setBounds(100, 100, 410, 233);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private File abrirArchivo(){
		File abre = null;
		/** llamamos el metodo que permite cargar la ventana */
		JFileChooser file = new JFileChooser();
		file.showOpenDialog(luz);
		/** abrimos el archivo seleccionado */
		abre = file.getSelectedFile();
		return abre;// El texto se almacena en el JTextArea
	}
	
	
	/*private File abrirArchivo() {
		//String aux = "";
		//String texto = "";
		File abre = null;
		try {
			/llamamos el metodo que permite cargar la ventana 
			JFileChooser file = new JFileChooser();
			file.showOpenDialog(luz);
			// abrimos el archivo seleccionado
			abre = file.getSelectedFile();

			
			//recorremos el archivo, lo leemos para plasmarlo en el area de texto
			if (abre != null) {
				FileReader archivos = new FileReader(abre);
				BufferedReader lee = new BufferedReader(archivos);
				while ((aux = lee.readLine()) != null) {
					texto += aux + "\n";
				}
				lee.close();
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex + "" + "\nNo se ha encontrado el archivo", "ADVERTENCIA!!!",
					JOptionPane.WARNING_MESSAGE);
		}
		return abre;// El texto se almacena en el JTextArea
	}*/

}
