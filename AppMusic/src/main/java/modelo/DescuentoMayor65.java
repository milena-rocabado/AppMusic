package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class DescuentoMayor65 implements Descuento {

	private double factor;
	
	public DescuentoMayor65(double factor) {
		this.factor = factor;
	}
	
	public DescuentoMayor65() {
		this(0.5);
	}
	
	@Override
	public double calcularDescuento(Usuario usuario, double precio) {
		int edad = LocalDate.now().getYear() - usuario.getFechaNacimiento().getYear();
		if (edad >= 65 &&
			usuario.getFechaNacimiento().with(ChronoField.YEAR, LocalDate.now().getYear()).isBefore(LocalDate.now())) {
			return precio*factor;
		}
		return precio;
	}

}
