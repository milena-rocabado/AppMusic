package persistencia;

import java.util.List;

import modelo.Usuario;

public interface UsuarioDAO {

	public void create(Usuario usuario);
	public void delete(Usuario usuario);
	public void update(Usuario usuario);
	public Usuario get(int codigo);
	List<Usuario> getAll();
}
