package modelo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListaCanciones {

	private int codigo;
	private String nombre;
	private List<Cancion> canciones;
	
	public ListaCanciones(String nombre) {
		this.codigo=0;
		this.nombre = nombre;
		this.canciones = new LinkedList<>();
	}
	
	public ListaCanciones(ListaCanciones listaCanciones) {
		this.codigo=listaCanciones.getCodigo();
		this.nombre = listaCanciones.getNombre();
		this.canciones = new LinkedList<>(listaCanciones.getCanciones());
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
		
	}
	
	public void removeCancion(Cancion cancion) {
		canciones.remove(cancion);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaCanciones other = (ListaCanciones) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Nombre="+nombre + ", id="+codigo+"\n");
		for (Cancion c : canciones) {
			sb.append("\t");
			sb.append(c.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public String toStringImprimirPDF() {
		StringBuffer sb = new StringBuffer();
		sb.append("Nombre="+nombre + "\n");
		for (Cancion c : canciones) {
			sb.append("\t");
			sb.append(c.toStringImprimirPDF());
			sb.append("\n");
		}
		return sb.toString();
	}
}
