//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.10.26 a las 12:42:42 AM CEST 
//

package umu.tds.componente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para anonymous complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cancion" type="{http://www.tds.es/canciones}Cancion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Canciones", propOrder = { "cancion", "cancionesListeners" })
@XmlRootElement(name = "canciones")
public class Canciones implements Serializable {

	protected Vector cancionesListeners = new Vector();

	protected List<CancionComponente> cancion;

	/**
	 * Gets the value of the cancion property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the cancion property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getCancion().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link CancionComponente }
	 * 
	 * 
	 */
	public Canciones() {
		if (cancion == null) {
			cancion = new ArrayList<CancionComponente>();
		}
	}

	public List<CancionComponente> getCancion() {
		return this.cancion;
	}

	public void setArchivoCanciones(String file) {
		List<CancionComponente> cancionesOld;
		List<CancionComponente> cancionesFile;
		cancionesFile = MapperCancionesXMLtoJava.cargarCanciones(file).getCancion();
		if (cancionesFile == null) {
			return;
		}
		synchronized (this.cancion) {
			cancionesOld = new ArrayList<>(this.cancion);
		}
		List<CancionComponente> cancionesNew = nuevasCanciones(cancionesFile, cancionesOld);		
		if (cancionesNew.size()!=0) {
			CancionesEvent evento = new CancionesEvent(this, cancionesOld, cancionesNew);
			notificarCambioCanciones(evento);
		}
	}

	public synchronized void addCancionesListener(ICancionesListener listener) {
		cancionesListeners.addElement(listener);
	}

	public synchronized void removeCancionesListener(ICancionesListener listener) {
		cancionesListeners.removeElement(listener);
	}

	private void notificarCambioCanciones(CancionesEvent evento) {
		Vector lista;
		synchronized (this) {
			lista = (Vector) cancionesListeners.clone();
		}
		for (int i = 0; i < lista.size(); i++) {
			ICancionesListener listener = (ICancionesListener) lista.elementAt(i);
			listener.enteradoCambioCanciones(evento);
		}
	}
	
	public List<CancionComponente> nuevasCanciones(List<CancionComponente> listaNueva, List<CancionComponente> listaVieja){
		List<CancionComponente> cancionesNew = new ArrayList<>();
		List<CancionComponente> cancionesActualizada = new ArrayList<>(listaVieja);
		for(int i =0;  i<listaNueva.size();i++) {
			CancionComponente c1 = listaNueva.get(i);
			if(!listaVieja.contains(c1)) {
				cancionesNew.add(listaNueva.get(i));
				cancionesActualizada.add(listaNueva.get(i));
			}		
		}
		synchronized (this.cancion) {
			this.cancion = cancionesActualizada;
		}
		return cancionesNew;
	}



}
