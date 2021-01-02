package controlador;

import java.io.File;
import java.time.LocalDate;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

import modelo.Cancion;
import modelo.CatalogoCanciones;
import modelo.CatalogoUsuarios;
import modelo.Estilo;
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

	public void addCancion(ListaCanciones lista, Cancion cancion) {
		lista.addCancion(cancion);
	}

	public void removeCancion(ListaCanciones lista, Cancion cancion) {
		lista.removeCancion(cancion);
	}

	public List<Cancion> buscarCanciones(String interprete, String titulo, String estilo) {
		List<Cancion> lista = new LinkedList<>();
		List<Cancion> cancInter = cCanciones.getCancionesInterprete(interprete); // el catalogo se encarga de comprobar
																					// si los strings estan vacios
		List<Cancion> cancTitulo = cCanciones.getCancionesTitulo(interprete);
		List<Cancion> cancEstilo = cCanciones.getCancionesEstilo(interprete);
		// interseccion de las listas
		return lista;
	}

	public List<String> getEstilos() {
		return cCanciones.getAllEstilos();
	}

	public ListaCanciones getListaCanciones(String nombre) {
		for (ListaCanciones lc : usuarioActual.getListas()) {
			if (lc.getNombre().equals(nombre))
				return lc;
		}
		return null;
	}

	public void cargarCanciones(File fichero) {
		// System.out.println(fichero.getAbsolutePath());
		// System.out.println(canciones);
		boolean correcto = canciones.setArchivoCanciones(fichero.getAbsolutePath());
	}

	@Override
	public void enteradoCambioCanciones(CancionesEvent event) {
		List<CancionComponente> canciones = event.getNewValue();
		List<Cancion> cancionesEnCatalogo = cCanciones.getAllCanciones();
		for (CancionComponente cancion : canciones) {
			Cancion c1 = new Cancion(cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo());
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

}
