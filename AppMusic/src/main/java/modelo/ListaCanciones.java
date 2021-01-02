package modelo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListaCanciones {

	private int codigo;
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
	
	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}

	public void addCancion(Cancion cancion) {
		canciones.add(cancion);
		System.out.println("[" + nombre + "] Canción añadida:   " + 
					cancion.getTitulo() + " - " + cancion.getInterprete());
	}
	
	public void removeCancion(Cancion cancion) {
		canciones.remove(cancion);
		System.out.println("[" + nombre + "] Canción eliminada: " + cancion.getTitulo() + " - " + cancion.getInterprete());
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}
