package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import modelo.Cancion;
import modelo.ListaCanciones;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class TDSListaCancionesDAO implements ListaCancionesDAO{
	
	private static TDSListaCancionesDAO unicaInstancia;
	private ServicioPersistencia sPersistencia;

	private static final String LISTACANCIONES = "listaCanciones";
	private static final String NOMBRE = "nombre";
	private static final String CANCIONES = "canciones";
	
	private TDSListaCancionesDAO() {
		sPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public static TDSListaCancionesDAO getInstancia() {
		if (unicaInstancia == null) unicaInstancia = new TDSListaCancionesDAO();
		return unicaInstancia;
	}
	
	private ListaCanciones entidadToListaCanciones(Entidad eListaCanciones) {
		String nombre = sPersistencia.recuperarPropiedadEntidad(eListaCanciones, NOMBRE);
		List<Cancion> canciones;
		
		canciones = obtenerCancionesDesdeCodigos(sPersistencia.recuperarPropiedadEntidad(eListaCanciones, CANCIONES));

		ListaCanciones listaCanciones = new ListaCanciones(nombre);
		if (canciones!=null)
			listaCanciones.setCanciones(canciones);
		
		listaCanciones.setCodigo(eListaCanciones.getId());
		return listaCanciones;
	}
	
	private Entidad listaCancionesToEntidad(ListaCanciones listaCanciones) {
		Entidad eListaCanciones = new Entidad();
		eListaCanciones.setNombre(LISTACANCIONES);
		
		eListaCanciones.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(NOMBRE, listaCanciones.getNombre()),
				new Propiedad(CANCIONES, obtenerCodigosCanciones(listaCanciones.getCanciones())
				))));
		return eListaCanciones;
	}

	@Override
	public void registrarListaCanciones(ListaCanciones listaCanciones) {
		Entidad eListaCanciones = listaCancionesToEntidad(listaCanciones); 
		eListaCanciones = sPersistencia.registrarEntidad(eListaCanciones);
		listaCanciones.setCodigo(eListaCanciones.getId());
		
	}

	@Override
	public void borrarListaCanciones(ListaCanciones listaCanciones) {
		Entidad eListaCanciones = sPersistencia.recuperarEntidad(listaCanciones.getCodigo());
		sPersistencia.borrarEntidad(eListaCanciones);
	}

	@Override
	public void modificarListaCanciones(ListaCanciones listaCanciones) {
		Entidad eListaCanciones = sPersistencia.recuperarEntidad(listaCanciones.getCodigo());
		sPersistencia.eliminarPropiedadEntidad(eListaCanciones, NOMBRE);
		sPersistencia.anadirPropiedadEntidad(eListaCanciones, NOMBRE, listaCanciones.getNombre());
		sPersistencia.eliminarPropiedadEntidad(eListaCanciones, CANCIONES);
		sPersistencia.anadirPropiedadEntidad(eListaCanciones, CANCIONES, obtenerCodigosCanciones(listaCanciones.getCanciones()));
		
	}

	@Override
	public ListaCanciones recuperarListaCanciones(int id) {
		Entidad eListaCanciones = sPersistencia.recuperarEntidad(id);
		return entidadToListaCanciones(eListaCanciones);
	}

	@Override
	public List<ListaCanciones> recuperarTodasListaCanciones() {
		List<Entidad> entidades = sPersistencia.recuperarEntidades(LISTACANCIONES);
		System.out.println("Hay en la base de datos: "+ entidades.size()+" LISTAS de canciones");
		List<ListaCanciones> listasDeCanciones = new LinkedList<>();
		for (Entidad eListaCanciones : entidades)
			listasDeCanciones.add(entidadToListaCanciones(eListaCanciones));
		
		return listasDeCanciones;
	}
	
	//-------------------Funciones auxiliares-------------------------------
	
	private String obtenerCodigosCanciones(List<Cancion> listaCanciones) {
		String linea = "";
		for (Cancion cancion : listaCanciones) {
			linea += cancion.getId() + " ";
		}
		return linea.trim();
	}
	
	private List<Cancion> obtenerCancionesDesdeCodigos(String canciones) {
		if (canciones == null) return null;
		List<Cancion> listaCanciones = new LinkedList<Cancion>();
		StringTokenizer strTok = new StringTokenizer(canciones, " ");
		TDSCancionDAO adaptadorCancion = TDSCancionDAO.getInstancia();
		while (strTok.hasMoreTokens()) {
			listaCanciones.add(adaptadorCancion.recuperarCancion(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaCanciones;
	}

}
