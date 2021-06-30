package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
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

public class AppMusic implements ICancionesListener {

	private static AppMusic unicaInstancia = null;

	private CatalogoUsuarios cUsuarios;
	private CatalogoCanciones cCanciones;
	private MediaPlayer mediaPlayer;
	private String tempPath;
	private String binPath;
	private UsuarioDAO usuarioDAO;
	private CancionDAO cancionDAO;
	private ListaCancionesDAO listaCancionesDAO;
	private Canciones canciones;

	private Usuario usuarioActual;

	private AppMusic() {
		binPath = AppMusic.class.getClassLoader().getResource(".").getPath();
		binPath = binPath.replaceFirst("/", "");
		// quitar "/" añadida al inicio del path en plataforma Windows
		tempPath = binPath.replace("/bin", "/temp");
		
		inicializarAdaptadores();
		inicializarCatalogos();
		if (canciones == null)
			canciones = new Canciones();
		canciones.addCancionesListener(this);
		// para que no falle por ahora
		usuarioActual = new Usuario("milena", "1234", "", "Milena", "asfdsa", "2020-05-06");
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

	public ListaCanciones crearLista(String nombre) {
		return usuarioActual.crearLista(nombre);
	}
	
	public void actualizarListaCanciones(ListaCanciones lista) {
		listaCancionesDAO.modificarListaCanciones(lista);
		//cUsuarios.removeUsuario(usuarioActual);
		
		usuarioActual.actualizarListaCanciones(lista);
		//cUsuarios.addUsuario(usuarioActual);
		//usuarioDAO.modificarUsuario(usuarioActual);
		
		
	}

	public List<Cancion> buscarCanciones(String interprete, String titulo, String estilo) {
//		List<Cancion> lista = new LinkedList<>();
//		lista = cCanciones.getCancionesInterprete(lista,interprete); // el catalogo se encarga de comprobar
//																					// si los strings estan vacios
//		lista = cCanciones.getCancionesTitulo(lista,titulo);
//		lista = cCanciones.getCancionesEstilo(lista,estilo);
//		// interseccion de las listas
//		return lista;
		return cCanciones.filtrarCanciones(interprete, titulo, estilo);
	}

	public List<String> getEstilos() {
		return cCanciones.getAllEstilos();
	}

	public ListaCanciones getListaCanciones(String nombre) {
		return usuarioActual.getListaCanciones(nombre);
	}

	public void cargarCanciones(File fichero) {
		/*boolean correcto = */canciones.setArchivoCanciones(fichero.getAbsolutePath());
	}

	@Override
	public void enteradoCambioCanciones(CancionesEvent event) {
		List<CancionComponente> canciones = event.getNewValue();
		List<Cancion> cancionesEnCatalogo = cCanciones.getAllCanciones();
		for (CancionComponente cancion : canciones) {
			Cancion c1 = new Cancion(cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo(), cancion.getURL());
			boolean introducir = true;
			for (Cancion c2 : cancionesEnCatalogo) {
				if (c1.equals(c2)) {
					introducir = false;
					break;
				}
			}
			if (introducir)
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
									+ " (" + usuarioActual.getUsuario() + ")"));
		for (ListaCanciones lc : usuarioActual.getListas()) {
			documento.add(new Paragraph(lc.toString()));
		}
		documento.close();
	}

	public void crearListaCanciones(ListaCanciones listaAux) {
		listaCancionesDAO.registrarListaCanciones(listaAux);
		//cUsuarios.removeUsuario(usuarioActual);
		usuarioActual.addListaCanciones(listaAux);
		//cUsuarios.addUsuario(usuarioActual);
		usuarioDAO.actualizarListasUsuario(usuarioActual);
		
	}

	public void reproducir(Cancion cancion) {
		if (mediaPlayer!=null) {
			pararCancionActual();
		}
		URL uri = null;
		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {
			});
			uri = new URL(cancion.getUrl());
			System.setProperty("java.io.tmpdir", tempPath);
			Path mp3 = Files.createTempFile("now-playing", ".mp3");
			System.out.println(mp3.getFileName());
//			try (InputStream stream = uri.openStream()) {
//				Files.copy(stream, mp3, StandardCopyOption.REPLACE_EXISTING);
//			}
//			System.out.println("finished-copy: " + mp3.getFileName());
			
			//Media media = new Media(mp3.toFile().toURI().toString());
			Media media = new Media(uri.toString());
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();
			System.out.println("///////////Reproduciendo///////////");
			System.out.println("Cancion: "+cancion.getTitulo()+ " Reproducciones: "+cancion.getNumReproducciones());
			cancion.reproducirCancion();
			usuarioActual.addCancionReciente(cancion);
			usuarioDAO.actualizarCancionesRecientesUsuario(usuarioActual);
			cancionDAO.actualizarReproduccionesCancion(cancion);
			System.out.println("Cancion: "+cancion.getTitulo()+ " Reproducciones: "+cancion.getNumReproducciones());
			System.out.println("///////////Reproduciendo///////////");
			System.out.println();
			getCancionesMasReproducidas();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void pararCancionActual() {
		mediaPlayer.stop();
		File directorio = new File(tempPath);
		String[] files = directorio.list();
		for (String archivo : files) {
			File fichero = new File(tempPath + File.separator + archivo);
			System.out.println(fichero.getAbsolutePath());
			fichero.delete();
		}
		
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
	
	public void getCancionesMasReproducidas() {
		System.out.println("//////////Canciones mas Reproducidas//////");
		List<Cancion> canciones = cancionDAO.recuperarTodasCanciones();
		List<Cancion> cancionOrdenadas = canciones.stream()
		        .sorted(Comparator.comparingInt(Cancion::getNumReproducciones).reversed())
		        .collect(Collectors.toList());
		for(int i=0; i<cancionOrdenadas.size();i++) {
			System.out.println("Posicion:"+i+" Cancion: "+ cancionOrdenadas.get(i).getTitulo() +" Reproducciones: "+cancionOrdenadas.get(i).getNumReproducciones());
		}
	}
}
