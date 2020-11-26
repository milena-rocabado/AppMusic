package modelo;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	private int codigo;
	private String usuario;
	private String clave;
	private String email;
	private String nombre;
	private String apellidos;
	private LocalDate fecha;
	private boolean premium;
	private Descuento descuento;
	private List<ListaCanciones> listas;

	public Usuario(String usuario, String clave, String email, String nombre, String apellidos, LocalDate fecha) {
		this.usuario = usuario;
		this.clave = clave;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha = fecha;
		this.premium = false;
		this.listas = new LinkedList<>();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String password) {
		this.clave = password;
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public Descuento getDescuento() {
		return descuento;
	}

	public List<ListaCanciones> getListas() {
		return Collections.unmodifiableList(listas);
	}

	public boolean login(String clave) {
		return this.clave.equals(clave);
	}
	
	public void realizarPago() {}
	
	public ListaCanciones crearLista(String nombre) {
		ListaCanciones lista = new ListaCanciones(nombre);
		listas.add(lista);
		return lista;
	}
}
