package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import modelo.Cancion;
import modelo.CatalogoCanciones;
import modelo.CatalogoUsuarios;
import modelo.ListaCanciones;
import modelo.Usuario;
import persistencia.CancionDAO;
import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.ListaCancionesDAO;
import persistencia.UsuarioDAO;
import umu.tds.componente.Canciones;
import umu.tds.componente.ICancionesListener;
import umu.tds.componente.CancionesEvent;
import umu.tds.componente.CancionComponente;

@SuppressWarnings("restriction")
public class AppMusic implements ICancionesListener {

	private static AppMusic unicaInstancia = null;

	private CatalogoUsuarios cUsuarios;
	private CatalogoCanciones cCanciones;
	private MediaPlayer mediaPlayer;
	private UsuarioDAO usuarioDAO;
	private CancionDAO cancionDAO;
	private ListaCancionesDAO listaCancionesDAO;
	private Canciones canciones;

	private Usuario usuarioActual;

	private AppMusic() {
		
		inicializarAdaptadores();
		inicializarCatalogos();
		if (canciones == null)
			canciones = new Canciones();
		canciones.addCancionesListener(this);
	}

	public static AppMusic getInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AppMusic();
		return unicaInstancia;
	}

	private void inicializarCatalogos() {
		cCanciones = CatalogoCanciones.getInstancia();
		cUsuarios = CatalogoUsuarios.getInstancia();
	}

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace(); 
		}
		cancionDAO = factoria.getCancionDAO();
		listaCancionesDAO = factoria.getListaCancionesDAO();
		usuarioDAO = factoria.getUsuarioDAO();
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public boolean login(String usuario, String clave) {
		Usuario u = cUsuarios.getUsuario(usuario);
		if (u != null && u.getPassword().equals(clave)) {
			usuarioActual = u;
			return true;
		}
		return false;
	}

	public boolean registrarUsuario(String usuario, String clave, String nombre, String apellidos, String email,
			LocalDate fecha) {
		Usuario u = new Usuario(usuario, clave, nombre, apellidos, email, fecha);
		usuarioDAO.registrarUsuario(u);
		cUsuarios.addUsuario(u);

		usuarioActual = u;
		return true;
	}

	public void play(Cancion cancion) {
		cancion.reproducirCancion();
	}

	public void crearListaCanciones(ListaCanciones listaAux) {
		listaCancionesDAO.registrarListaCanciones(listaAux);
		usuarioActual.addListaCanciones(listaAux);
		usuarioDAO.actualizarListasUsuario(usuarioActual);
	}
	
	public void actualizarListaCanciones(ListaCanciones lista) {
		listaCancionesDAO.modificarListaCanciones(lista);
		usuarioActual.actualizarListaCanciones(lista);
	}

	public List<Cancion> buscarCanciones(String interprete, String titulo, String estilo) {
		return cCanciones.filtrarCanciones(interprete, titulo, estilo);
	}

	public List<String> getEstilos() {
		return cCanciones.getAllEstilos();
	}

	public ListaCanciones getListaCanciones(String nombre) {
		return usuarioActual.getListaCanciones(nombre);
	}

	public void cargarCanciones(File fichero) {
		canciones.setArchivoCanciones(fichero.getAbsolutePath());
	}

	@Override
	public void enteradoCambioCanciones(CancionesEvent event) {
		List<CancionComponente> canciones = event.getNewValue();
		List<Cancion> cancionesEnCatalogo = cCanciones.getAllCanciones();
		for (CancionComponente cancion : canciones) {
			Cancion c1 = new Cancion(cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo(), cancion.getURL());
			if (!cancionesEnCatalogo.contains(c1))
				registrarCancion(c1);
		}

	}
	

	public void registrarCancion(Cancion cancion) {
		cancionDAO.registrarCancion(cancion);
		cCanciones.addCancion(cancion);
	}
	
	public boolean existeUsuario(String usuario) {
		Usuario u = cUsuarios.getUsuario(usuario);
		return (u != null);
	}

	public void generarPDF(File file) throws FileNotFoundException, DocumentException {
		FileOutputStream output = new FileOutputStream(file);
		Document documento = new Document();
		PdfWriter.getInstance(documento, output);
		documento.open();
		documento.add(new Paragraph("Listas de Canciones de " + usuarioActual.getNombre()
									+ " (" + usuarioActual.getUsuario() + ")\n\n"));
		for (ListaCanciones lc : usuarioActual.getListas()) {
			documento.add(new Paragraph(lc.toStringImprimirPDF()+"\n"));
		}
		documento.close();
	}

	public void reproducir(Cancion cancion) {
		if (mediaPlayer!=null) {
			pararCancionActual();
		}
		URL uri = null;
		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {});
			uri = new URL(cancion.getUrl());
			Media media = new Media(uri.toString());
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();
			cancion.reproducirCancion();
			System.out.println("Reproduciendo:"+cancion.toString());
			usuarioActual.addCancionReciente(cancion);
			usuarioDAO.actualizarCancionesRecientesUsuario(usuarioActual);
			cancionDAO.actualizarReproduccionesCancion(cancion);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void pararCancionActual() {
		if (mediaPlayer != null)
			mediaPlayer.stop();
	}

	public void pausar() {
		if (mediaPlayer != null)
			mediaPlayer.pause();
	}
	
	
	public void reanudar() {
		if (mediaPlayer!=null && mediaPlayer.getStatus().equals(Status.PAUSED)){
			mediaPlayer.play();
		}
	}
	
	public List<Cancion> getCancionesMasReproducidas() {
		List<Cancion> canciones = cCanciones.getAllCanciones();
		List<Cancion> cancionOrdenadas = canciones.stream()
		        .sorted(Comparator.comparingInt(Cancion::getNumReproducciones).reversed())
		        .collect(Collectors.toList());
		List<Cancion> devolver10Primeras = new ArrayList<Cancion>();
		for(int i=0; i<cancionOrdenadas.size() && i<10;i++) {
			if (cancionOrdenadas.get(i).getNumReproducciones()==0)
				break;
			devolver10Primeras.add(cancionOrdenadas.get(i));
		}
		return devolver10Primeras;
	}
	
	public boolean hacerPremium() {
		usuarioActual.realizarPago(9.99);
		usuarioDAO.modificarPremium(usuarioActual);
		return true;
	}

	
}
