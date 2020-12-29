package persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {
	

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return TDSUsuarioDAO.getInstancia();
	}

	@Override
	public CancionDAO getCancionDAO() {
		return TDSCancionDAO.getInstancia();
	}

	@Override
	public InterpreteDAO getInterpreteDAO() {
				return TDSInterpreteDAO.getInstancia();
	}

	@Override
	public EstiloDAO getEstiloDAO() {
		return TDSEstiloDAO.getInstancia();
	}

	@Override
	public ListaCancionesDAO getListaCancionesDAO() {
		return TDSListaCancionesDAO.getInstancia();
	}

}
