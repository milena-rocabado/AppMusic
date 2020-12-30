package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import modelo.Interprete;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class TDSInterpreteDAO implements InterpreteDAO{
	
	private static TDSInterpreteDAO unicaInstancia;
	private ServicioPersistencia sPersistencia;

	private static final String INTERPRETE = "interprete";
	private static final String NOMBRE = "nombre";
	private static final String CANCIONES = "canciones";
	
	private TDSInterpreteDAO() {
		sPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public static TDSInterpreteDAO getInstancia() {
		if (unicaInstancia == null) unicaInstancia = new TDSInterpreteDAO();
		return unicaInstancia;
	}
	
	private Interprete entidadToInterprete(Entidad eInterprete) {
		String nombre = sPersistencia.recuperarPropiedadEntidad(eInterprete, NOMBRE);
		String canciones = sPersistencia.recuperarPropiedadEntidad(eInterprete, CANCIONES);
		
		Interprete interprete = new Interprete(nombre);
		interprete.setCodigo(eInterprete.getId());
		return interprete;
	}
	
	private Entidad interpreteToEntidad(Interprete interprete) {
		Entidad eInterprete = new Entidad();
		eInterprete.setNombre(INTERPRETE);
		
		eInterprete.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(NOMBRE, interprete.getNombre()))));
		return eInterprete;
	}
	
	@Override
	public void registrarInterprete(Interprete interprete) {
		Entidad eInterprete = interpreteToEntidad(interprete); 
		eInterprete = sPersistencia.registrarEntidad(eInterprete);
		interprete.setCodigo(eInterprete.getId());
	}

	@Override
	public void borrarInterprete(Interprete interprete) {
		Entidad eInterprete = sPersistencia.recuperarEntidad(interprete.getCodigo());
		sPersistencia.borrarEntidad(eInterprete);
	}

	@Override
	public Interprete recuperarInterprete(int id) {
		Entidad eInterprete = sPersistencia.recuperarEntidad(id);
		return entidadToInterprete(eInterprete);
	}

	@Override
	public List<Interprete> recuperarTodosInterpretes() {
		List<Entidad> entidades = sPersistencia.recuperarEntidades(INTERPRETE);
		
		List<Interprete> interpretes = new LinkedList<>();
		for (Entidad eInterprete : entidades)
			interpretes.add(entidadToInterprete(eInterprete));
		
		return interpretes;
	}

	@Override
	public void modificarInterprete(Interprete interprete) {
		Entidad eInterprete = sPersistencia.recuperarEntidad(interprete.getCodigo());
		sPersistencia.eliminarPropiedadEntidad(eInterprete, NOMBRE);
		sPersistencia.anadirPropiedadEntidad(eInterprete, NOMBRE, interprete.getNombre());
		
	}
	

}
