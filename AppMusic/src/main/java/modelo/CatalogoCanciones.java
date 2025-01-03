package modelo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
			List<Cancion> lCanciones = factoria.getCancionDAO().recuperarTodasCanciones();
			System.out.println("-----------------------Canciones-------------------------");
			System.out.println("El tamaño de las canciones es: " + lCanciones.size());
			for (Cancion c : lCanciones) {
				System.out.println(c.toString());
				canciones.put(c.getId(), c);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public static CatalogoCanciones getInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new CatalogoCanciones();
		return unicaInstancia;
	}

	public Cancion getCancion(int codigo) {
		for (Cancion c : canciones.values()) {
			if (c.getId() == codigo)
				return c;
		}
		return null;
	}

	public List<Cancion> getAllCanciones() {
		return new LinkedList<>(this.canciones.values());
	}

	public List<Cancion> getCancionesTitulo(List<Cancion> lista,String titulo) {
		if (titulo.isEmpty())
			return lista;
		List<Cancion> listaCopia = new LinkedList<>(lista);
		for (Cancion c : lista) {
			if (!c.getTitulo().contains(titulo))
				listaCopia.remove(c);
		}
		return listaCopia;
	}

	public List<Cancion> getCancionesInterprete(List<Cancion> lista,String interprete) {
		List<Cancion> todasCanciones = new LinkedList<Cancion>(this.canciones.values());
		if (interprete.isEmpty())
			return todasCanciones;
		for (Cancion c : this.canciones.values()) {
			if (c.esInterpretadaPor(interprete))
				lista.remove(c);
		}
		return lista;
	}

	public List<Cancion> getCancionesEstilo(List<Cancion> lista,String estilo) {
		if (estilo.isEmpty())
			return lista;
		List<Cancion> listaCopia = new LinkedList<>(lista);
		for (Cancion c : lista) {
			if (!c.esEstiloMusical(estilo))
				listaCopia.remove(c);
		}
		return listaCopia;
	}

	public List<Cancion> filtrarCanciones(String interprete, String titulo, String estilo) {
		return canciones.values().stream().filter(c -> interprete == null || interprete.isEmpty() || c.esInterpretadaPor(interprete))
											.filter(c -> titulo == null || titulo.isEmpty() || c.tieneTitulo(titulo))
											.filter(c -> estilo == null || estilo.isEmpty() || c.esEstiloMusical(estilo))
											.collect(Collectors.toList());
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
		return new LinkedList<>(estilos);
	}
}
