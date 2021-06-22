package modelo;

import java.time.LocalDate;
import java.util.function.Supplier;

public class DescuentoVerano implements Descuento {

	private Supplier<LocalDate> dateSupplier;
	
	public DescuentoVerano(Supplier<LocalDate> dateSupplier) {
		super();
		this.dateSupplier = dateSupplier;
	}
	
	public DescuentoVerano() {
		this(LocalDate::now);
	}
	
	@Override
	public double calcularDescuento(Usuario usuario, double precio) {
		LocalDate hoy = dateSupplier.get();
		if (hoy.isAfter(LocalDate.of(hoy.getYear(), 6, 20)) && 
				hoy.isBefore(LocalDate.of(hoy.getYear(), 9, 22))) {
			return 0.9*precio;
		}
		return precio;
	}

}
