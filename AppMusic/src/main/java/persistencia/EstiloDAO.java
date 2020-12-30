package persistencia;

import java.util.List;

import modelo.Estilo;

public interface EstiloDAO {
	
	public void registrarEstilo(Estilo estilo);
	public void borrarEstilo(Estilo estilo);
	public void modificarEstilo(Estilo estilo);
	public Estilo recuperarEstilo(int codigo);
	public List<Estilo> recuperarTodosEstilos();
	
}
