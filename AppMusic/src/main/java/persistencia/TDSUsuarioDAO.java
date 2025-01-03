package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import beans.Entidad;
import beans.Propiedad;
import modelo.Cancion;
import modelo.ListaCanciones;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public final class TDSUsuarioDAO implements UsuarioDAO {

	private static TDSUsuarioDAO unicaInstancia;
	private ServicioPersistencia sPersistencia;

	private static final String USUARIO = "usuario";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";
	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String PREMIUM = "premium";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String LISTAS = "listas";
	private static final String CANCIONESRECIENTES = "cancionesRecientes";

	private TDSUsuarioDAO() {
		sPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public static TDSUsuarioDAO getInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new TDSUsuarioDAO();
		return unicaInstancia;
	}

	private Usuario entidadToUsuario(Entidad eUsuario) {
		String login = sPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String password = sPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String email = sPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String nombre = sPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = sPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String fecha = sPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		String premium = sPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		
		Usuario usuario = new Usuario(login, password, email, nombre, apellidos, fecha);
		usuario.setPremium(Boolean.parseBoolean(premium));

		List<ListaCanciones> lista;

		lista = obtenerListasDesdeCodigos(sPersistencia.recuperarPropiedadEntidad(eUsuario, LISTAS));

		Vector<Cancion> cancionesRecientes;
		cancionesRecientes = obtenerCancionesRecientesDesdeCodigos(
				sPersistencia.recuperarPropiedadEntidad(eUsuario, CANCIONESRECIENTES));

		
		if (lista != null)
			usuario.setListas(lista);
		if (cancionesRecientes != null)
			usuario.setCancionesRecientes(cancionesRecientes);
		usuario.setId(eUsuario.getId());

		return usuario;
	}

	private Entidad usuarioToEntidad(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);

		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, usuario.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(LOGIN, usuario.getUsuario()), new Propiedad(PASSWORD, usuario.getPassword()),
				new Propiedad(PREMIUM, Boolean.toString(usuario.isPremium())),
				new Propiedad(LISTAS, obtenerCodigosListas(usuario.getListas())),
				new Propiedad(CANCIONESRECIENTES, obtenerCodigosCancionesRecientes(usuario.getCancionesRecientes())),
				new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimientoStr()))));
		return eUsuario;
	}

	@Override
	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario = null;
		eUsuario = usuarioToEntidad(usuario);
		eUsuario = sPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());

	}
	

	@Override
	public void borrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = sPersistencia.recuperarEntidad(usuario.getId());
		sPersistencia.borrarEntidad(eUsuario);
	}

	@Override
	public void actualizarListasUsuario(Usuario usuario) {
		Entidad eUsuario = sPersistencia.recuperarEntidad(usuario.getId());
		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(LISTAS)) {
				prop.setValor(obtenerCodigosListas(usuario.getListas()));
			}

			sPersistencia.modificarPropiedad(prop);
		}
	}
	
	@Override
	public void actualizarCancionesRecientesUsuario(Usuario usuario) {
		Entidad eUsuario = sPersistencia.recuperarEntidad(usuario.getId());
		
		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(CANCIONESRECIENTES)) {
				prop.setValor(obtenerCodigosCancionesRecientes(usuario.getCancionesRecientes()));
			}

			sPersistencia.modificarPropiedad(prop);
		}
	}
	
	@Override
	public void modificarPremium(Usuario usuario) {
		Entidad eUsuario = sPersistencia.recuperarEntidad(usuario.getId());
		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(PREMIUM)) {
				prop.setValor(Boolean.toString(usuario.isPremium()));
			}

			sPersistencia.modificarPropiedad(prop);
		}
		
	}

	@Override
	public Usuario recuperarUsuario(int id) {
		Entidad eUsuario = sPersistencia.recuperarEntidad(id);
		return entidadToUsuario(eUsuario);
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		List<Entidad> entidades = sPersistencia.recuperarEntidades(USUARIO);
		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		return usuarios;
	}

	// -------------------Funciones auxiliares-------------------------------

	private String obtenerCodigosListas(List<ListaCanciones> listaDeListaCanciones) {
		String linea = "";
		for (ListaCanciones lista : listaDeListaCanciones) {
			linea += lista.getCodigo() + " ";
		}
		return linea.trim();
	}

	private List<ListaCanciones> obtenerListasDesdeCodigos(String listaCanciones) {
		if (listaCanciones == null)
			return null;
		List<ListaCanciones> listaDeListaCanciones = new LinkedList<ListaCanciones>();
		StringTokenizer strTok = new StringTokenizer(listaCanciones, " ");
		TDSListaCancionesDAO adaptadorListaCanciones = TDSListaCancionesDAO.getInstancia();
		while (strTok.hasMoreTokens()) {
			listaDeListaCanciones.add(
					adaptadorListaCanciones.recuperarListaCanciones(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaDeListaCanciones;
	}

	private String obtenerCodigosCancionesRecientes(Vector<Cancion> cancionesRecientes) {
		String linea = "";
		for (Cancion cancion : cancionesRecientes) {
			linea += cancion.getId() + " ";
		}
		return linea.trim();
	}

	private Vector<Cancion> obtenerCancionesRecientesDesdeCodigos(String cadena) {
		if (cadena == null)
			return null;
		Vector<Cancion> cancionesRecientes = new Vector<Cancion>();
		StringTokenizer strTok = new StringTokenizer(cadena, " ");
		TDSCancionDAO adaptadorCanciones = TDSCancionDAO.getInstancia();
		while (strTok.hasMoreTokens()) {
			cancionesRecientes.add(adaptadorCanciones.recuperarCancion(Integer.valueOf((String) strTok.nextElement())));
		}
		return cancionesRecientes;

	}

	

	

}
