package persistencia;

import java.util.List;

import modelo.Cancion;

public interface CancionDAO {

	public void registrarCancion(Cancion cancion);
	public void borrarCancion(Cancion cancion);
	public void actualizarReproduccionesCancion(Cancion cancion);
	public Cancion recuperarCancion(int id);
	public List<Cancion> recuperarTodasCanciones();
	
}
