package umu.tds.componente;

import java.util.EventListener;
import java.util.EventObject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "CancionesEvent", propOrder = {})
public interface ICancionesListener extends EventListener {

	public void enteradoCambioCanciones (CancionesEvent e);
}