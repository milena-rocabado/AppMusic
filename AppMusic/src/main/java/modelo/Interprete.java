package modelo;

import java.util.ArrayList;
import java.util.List;

public class Interprete {
	
	private int codigo;
	private String nombre;
	private List<Cancion> canciones;
	
	public Interprete(String nombre) {
		codigo=0;
		this.nombre = nombre;
		if (canciones == null) {
			canciones = new ArrayList<Cancion>();
		}

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void addCancionInterprete(Cancion cancion) {
		if (canciones == null) {
			canciones = new ArrayList<Cancion>();
		}
		canciones.add(cancion);
	}

}
