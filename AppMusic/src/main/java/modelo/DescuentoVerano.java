package modelo;

import java.time.LocalDate;

public class DescuentoVerano implements Descuento {

	@Override
	public double calcularDescuento(Usuario usuario, double precio) {
		LocalDate hoy = LocalDate.now();
		if (hoy.isAfter(LocalDate.of(hoy.getYear(), 6, 20)) && 
				hoy.isBefore(LocalDate.of(hoy.getYear(), 9, 22))) {
			return 0.1*precio;
		}
		return precio;
	}

}
