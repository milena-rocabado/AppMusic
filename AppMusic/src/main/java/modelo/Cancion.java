package modelo;

public final class Cancion {
	private int id;
	private final String titulo;
	private final Interprete interprete;
	private int numReproducciones;
	private Estilo estilo;
	
	public Cancion(String titulo, Interprete interprete, Estilo estilo, int nreproducciones) {
		this.titulo = titulo;
		this.interprete = interprete;
		this.numReproducciones = nreproducciones;
		this.estilo = estilo;
		interprete.addCancionInterprete(this);
	}
	
	public Cancion(String titulo, Interprete interprete, Estilo estilo, String nreproducciones) {
		this(titulo, interprete, estilo, Integer.parseInt(nreproducciones));
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public Interprete getInterprete() {
		return interprete;
	}

	public String getRutaFichero() {
		return null; // se construye con estilo, interpretes y titulo
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}
	
	public String getNumReproduccionesStr() {
		return Integer.toString(numReproducciones);
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public void reproducirCancion() {
		numReproducciones++;
	}

	public boolean esInterpretadaPor(String nombre) {
		return this.interprete.equals(nombre);
	}
	
	public boolean esEstiloMusical(String estilo) {
		return this.estilo.equals(estilo);
	}
}
