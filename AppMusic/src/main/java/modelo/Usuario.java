package modelo;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

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
	private Vector<Cancion> cancionesRecientes;

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
		this.cancionesRecientes = new Vector<>();
		
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
	
	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}

	public List<ListaCanciones> getListas() {
		return Collections.unmodifiableList(listas);
	}

	public void setListas(List<ListaCanciones> listas) {
		this.listas = new LinkedList<>(listas);
	}

	public boolean login(String clave) {
		return this.password.equals(clave);
	}
	
	public double realizarPago(double precio) {
		double total = (descuento == null) ? precio : descuento.calcularDescuento(this, precio); 
		// Se hace uso de un servicio externo para realizar el pago
		// Si se realiza con éxito:
		this.premium = true;
		return total;
	}
	
	public ListaCanciones crearLista(String nombre) {
		ListaCanciones lista = new ListaCanciones(nombre);
		System.out.println("lista creada: " + nombre);
		listas.add(lista);
		return lista;
	}

	public void addListaCanciones(ListaCanciones lista) {
		listas.add(lista);
	}
	
	public ListaCanciones getListaCanciones(String nombre) {
		for (ListaCanciones lc : listas) {
			if (lc.getNombre().equals(nombre))
				return lc;
		}
		return null;
	}

	public void actualizarListaCanciones(ListaCanciones lista) {
		listas.remove(lista);
		listas.add(lista);
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Vector<Cancion> getCancionesRecientes() {
		return cancionesRecientes;
	}

	public void setCancionesRecientes(Vector<Cancion> cancionesRecientes) {
		this.cancionesRecientes = cancionesRecientes;
	}
	
	public void addCancionReciente(Cancion cancion) {
		if (cancionesRecientes.contains(cancion)) {
				cancionesRecientes.remove(cancion);
				cancionesRecientes.add(cancion);
		}else {
			if(cancionesRecientes.size()==10) {
				cancionesRecientes.remove(0);
				cancionesRecientes.add(cancion);
			}else {
				cancionesRecientes.add(cancion);
			}
		}
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", password=" + password + ", premium=" + premium
				+ ", listas=" + listas.toString() + "]";
	}
	
	
	
}
