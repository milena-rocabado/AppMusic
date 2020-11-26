package controlador;

import java.time.LocalDate;

import modelo.Cancion;
import modelo.ListaCanciones;
import modelo.Usuario;

public class AppMusic {

	private static AppMusic unicaInstancia = null;
	private Usuario usuarioActual;
	
	private AppMusic() {}
	
	public static AppMusic getUnicaInstancia() {
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
		// if usuario existe en catalogo o BBDD entonces
		//     get usuario
		//     if (clave == usuario.login(clave))
		//        return true
		// else
		return false;
	}
	
	public boolean registrarUsuario(String usuario, String clave, String nombre, String apellidos, String email, LocalDate fecha) {
		// Usuario u = new Usuario(usuario, clave, nombre, apellidos, email, fecha);
		// registrar en BBDD
		// si todo correcto
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
}
