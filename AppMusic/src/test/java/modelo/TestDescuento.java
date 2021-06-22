package modelo;

import java.time.LocalDate;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestDescuento {
	private Usuario usuario1;
	private Usuario usuario2;
	
	private Descuento descuentoVerano1;
	private Descuento descuentoVerano2;
	private Descuento descuentoMayores;
	
	@Before
	public void setUp() {
		usuario1 = new Usuario("david-2000", "1234", "david@um.es", "David", "Pino", LocalDate.of(2000, 8, 29));
		usuario2 = new Usuario("moonstar", "4321", "luna@um.es", "Luna", "Berenguer", LocalDate.of(1955, 3, 25));
		
		descuentoVerano1 = new DescuentoVerano(() -> LocalDate.of(2021, 6, 22));
		descuentoVerano2 = new DescuentoVerano(() -> LocalDate.of(2021, 1, 1));
		descuentoMayores = new DescuentoMayor65();
	}
	
	@Test
	public void testDescuentoVeranoAplicado() {
		usuario1.setDescuento(descuentoVerano1);
		double total = usuario1.realizarPago(9.99);
		
		assertEquals("Descuento de verano aplicado", total, 9.99*0.9, 0.01);
	}
	
	@Test
	public void testDescuentoVeranoNoAplicado() {
		usuario1.setDescuento(descuentoVerano2);
		double total = usuario1.realizarPago(9.99);
		
		assertEquals("Descuento de verano no aplicado", total, 9.99, 0.01);
	}
	
	@Test
	public void testDescuentoMayor65Aplicado() {
		usuario2.setDescuento(descuentoMayores);
		double total = usuario2.realizarPago(9.99);
		
		assertEquals("Descuento mayores de 65 aplicado", total, 9.99*0.5, 0.01);
	}
	
	@Test
	public void testDescuentoMayor65NoAplicado() {
		usuario1.setDescuento(descuentoMayores);
		double total = usuario1.realizarPago(9.99);
		
		assertEquals("Descuento mayores de 65 no aplicado", total, 9.99, 0.01);
	}
}
