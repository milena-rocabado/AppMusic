package modelo;

import java.util.Objects;


public final class Cancion {
	private int id;
	private final String titulo;
	private final String interprete;
	private final String url;
	private int numReproducciones;
	private String estilo;
	
	
	
	public Cancion(String titulo, String interprete, String estilo, String url, int nreproducciones) {
		this.titulo = titulo;
		this.interprete = interprete;
		this.numReproducciones = nreproducciones;
		this.url = url;
		this.estilo = estilo;
	}
	
	public Cancion(String titulo, String interprete, String estilo, String url, String nreproducciones) {
		this(titulo, interprete, estilo,url, Integer.parseInt(nreproducciones));
	}
	
	public Cancion(String titulo, String interprete, String estilo, String url) {
		this(titulo, interprete, estilo, url, 0);
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
	
	public String getInterprete() {
		return interprete;
	}

	public String getRutaFichero() {
		return ""+interprete+" - "+titulo+".mp3"; // se construye con estilo, interpretes y titulo
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}
	
	public String getNumReproduccionesStr() {
		return Integer.toString(numReproducciones);
	}

	public String getEstilo() {
		return estilo;
	}
	
	

	public String getUrl() {
		return url;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public void reproducirCancion() {
		numReproducciones++; 
	} 

	public boolean esInterpretadaPor(String nombre) {
		return this.interprete.toLowerCase().contains(nombre.toLowerCase());
	}
	
	public boolean esEstiloMusical(String estilo) {
		return this.estilo.equals(estilo);
	}
	
	public boolean tieneTitulo(String titulo) {
		return this.titulo.toLowerCase().contains(titulo.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cancion other = (Cancion) obj;
		return Objects.equals(estilo, other.estilo) && Objects.equals(interprete, other.interprete)
				 && Objects.equals(titulo, other.titulo) && Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "Cancion [id=" + id + ", titulo=" + titulo + ", interprete=" + interprete + ", url=" + url
				+ ", numReproducciones=" + numReproducciones + ", estilo=" + estilo + "]";
	}		
	
	
}
