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
			List<Usuario> usuarios = factoria.getUsuarioDAO().recuperarTodosUsuarios();
			System.out.println("------------------------------------Usuarios-----------------------");
			System.out.println("El tamaño de usuarios es: "+usuarios.size());
			int i=0;
			for (Usuario u : usuarios) {
				System.out.println("El id del usuario "+i+" es "+ u.getId());
				System.out.println("El usuario del usuario "+i+" es "+ u.getUsuario());
				System.out.println("El password del usuario "+i+" es "+ u.getPassword());
				System.out.println("El usuario tiene "+ u.getListas().size()+" listas registradas");
				for (ListaCanciones lc: u.getListas())
					System.out.println("modificando lista con nombre "+ lc.getNombre()+" y codigo "
							+lc.getCodigo()+" y tiene "+lc.getCanciones().size());
				System.out.println();
				usuariosID.put(u.getId(), u);
				usuariosLogIn.put(u.getUsuario(), u);
				i++;
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
		return usuariosID.get(id);
	}
	
	public void addUsuario(Usuario usuario) {
		usuariosID.put(usuario.getId(), usuario);
		usuariosLogIn.put(usuario.getUsuario(), usuario);
		//List<Usuario> usuarios = factoria.getUsuarioDAO().recuperarTodosUsuarios();
	}
	
	public void removeUsuario(Usuario usuario) {
		usuariosID.remove(usuario.getId());
		usuariosLogIn.remove(usuario.getUsuario());
	}
}
