package modelo;

import java.util.HashMap;
import java.util.Map;

public class CatalogoUsuarios {
	Map<Integer, Usuario> usuarios;
	private static CatalogoUsuarios unicaInstancia = null;
	
	
	private CatalogoUsuarios() {
		usuarios = new HashMap<>();
	}
	
	public CatalogoUsuarios getInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoUsuarios();
		return unicaInstancia;
	}
}
