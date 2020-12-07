package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public final class TDSUsuarioDAO implements UsuarioDAO {

	private ServicioPersistencia sPersistencia;
	
	private static final String USUARIO = "usuario";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";
	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	// falta persistir las listas de canciones
	
	public TDSUsuarioDAO() {
		sPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	private Usuario entidadToUsuario(Entidad eUsuario) {
		String login = sPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String password = sPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String email = sPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String nombre = sPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = sPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String fecha = sPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);

		Usuario usuario = new Usuario(login, password, email, nombre, apellidos, fecha);
		usuario.setId(eUsuario.getId());
		
		return usuario;
	}
	
	private Entidad usuarioToEntidad(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);

		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(NOMBRE, usuario.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), 
				new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(LOGIN, usuario.getUsuario()), 
				new Propiedad(PASSWORD, usuario.getPassword()),
				new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimientoStr()))));
		return eUsuario;
	}

	@Override
	public void create(Usuario usuario) {
		Entidad eUsuario = usuarioToEntidad(usuario);
		eUsuario = sPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	@Override
	public void delete(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = sPersistencia.recuperarEntidad(usuario.getId());
		sPersistencia.borrarEntidad(eUsuario);
	}

	@Override
	public void update(Usuario usuario) {
		Entidad eUsuario = sPersistencia.recuperarEntidad(usuario.getId());
		sPersistencia.eliminarPropiedadEntidad(eUsuario, PASSWORD);
		sPersistencia.anadirPropiedadEntidad(eUsuario, PASSWORD, usuario.getPassword());
		sPersistencia.eliminarPropiedadEntidad(eUsuario, EMAIL);
		sPersistencia.anadirPropiedadEntidad(eUsuario, EMAIL, usuario.getEmail());
	}

	@Override
	public Usuario get(int id) {
		Entidad eUsuario = sPersistencia.recuperarEntidad(id);		
		return entidadToUsuario(eUsuario);
	}

	@Override
	public List<Usuario> getAll() {
		List<Entidad> entidades = sPersistencia.recuperarEntidades(USUARIO);

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(get(eUsuario.getId()));
		}
		
		return usuarios;
	}
	
	
}
