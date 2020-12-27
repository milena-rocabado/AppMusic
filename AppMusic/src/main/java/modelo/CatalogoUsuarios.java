package modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import persistencia.DAOException;
import persistencia.FactoriaDAO;

public class CatalogoUsuarios {
	Map<Integer, Usuario> usuariosID;
	Map<String, Usuario> usuariosLogIn;
	private static CatalogoUsuarios unicaInstancia = null;
	
	private FactoriaDAO factoria;
	
	private CatalogoUsuarios() {
		usuariosID = new HashMap<>();
		usuariosLogIn = new HashMap<>();
		try {
			factoria = FactoriaDAO.getInstancia();
			List<Usuario> usuarios = factoria.getUsuarioDAO().getAll();
			for (Usuario u : usuarios) {
				usuariosID.put(u.getId(), u);
				usuariosLogIn.put(u.getUsuario(), u);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public static CatalogoUsuarios getInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoUsuarios();
		return unicaInstancia;
	}
	
	public List<Usuario> getAllUsuarios() throws DAOException {
		return new LinkedList<>(usuariosID.values());
	}
	
	public Usuario getUsuario(String logIn) {
		return usuariosLogIn.get(logIn);
	}
	
	public Usuario getUsuario(Integer id) {
		return usuariosLogIn.get(id);
	}
	
	public void addUsuario(Usuario usuario) {
		usuariosID.put(usuario.getId(), usuario);
		usuariosLogIn.put(usuario.getUsuario(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		usuariosID.remove(usuario.getId());
		usuariosLogIn.remove(usuario.getUsuario());
	}
}
