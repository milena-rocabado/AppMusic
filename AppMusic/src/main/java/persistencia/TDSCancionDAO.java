package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import modelo.Cancion;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public final class TDSCancionDAO implements CancionDAO {

	private static TDSCancionDAO unicaInstancia;
	private ServicioPersistencia sPersistencia;

	private static final String CANCION = "cancion";
	private static final String TITULO = "titulo";
	private static final String INTERPRETE = "interprete";
	private static final String ESTILO = "estilo";
	private static final String URL = "url";
	private static final String NREPRODUCCIONES = "nreproducciones";
	
	private TDSCancionDAO() {
		sPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public static TDSCancionDAO getInstancia() {
		if (unicaInstancia == null) unicaInstancia = new TDSCancionDAO();
		return unicaInstancia;
	}
	
	private Cancion entidadToCancion(Entidad eCancion) {

		String titulo = sPersistencia.recuperarPropiedadEntidad(eCancion, TITULO);
		String interprete = sPersistencia.recuperarPropiedadEntidad(eCancion, INTERPRETE);
		String estilo = sPersistencia.recuperarPropiedadEntidad(eCancion, ESTILO);
		String url = sPersistencia.recuperarPropiedadEntidad(eCancion, URL);
		String nreproducciones = sPersistencia.recuperarPropiedadEntidad(eCancion, NREPRODUCCIONES);
		
		Cancion cancion = new Cancion(titulo, interprete, estilo, url, nreproducciones);
		cancion.setId(eCancion.getId());
		PoolDAO.getUnicaInstancia().addObjeto(cancion.getId(), cancion); 
		return cancion;
	}
	
	private Entidad cancionToEntidad(Cancion cancion) {
		Entidad eCancion = new Entidad();
		eCancion.setNombre(CANCION);
		
		eCancion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(TITULO, cancion.getTitulo()),
				new Propiedad(INTERPRETE, cancion.getInterprete()),
				new Propiedad(ESTILO, cancion.getEstilo()),
				new Propiedad(URL, cancion.getUrl()),
				new Propiedad(NREPRODUCCIONES, cancion.getNumReproduccionesStr())
				)));
		return eCancion;
	}
	
	@Override
	public void registrarCancion(Cancion cancion) {
		Entidad eCancion = cancionToEntidad(cancion); 
		eCancion = sPersistencia.registrarEntidad(eCancion);
		cancion.setId(eCancion.getId());
	}

	@Override
	public void borrarCancion(Cancion cancion) {
		Entidad eCancion = sPersistencia.recuperarEntidad(cancion.getId());
		sPersistencia.borrarEntidad(eCancion);
	}

	@Override
	public Cancion recuperarCancion(int id) {
		if (PoolDAO.getUnicaInstancia().contiene(id))
			 return (Cancion) PoolDAO.getUnicaInstancia().getObjeto(id);
		Entidad eCancion = sPersistencia.recuperarEntidad(id);
		return entidadToCancion(eCancion);
	}

	@Override
	public List<Cancion> recuperarTodasCanciones() {
		List<Entidad> entidades = sPersistencia.recuperarEntidades(CANCION);
		List<Cancion> canciones = new LinkedList<>();
		
		for (Entidad eCancion : entidades) {
			
			canciones.add(entidadToCancion(eCancion));
		}
		
		return canciones;
	}

	@Override
	public void actualizarReproduccionesCancion(Cancion cancion) {
		Entidad eCancion = sPersistencia.recuperarEntidad(cancion.getId());
		for (Propiedad prop : eCancion.getPropiedades()) {
			if (prop.getNombre().equals(NREPRODUCCIONES)) {
				prop.setValor(cancion.getNumReproduccionesStr());
			}

			sPersistencia.modificarPropiedad(prop);
		}
	}
	
}
