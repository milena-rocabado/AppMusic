package modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class CatalogoCanciones {

	private Map<Integer, Cancion> canciones;
	private static CatalogoCanciones unicaInstancia = null;
	
	private CatalogoCanciones() {
		this.canciones = new HashMap<>();
	}
	
	public CatalogoCanciones getInstancia() {
		if(unicaInstancia == null) unicaInstancia = new CatalogoCanciones();
		return unicaInstancia;
	}
	
	public Cancion getCancion(int codigo) {
		for (Cancion c : canciones.values()) {
			if (c.getId() == codigo) return c;
		}
		return null;
	}
	
	public List<Cancion> getAllCanciones() {
		List<Cancion> lista = new LinkedList<>();
		for (Cancion c : this.canciones.values())
			lista.add(c);
		return lista;
	}
	
	public List<Cancion> getCancionTitulo(String titulo) {
		List<Cancion> lista = new LinkedList<>();
		for (Cancion c : this.canciones.values()) {
			if (c.getTitulo().contains(titulo)) lista.add(c);
		}
		return lista;
	}
	
	// buscar alguna forma de usar la lista de canciones que almacena el interprete
	public List<Cancion> getCancionesInterprete(String nombre) {
		List<Cancion> lista = new LinkedList<>();
		for (Cancion c : this.canciones.values()) {
			if (c.esInterpretadaPor(nombre)) lista.add(c);
		}
		return lista;
	}
	
	public List<Cancion> getCancionEstilo(String estilo) {
		List<Cancion> canciones = new LinkedList<>();
		for (Cancion c : this.canciones.values()) {
			if (c.esEstiloMusical(estilo)) canciones.add(c);
		}
		return canciones;
	}
	
	public void addCancion(Cancion cancion) {
		canciones.put(cancion.getId(), cancion);
	}
	
	public void removeCancion(Cancion cancion) {
		canciones.remove(cancion.getId());
	}
}
