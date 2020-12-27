package vista;

import java.util.List;

import modelo.Cancion;

public interface BusquedaListener {

	void handleBusqueda(List<Cancion> busqueda);
}
