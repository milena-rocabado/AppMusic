package persistencia;

import modelo.ListaCanciones;

public interface ListaCancionesDAO {
	
	public void registrarListaCanciones(ListaCanciones listaCanciones);
	public void modificarListaCanciones(ListaCanciones listaCanciones);
	public ListaCanciones recuperarListaCanciones(int id);

}
