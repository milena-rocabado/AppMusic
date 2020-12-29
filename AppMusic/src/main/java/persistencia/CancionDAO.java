package persistencia;

import java.util.List;

import modelo.Cancion;
import modelo.Usuario;

public interface CancionDAO {

	public void registrarCancion(Cancion cancion);
	public void borrarCancion(Cancion cancion);
	//public void modificarCancion(Cancion cancion);
	public Cancion recuperarCancion(int id);
	public List<Cancion> recuperarTodasCanciones();
	
}
