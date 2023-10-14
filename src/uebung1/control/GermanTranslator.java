package uebung1.control;

public class GermanTranslator implements Translator {

	public String date = "Okt/2023"; // Default-Wert

	/*
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	public String translateNumber(int number) {
		// [ihr Source Code aus Übung 1-2]
		double version = Translator.version;
		String s = "Uebersetzung der Zahl " + number + " nicht möglich " + version;
		String test = "Uebersetzung der Zahl " + 1 + " nicht möglich " + version;
		s = s.replaceAll(test, "eins");
		test = "Uebersetzung der Zahl " + 2 + " nicht möglich " + version;
		s = s.replaceAll(test, "zwei");
		test = "Uebersetzung der Zahl " + 3 + " nicht möglich " + version;
		s = s.replaceAll(test, "drei");
		test = "Uebersetzung der Zahl " + 4 + " nicht möglich " + version;
		s = s.replaceAll(test, "vier");
		test = "Uebersetzung der Zahl " + 5 + " nicht möglich " + version;
		s = s.replaceAll(test, "fuenf");
		test = "Uebersetzung der Zahl " + 6 + " nicht möglich " + version;
		s = s.replaceAll(test, "sechs");
		test = "Uebersetzung der Zahl " + 7 + " nicht möglich " + version;
		s = s.replaceAll(test, "sieben");
		test = "Uebersetzung der Zahl " + 8 + " nicht möglich " + version;
		s = s.replaceAll(test, "acht");
		test = "Uebersetzung der Zahl " + 9 + " nicht möglich " + version;
		s = s.replaceAll(test, "neun");
		test = "Uebersetzung der Zahl " + 10 + " nicht möglich " + version;
		s = s.replaceAll(test, "zehn");

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
