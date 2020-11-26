package modelo;

public final class Cancion {
	private int codigo;
	private final String titulo;
	private final String interprete;
	private int numReproducciones;
	private EstiloMusical estilo;
	
	public Cancion(String titulo, String interprete, EstiloMusical estilo) {
		this.codigo = 0;
		this.titulo = titulo;
		this.interprete = interprete;
		this.numReproducciones = 0;
		this.estilo = estilo;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getInterprete() {
		return interprete;
	}

	public String getRutaFichero() {
		return null; // se construye con estilo, interpretes y titulo
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}

	public EstiloMusical getEstilo() {
		return estilo;
	}

	public void setEstilo(EstiloMusical estilo) {
		this.estilo = estilo;
	}

	public void reproducirCancion() {
		numReproducciones++;
	}

	public boolean esInterpretadaPor(String nombre) {
		return this.interprete.equals(nombre);
	}
	
	public boolean esEstiloMusical(EstiloMusical estilo) {
		return this.estilo.equals(estilo);
	}
}
