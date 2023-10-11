package org.hbrs.se1.ws23.uebung1.view;

import org.hbrs.se1.ws23.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws23.uebung1.control.Helper;

public class Client {
	/*
	 * Methode zur Ausgabe einer Zahl auf der Console
	 * (auch bezeichnet als CLI, Terminal)
	 *
	 */
	public void display(int aNumber){
		// In dieser Methode soll die Methode translateNumber
		// mit dem uebergebenen Wert der Variable aNumber
		// aufgerufen werden.
		//
		// Strenge Implementierung gegen das Interface Translator gewuenscht!
		GermanTranslator translater = Helper.getGermanTranslator();
		String s = translater.translateNumber(aNumber);

		System.out.println("Das Ergebnis der Berechnung: " + s + "[das Ergebnis an dieser Stelle]"  );
	}
}
