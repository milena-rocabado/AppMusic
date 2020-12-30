package persistencia;

import java.util.List;

import modelo.ListaCanciones;

public interface ListaCancionesDAO {
	
	public void registrarListaCanciones(ListaCanciones listaCanciones);
	public void borrarListaCanciones(ListaCanciones listaCanciones);
	public void modificarListaCanciones(ListaCanciones listaCanciones);
	public ListaCanciones recuperarListaCanciones(int id);
	List<ListaCanciones> recuperarTodasListaCanciones();

}
