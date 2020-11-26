package vista;


import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal {

	private JFrame frame;
	private JPanel headerPanel;
	private JLabel holaLabel;
	private JButton premiumBtn;
	private JButton logoutBtn;
	private JPanel sidebarPanel;
	private JButton descubreBtn;
	private JButton nlistaBtn;
	private JButton recienteBtn;
	private JButton listaBtn;
	private JPanel emptyPanel;
	private JPanel mainPanel;
	private PanelExplora panelExplora;
	private PanelNuevaLista panelNLista;
	
	/*
	 * SACAR DESPUES
	 * */
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal vp = new VentanaPrincipal();
					vp.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void mostrarVentana() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
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
		// ^no
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		headerPanel = new JPanel();
		headerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		headerPanel.setBackground(new Color(240, 255, 255));
		frame.getContentPane().add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
		
		holaLabel = new JLabel("Hola, ");
		holaLabel.setForeground(new Color(128, 128, 128));
		holaLabel.setIcon(null);
		holaLabel.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		headerPanel.add(holaLabel);
		
		premiumBtn = new JButton("Hazte Premium");
		headerPanel.add(premiumBtn);
		
		logoutBtn = new JButton("Log Out");
		headerPanel.add(logoutBtn);
		
		sidebarPanel = new JPanel();
		sidebarPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sidebarPanel.setBackground(new Color(240, 255, 255));
		sidebarPanel.setPreferredSize(new Dimension(200, 300));
		sidebarPanel.setMinimumSize(new Dimension(200, 300));
		frame.getContentPane().add(sidebarPanel, BorderLayout.WEST);
		GridBagLayout gbl_sidebarPanel = new GridBagLayout();
		gbl_sidebarPanel.columnWidths = new int[]{20, 0, 20, 0};
		gbl_sidebarPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 20, 0};
		gbl_sidebarPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_sidebarPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		sidebarPanel.setLayout(gbl_sidebarPanel);
		
		descubreBtn = new JButton("Descubre");
		descubreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelExplora = new PanelExplora();
				panelExplora.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				frame.getContentPane().remove(mainPanel);
				mainPanel = panelExplora;
				frame.getContentPane().add(panelExplora, BorderLayout.CENTER);
				
				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
				frame.getContentPane().validate();
				
				// Si se le da al boton cuando ya está visible el panel explora pasa algo raro
				// comprobar si el panel visible actualmente es explora(?
			}
		});
		descubreBtn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/images/antenna3d-32.png")));
		GridBagConstraints gbc_descubreBtn = new GridBagConstraints();
		gbc_descubreBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_descubreBtn.insets = new Insets(0, 0, 5, 5);
		gbc_descubreBtn.gridx = 1;
		gbc_descubreBtn.gridy = 1;
		sidebarPanel.add(descubreBtn, gbc_descubreBtn);
		
		nlistaBtn = new JButton("Nueva Lista");
		nlistaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNLista = new PanelNuevaLista();
				panelNLista.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				frame.getContentPane().remove(mainPanel);
				mainPanel = panelNLista;
				frame.getContentPane().add(panelNLista, BorderLayout.CENTER);
				
				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
				frame.getContentPane().validate();
			}
		});
		nlistaBtn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/images/link-32.png")));
		GridBagConstraints gbc_nlistaBtn = new GridBagConstraints();
		gbc_nlistaBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_nlistaBtn.insets = new Insets(0, 0, 5, 5);
		gbc_nlistaBtn.gridx = 1;
		gbc_nlistaBtn.gridy = 2;
		sidebarPanel.add(nlistaBtn, gbc_nlistaBtn);
		
		recienteBtn = new JButton("Reciente");
		recienteBtn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/images/headphones-32.png")));
		GridBagConstraints gbc_recienteBtn = new GridBagConstraints();
		gbc_recienteBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_recienteBtn.insets = new Insets(0, 0, 5, 5);
		gbc_recienteBtn.gridx = 1;
		gbc_recienteBtn.gridy = 3;
		sidebarPanel.add(recienteBtn, gbc_recienteBtn);
		
		listaBtn = new JButton("Mis Listas");
		listaBtn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/images/papers-32.png")));
		GridBagConstraints gbc_listaBtn = new GridBagConstraints();
		gbc_listaBtn.insets = new Insets(0, 0, 5, 5);
		gbc_listaBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_listaBtn.gridx = 1;
		gbc_listaBtn.gridy = 4;
		sidebarPanel.add(listaBtn, gbc_listaBtn);
		
		emptyPanel = new JPanel();
		emptyPanel.setBackground(new Color(240, 255, 255));
		emptyPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mainPanel = emptyPanel;
		
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.setBounds(100, 100, 654, 395);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
