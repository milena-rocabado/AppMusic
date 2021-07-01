package persistencia;

import java.util.List;

import modelo.Usuario;

public interface UsuarioDAO {

	public void registrarUsuario(Usuario usuario);
	public void borrarUsuario(Usuario usuario);
	public void modificarUsuario(Usuario usuario);
	public void actualizarCancionesRecientesUsuario(Usuario usuario);
	public Usuario recuperarUsuario(int id);
	List<Usuario> recuperarTodosUsuarios();
	void actualizarListasUsuario(Usuario usuario);
}
