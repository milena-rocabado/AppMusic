package modelo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import persistencia.DAOException;
import persistencia.FactoriaDAO;


public class CatalogoCanciones {

	private Map<Integer, Cancion> canciones;
	private static CatalogoCanciones unicaInstancia = null;
	private FactoriaDAO factoria;
	
	private CatalogoCanciones() {
		this.canciones = new HashMap<>();
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Cancion> lCanciones = factoria.getCancionDAO().getAll();
			for (Cancion c : lCanciones)
				canciones.put(c.getId(), c);
			
		} catch (DAOException e) {
			e.printStackTrace();
		}  
	}
	
	public static CatalogoCanciones getInstancia() {
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
	
	public List<Cancion> getCancionesTitulo(String titulo) {
		List<Cancion> lista = new LinkedList<>();
		for (Cancion c : this.canciones.values()) {
			if (c.getTitulo().contains(titulo)) lista.add(c);
		}
		return lista;
	}
	
	public List<Cancion> getCancionesInterprete(String nombre) {
		List<Cancion> lista = new LinkedList<>();
		for (Cancion c : this.canciones.values()) {
			if (c.esInterpretadaPor(nombre)) lista.add(c);
		}
		return lista;
	}
	
	public List<Cancion> getCancionesEstilo(String estilo) {
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

	public List<String> getAllEstilos() {
		Set<String> estilos = new HashSet<>();
		// añadir estilos sin repetir
		for (Cancion c : canciones.values())
			estilos.add(c.getEstilo());
		List<String> lista = new LinkedList<>();
		lista.addAll(estilos);
		return lista;
	}
}
