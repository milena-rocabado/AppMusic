package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import modelo.Estilo;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class TDSEstiloDAO implements EstiloDAO{
	
	private static TDSEstiloDAO unicaInstancia;
	private ServicioPersistencia sPersistencia;

	private static final String ESTILO = "estilo";
	private static final String NOMBRE = "nombre";
	
	private TDSEstiloDAO() {
		sPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public static TDSEstiloDAO getInstancia() {
		if (unicaInstancia == null) unicaInstancia = new TDSEstiloDAO();
		return unicaInstancia;
	}
	
	private Estilo entidadToEstilo(Entidad eEstilo) {
		String nombre = sPersistencia.recuperarPropiedadEntidad(eEstilo, NOMBRE);
		
		Estilo estilo = new Estilo(nombre);
		estilo.setCodigo(eEstilo.getId());
		return estilo;
	}
	
	private Entidad estiloToEntidad(Estilo estilo) {
		Entidad eEstilo = new Entidad();
		eEstilo.setNombre(ESTILO);
		
		eEstilo.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(NOMBRE, estilo.getNombre()))));
		return eEstilo;
	}
	
	@Override
	public void registrarEstilo(Estilo estilo) {
		Entidad eEstilo = estiloToEntidad(estilo); 
		eEstilo = sPersistencia.registrarEntidad(eEstilo);
		estilo.setCodigo(eEstilo.getId());
	}

	@Override
	public void borrarEstilo(Estilo estilo) {
		Entidad eEstilo = sPersistencia.recuperarEntidad(estilo.getCodigo());
		sPersistencia.borrarEntidad(eEstilo);
	}

	@Override
	public Estilo recuperarEstilo(int id) {
		Entidad eEstilo = sPersistencia.recuperarEntidad(id);
		return entidadToEstilo(eEstilo);
	}

	@Override
	public List<Estilo> recuperarTodosEstilos() {
		List<Entidad> entidades = sPersistencia.recuperarEntidades(ESTILO);
		
		List<Estilo> estilos = new LinkedList<>();
		for (Entidad eEstilo : entidades)
			estilos.add(entidadToEstilo(eEstilo));
		
		return estilos;
	}

	@Override
	public void modificarEstilo(Estilo estilo) {
		Entidad eEstilo = sPersistencia.recuperarEntidad(estilo.getCodigo());
		sPersistencia.eliminarPropiedadEntidad(eEstilo, NOMBRE);
		sPersistencia.anadirPropiedadEntidad(eEstilo, NOMBRE, estilo.getNombre());
		
	}
	
}
