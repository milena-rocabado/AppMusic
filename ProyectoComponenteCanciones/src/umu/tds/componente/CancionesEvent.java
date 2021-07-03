package umu.tds.componente;

import java.util.EventObject;
import java.util.List;

public class CancionesEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	protected final List<CancionComponente> oldValue, newValue;

	public CancionesEvent(Object source, List<CancionComponente> cancionesOld, List<CancionComponente> cancionesNew) {
			super(source);
			this.oldValue= cancionesOld;
			this.newValue=cancionesNew;
		}

	public List<CancionComponente> getOldValue() {
		return oldValue;
	}

	public List<CancionComponente> getNewValue() {
		return newValue;
	}

	
}
