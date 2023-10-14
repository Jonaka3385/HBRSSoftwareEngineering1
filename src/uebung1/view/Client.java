package uebung1.view;

import uebung1.control.GermanTranslator;
import uebung1.control.Helper;

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

		System.out.println("Das Ergebnis der Berechnung: " + s);
	}
}
