package controlador;

import java.time.LocalDate;
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
import persistencia.UsuarioDAO;

public class AppMusic {

	private static AppMusic unicaInstancia = null;
	
	private CatalogoCanciones cCanciones;
	private CatalogoUsuarios cUsuarios;
	
	private CancionDAO cancionDAO;
	private UsuarioDAO usuarioDAO;
	
	private Usuario usuarioActual;
	
	private AppMusic() {
		inicializarAdaptadores();
		inicializarCatalogos();
		//para que no falle por ahora
		usuarioActual = new Usuario("milena", "1234", "", "Milena", "asfdsa", "2020-05-06");
	}
	
	public static AppMusic getInstancia() {
		if (unicaInstancia == null) unicaInstancia = new AppMusic();
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
		System.out.println("El usuario devuelto del catalogo es: "+ u.getUsuario() );
		System.out.println("La clave es: "+ u.getPassword() );
		if (u != null && u.getPassword().equals(clave)) {
			System.out.println("Ahora si entra");
			usuarioActual = u;
			return true;
		}
		System.out.println("No entra");
		return false;
	}
	
	public boolean registrarUsuario(String usuario, String clave, String nombre, String apellidos, String email, LocalDate fecha) {
		Usuario u = new Usuario(usuario, clave, nombre, apellidos, email, fecha);
		usuarioDAO.registrarUsuario(u);
		cUsuarios.addUsuario(u);
		
		usuarioActual = u;
		return true;
	}
	
	public void play(Cancion cancion) {
		cancion.reproducirCancion();
	}
	
	// rfc
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
		List<Cancion> cancInter = cCanciones.getCancionesInterprete(interprete); // el catalogo se encarga de comprobar si los strings estan vacios
		List<Cancion> cancTitulo = cCanciones.getCancionesTitulo(interprete);
		List<Cancion> cancEstilo = cCanciones.getCancionesEstilo(interprete);
		// interseccion de las listas
		return lista;
	}
	
	public List<Estilo> getEstilos() {
		return cCanciones.getAllEstilos();
	}
}
