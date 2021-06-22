package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class DescuentoMayor65 implements Descuento {

	@Override
	public double calcularDescuento(Usuario usuario, double precio) {
		int edad = LocalDate.now().getYear() - usuario.getFechaNacimiento().getYear();
		if (edad >= 65 &&
			usuario.getFechaNacimiento().with(ChronoField.YEAR, LocalDate.now().getYear()).isBefore(LocalDate.now())) {
			return precio*0.5;
		}
		return precio;
	}

}
