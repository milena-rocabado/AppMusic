package modelo;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	private int id;
	private String usuario;
	private String password;
	private String email;
	private String nombre;
	private String apellidos;
	private LocalDate fecha;
	private boolean premium;
	private Descuento descuento;
	private List<ListaCanciones> listas;

	public Usuario(String usuario, String clave, String email, String nombre, String apellidos, String fecha) {
		this.id = -1;
		this.usuario = usuario;
		this.password = clave;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha = LocalDate.parse(fecha);
		this.premium = false;
		this.listas = new LinkedList<>();
	}
	
	public Usuario(String usuario, String clave, String email, String nombre, String apellidos, LocalDate fecha) {
		this(usuario, clave, email, nombre, apellidos, fecha.toString());
	}

	public int getId() {
		return id;
	}

	public void setId(int codigo) {
		this.id = codigo;
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	public LocalDate getFechaNacimiento() {
		return fecha;
	}
	
	public String getFechaNacimientoStr() {
		return fecha.toString();
	}
	
	public void setFechaNacimiento(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public Descuento getDescuento() {
		return descuento;
	}

	public List<ListaCanciones> getListas() {
		return Collections.unmodifiableList(listas);
	}
	

	public void setListas(List<ListaCanciones> listas) {
		this.listas = listas;
	}

	public boolean login(String clave) {
		return this.password.equals(clave);
	}
	
	public void realizarPago() {}
	
	public ListaCanciones crearLista(String nombre) {
		ListaCanciones lista = new ListaCanciones(nombre);
		listas.add(lista);
		return lista;
	}
}
