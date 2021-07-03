package umu.tds.componente;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class MapperCancionesXMLtoJava {

	public static Canciones cargarCanciones(String fichero) {

		JAXBContext jc;
		Canciones canciones = null;
		try {
			jc = JAXBContext.newInstance("umu.tds.componente");
			Unmarshaller u = jc.createUnmarshaller();
			File file = new File(fichero);
			canciones = (Canciones) u.unmarshal(file);
		} catch (JAXBException e) {
			System.err.println("El contenido del fichero no es correcto para hacer la"
					+ " conversion de xml a cancion o no se ha encontrado el fichero en la ruta indicada");
			System.err.println("Error generado en el componente CargarCanciones, clase MapperCancionesXMLtoJava");
			e.printStackTrace();
		}	
		return canciones;
	}
}
