package org.hbrs.se1.ws23.uebung1.control;

public class GermanTranslator implements Translator {

	public String date = "Okt/2023"; // Default-Wert

	/*
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	public String translateNumber(int number) {
		// [ihr Source Code aus Übung 1-2]
		String s = String.valueOf(number);
		s = s.replaceAll("1", "eins");
		s = s.replaceAll("2", "zwei");
		s = s.replaceAll("3", "drei");
		s = s.replaceAll("4", "vier");
		s = s.replaceAll("5", "fuenf");
		s = s.replaceAll("6", "sechs");
		s = s.replaceAll("7", "sieben");
		s = s.replaceAll("8", "acht");
		s = s.replaceAll("9", "neun");
		s = s.replaceAll("10", "zehn");

		return s;
	}

	/*
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	public void printInfo() {
		System.out.println("GermanTranslator v1.9, erzeugt am " + this.date);
	}

	/*
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: "Okt/2022"))
	 * Das Datum sollte system-intern durch eine Control-Klasse gesetzt werden und nicht von externen View-Klassen
	 */
	public void setDate(String date) {
		this.date = date;
	}
}
