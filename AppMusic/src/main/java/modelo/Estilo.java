package modelo;

public class Estilo {
	
	private String nombre;
	private int codigo;
	
	public Estilo(String nombre) {
		codigo=0;
		this.nombre = nombre;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	

}
