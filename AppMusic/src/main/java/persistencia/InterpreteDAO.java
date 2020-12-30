package persistencia;

import java.util.List;

import modelo.Interprete;

public interface InterpreteDAO {
	
	public void registrarInterprete(Interprete interprete);
	public void borrarInterprete(Interprete interprete);
	public void modificarInterprete(Interprete interprete);
	public Interprete recuperarInterprete(int codigo);
	public List<Interprete> recuperarTodosInterpretes();

}
