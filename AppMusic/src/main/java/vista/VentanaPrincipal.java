package vista;


import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.border.EtchedBorder;

import controlador.AppMusic;
import modelo.ListaCanciones;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

import com.itextpdf.text.DocumentException;

import javax.swing.event.ListSelectionEvent;

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
	private JPanel mainPanel;
	private PanelExplora panelExplora;
	private PanelNuevaLista panelNLista;
	private PanelMisListas panelMListas;
	private PanelMisListas panelReciente;
	private PanelMisListas panelMReproducidas;
	private JList<String> listas;
	private JScrollPane listaScrollPane;
	
	private JButton descargaBtn;
	private JButton estadisticaBtn;
	
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
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		headerPanel = new JPanel();
		headerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		headerPanel.setBackground(new Color(240, 255, 255));
		frame.getContentPane().add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
		
		String nombre = AppMusic.getInstancia().getUsuarioActual().getNombre();
		holaLabel = new JLabel("Hola, "+nombre);
		holaLabel.setForeground(new Color(128, 128, 128));
		holaLabel.setIcon(null);
		holaLabel.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		headerPanel.add(holaLabel);
		
		//AppMusic.getInstancia().getUsuarioActual().setPremium(true);
		if (! AppMusic.getInstancia().getUsuarioActual().isPremium()) {
			botonesBasico();
		} else botonesPremium();
		
		logoutBtn = new JButton("Log Out");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int salida = JOptionPane.showConfirmDialog(logoutBtn,"¿Seguro que quiere salir de tu cuenta?","Exit",JOptionPane.YES_NO_OPTION);
				if (salida==0) {
					VentanaLogin vs = new VentanaLogin();
					vs.mostrarVentana();
					frame.setVisible(false);
				}
			}
		});
		headerPanel.add(logoutBtn);
		
		sidebarPanel = new JPanel();
		sidebarPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sidebarPanel.setBackground(new Color(240, 255, 255));
		sidebarPanel.setPreferredSize(new Dimension(200, 300));
		sidebarPanel.setMinimumSize(new Dimension(200, 300));
		frame.getContentPane().add(sidebarPanel, BorderLayout.WEST);
		GridBagLayout gbl_sidebarPanel = new GridBagLayout();
		gbl_sidebarPanel.columnWidths = new int[]{20, 0, 20, 0};
		gbl_sidebarPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 20, 0};
		gbl_sidebarPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_sidebarPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		sidebarPanel.setLayout(gbl_sidebarPanel);
		
		creaBotonesSidebar();
		
		addBotonesSidebar();
		addListasSidebar();
		listaScrollPane.setVisible(false);
		
		panelMListas = new PanelMisListas();
		panelReciente = new PanelMisListas();
		panelReciente.setModeloTabla(AppMusic.getInstancia().getUsuarioActual().getCancionesRecientes());
		panelReciente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mainPanel = panelReciente;
		
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.setBounds(100, 100, 669, 406);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
	
	private void botonesPremium() {
		descargaBtn = new JButton("Descarga tus Listas");
		descargaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int retval = fileChooser.showSaveDialog(descargaBtn);
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					if (file == null) return;
					if (! file.getName().endsWith(".pdf"))
						file = new File(file.getParentFile(), file.getName()+".pdf");
					try {
						AppMusic.getInstancia().generarPDF(file);
					} catch (/*FileNotFoundException |*/ DocumentException | IOException e1) {
						JOptionPane.showMessageDialog(descargaBtn, "Error creando el fichero pdf", "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}				
				}
			}
		});
		estadisticaBtn = new JButton("Canciones más reproducidas");
		estadisticaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				panelMReproducidas = new PanelMisListas();
				panelMReproducidas.setModeloTabla(AppMusic.getInstancia().getCancionesMasReproducidas());
				panelMReproducidas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				frame.getContentPane().remove(mainPanel);
				mainPanel = panelMReproducidas;
				frame.getContentPane().add(panelMReproducidas, BorderLayout.CENTER);
				frame.pack();
				listaScrollPane.setVisible(false);
				actualizarVentana();
			}
		});
		
		headerPanel.add(descargaBtn);
		headerPanel.add(estadisticaBtn);
	}
	
	private void botonesBasico() {
		premiumBtn = new JButton("Hazte Premium");
		premiumBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (AppMusic.getInstancia().hacerPremium()) {
					JOptionPane.showMessageDialog(premiumBtn, "Bienvenido a AppMusic Premium");
				} else JOptionPane.showMessageDialog(descargaBtn, "Error realizando el pago", "Error", JOptionPane.ERROR_MESSAGE);
				
				headerPanel.remove(premiumBtn);
				headerPanel.remove(logoutBtn);
				botonesPremium();
				headerPanel.add(logoutBtn);
				actualizarVentana();
			}
		});
		headerPanel.add(premiumBtn);
	}

	private void creaBotonesSidebar() {
		descubreBtn = new JButton("Descubre");
		descubreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelExplora = new PanelExplora();
				panelExplora.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				frame.getContentPane().remove(mainPanel);
				mainPanel = panelExplora;
				frame.getContentPane().add(panelExplora, BorderLayout.CENTER);
				frame.pack();
				listaScrollPane.setVisible(false);
				actualizarVentana();
			}
		});
		descubreBtn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/antenna3d-32.png")));
		
		nlistaBtn = new JButton("Nueva Lista");
		nlistaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNLista = new PanelNuevaLista();
				panelNLista.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				frame.getContentPane().remove(mainPanel);
				mainPanel = panelNLista;
				frame.getContentPane().add(panelNLista, BorderLayout.CENTER);
				frame.setSize(900, 500);
				listaScrollPane.setVisible(false);
				actualizarVentana();
			}
		});
		nlistaBtn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/link-32.png")));
		
		recienteBtn = new JButton("Reciente");
		recienteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.getContentPane().remove(mainPanel);
				panelReciente.setModeloTabla(AppMusic.getInstancia().getUsuarioActual().getCancionesRecientes());
				mainPanel = panelReciente;
				frame.getContentPane().add(panelReciente, BorderLayout.CENTER);
				frame.pack();
				listaScrollPane.setVisible(false);
				actualizarVentana();
			}
		});
		recienteBtn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/headphones-32.png")));
		
		listaBtn = new JButton("Mis Listas");
		listaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarListasSidebar();
				listas.setSelectedIndex(0);
				listaScrollPane.setVisible(true);
				frame.pack();
			}
		});
		listaBtn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/papers-32.png")));
	}
	
	private void addBotonesSidebar() {
		GridBagConstraints gbc_descubreBtn = new GridBagConstraints();
		gbc_descubreBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_descubreBtn.insets = new Insets(0, 0, 5, 5);
		gbc_descubreBtn.gridx = 1;
		gbc_descubreBtn.gridy = 1;
		sidebarPanel.add(descubreBtn, gbc_descubreBtn);
		
		
		GridBagConstraints gbc_nlistaBtn = new GridBagConstraints();
		gbc_nlistaBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_nlistaBtn.insets = new Insets(0, 0, 5, 5);
		gbc_nlistaBtn.gridx = 1;
		gbc_nlistaBtn.gridy = 2;
		sidebarPanel.add(nlistaBtn, gbc_nlistaBtn);
		
		
		GridBagConstraints gbc_recienteBtn = new GridBagConstraints();
		gbc_recienteBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_recienteBtn.insets = new Insets(0, 0, 5, 5);
		gbc_recienteBtn.gridx = 1;
		gbc_recienteBtn.gridy = 3;
		sidebarPanel.add(recienteBtn, gbc_recienteBtn);
		
		GridBagConstraints gbc_listaBtn = new GridBagConstraints();
		gbc_listaBtn.insets = new Insets(0, 0, 5, 5);
		gbc_listaBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_listaBtn.gridx = 1;
		gbc_listaBtn.gridy = 4;
		sidebarPanel.add(listaBtn, gbc_listaBtn);
	}
	
	private void addListasSidebar() {
		listaScrollPane = new JScrollPane();
		GridBagConstraints gbc_listaScrollPane = new GridBagConstraints();
		gbc_listaScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_listaScrollPane.fill = GridBagConstraints.BOTH;
		gbc_listaScrollPane.gridx = 1;
		gbc_listaScrollPane.gridy = 5;
		sidebarPanel.add(listaScrollPane, gbc_listaScrollPane);
		
		listas = new JList<>();
		listas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListaCanciones lc = AppMusic.getInstancia().getListaCanciones(listas.getSelectedValue());
				if (lc != null) panelMListas.setModeloTabla(lc.getCanciones());
				panelMListas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				frame.getContentPane().remove(mainPanel);
				mainPanel = panelMListas;
				frame.getContentPane().add(panelMListas, BorderLayout.CENTER);
				frame.pack();
				actualizarVentana();
			}
		});
		listaScrollPane.setViewportView(listas);
		frame.pack();
	}
	
	private void actualizarListasSidebar() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		for (ListaCanciones lc : AppMusic.getInstancia().getUsuarioActual().getListas()) {
			modelo.addElement(lc.getNombre());
		}
		listas.setModel(modelo);
	}
	
	private void actualizarVentana() {
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		frame.getContentPane().validate();
	}
}

