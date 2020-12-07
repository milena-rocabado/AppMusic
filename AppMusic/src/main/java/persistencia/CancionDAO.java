package persistencia;

import java.util.List;

import modelo.Cancion;

public interface CancionDAO {

	void create(Cancion cancion);
	void delete(Cancion cancion);
	Cancion get(int id);
	List<Cancion> getAll();
}
