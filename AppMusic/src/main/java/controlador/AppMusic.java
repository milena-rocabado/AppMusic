package controlador;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import modelo.Cancion;
import modelo.CatalogoCanciones;
import modelo.CatalogoUsuarios;
import modelo.ListaCanciones;
import modelo.Usuario;

public class AppMusic {

	private static AppMusic unicaInstancia = null;
	private CatalogoCanciones cCanciones;
	private CatalogoUsuarios cUsuarios;
	private Usuario usuarioActual;
	
	private AppMusic() {
		cCanciones = CatalogoCanciones.getInstancia();
		cUsuarios = CatalogoUsuarios.getInstancia();
		
		//para que no falle por ahora
		usuarioActual = new Usuario("milena", "1234", "", "Milena", "asfdsa", "2020-05-06");
	}
	
	public static AppMusic getInstancia() {
		if (unicaInstancia == null) unicaInstancia = new AppMusic();
		return unicaInstancia;
	}
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
	
	public boolean login(String usuario, String clave) {
		Usuario u = cUsuarios.getUsuario(usuario);
		if (u != null && u.getPassword() == clave) {
			usuarioActual = u;
			return true;
		} return false;
	}
	
	public boolean registrarUsuario(String usuario, String clave, String nombre, String apellidos, String email, LocalDate fecha) {
		Usuario u = new Usuario(usuario, clave, nombre, apellidos, email, fecha);
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
	
	public List<String> getEstilos() {
		return cCanciones.getAllEstilos();
	}
}
