package uebung3;

import java.io.Serializable;

public interface Member extends Serializable {

	/**
	 * Die ID ist über einen Konstruktor einer Klasse, welches dieses Interface implementiert,
	 * zu setzen. Die ID darf nicht innerhalb des Container-Objekts gesetzt werden.
	 * Die ID dient als Primärschlüssel zur Unterscheidung aller Member-Objekte.
	 */
	Integer getID();
}
