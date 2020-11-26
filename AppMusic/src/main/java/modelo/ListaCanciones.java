package modelo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListaCanciones {

	private String nombre;
	private List<Cancion> canciones;
	
	public ListaCanciones(String nombre) {
		this.nombre = nombre;
		this.canciones = new LinkedList<>();
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Cancion> getCanciones() {
		return Collections.unmodifiableList(canciones);
	}
	
	public void addCancion(Cancion cancion) {
		canciones.add(cancion);
	}
	
	public void removeCancion(Cancion cancion) {
		canciones.remove(cancion);
	}
	
}
