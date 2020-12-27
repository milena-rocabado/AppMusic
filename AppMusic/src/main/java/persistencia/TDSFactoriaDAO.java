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

}
