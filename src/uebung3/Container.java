package uebung3;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Container {
	private static Container instance;

	/*
	 * Interne ArrayList zur Abspeicherung der Objekte
	 * Alternative: HashMap oder Set. HashMap hat vor allem Probleme 
	 * bei der Bewahrung der Konsistenz vom Key und Value (siehe TestStore, letzter Test)
	 */
	private List<Member> liste = new ArrayList<>();

	public static synchronized Container getInstance() {
		if(instance==null) instance = new Container();
		return instance;
	}
	
	/*
	 * Methode zum Hinzufuegen einer Member.
	 * @throws ContainerException
	 */ 
	public void addMember(Member r) throws ContainerException {
		if (r == null) {
            throw new ContainerException();
		}

		if (contains(r)) {
            throw new ContainerException(r.getID().toString());
		}
		liste.add(r);
	} 
	
	/*
	 * Methode zur Ueberpruefung, ob ein Member-Objekt in der Liste enthalten ist
	 * 
	 */
	private boolean contains(@NotNull Member r) {
		Integer ID = r.getID();
		for ( Member rec : liste) {
			// wichtig: Check auf die Values innerhalb der Integer-Objekte!
			if (rec.getID().intValue() == ID.intValue()) {
				return true;
			}
		}
		return false;
		
		// liste.contains(r), falls equals-Methode in Member ueberschrieben.
	}
	/*
	 * Methode zum Loeschen einer Member
	 * In Praxis durchaus verwendet: C-Programme; beim HTTP-Protokoll; SAP-Anwendung (R3); Mond-Landung ;-)
	 * 
	 */
	public String deleteMember(Integer id) {
		Member rec = getMember(id);
		if (rec == null) return "Member nicht enthalten - ERROR";
		else {
			liste.remove(rec);
			return "Member mit der ID " + id + " konnte geloescht werden";
		}
	}
	
	/*
	 * Methode zur Bestimmung der Anzahl der von Member-Objekten
	 * Aufruf der Methode size() aus List
	 * 
	 */
	public int size(){
		return liste.size();
	}

	
	/*
	 * Methode zur Ausgabe aller IDs der Member-Objekte. Es werden verschiedene Varianten vorgestellt!
	 * Fuer eine ordnungsgemaesse Ausgabe sollten die unpassenden Varianten und Loesungen
	 * natuerlich auskommentiert werden.
	 * 
	 */
	public void dump(){
		System.out.println("Ausgabe aller Member-Objekte: ");

		liste.forEach(System.out::println);

		// Ueber die Streaming-Funktion koennen weitere Filterungen durchgefuehrt werden
		// Dieses Vorgehen wird auch als Pattern Filter-Map-Reduce bezeichnet (vgl. Kapitel 4 bzw. 6
		// sowie die Uebung Nr. 4:		
		// Variante 4.1 (mit zwei Filtern und mit foreach als Reduzierung (reduce)):
		liste.stream() // Parallelisierung hier moeglich mit .parallelstream()
		      .filter(element -> element.getID() > 20)
			  .filter(element -> element.getID() < 1000)
			  .forEach(System.out::println);

		// Variante 4.2 (mit zwei Filtern und einer Reduzierung (reduce) auf eine Liste ohne foreach):		
		List<Integer> newListe = liste.stream() // Parallelisierung hier moeglich mit .parallelstream()
								      .filter(element -> element.getID() > 20)
								      .filter(element -> element.getID() < 1000)
								      .map(Member::getID)
								      .collect( Collectors.toList() );
								     
		System.out.println(newListe);

        // Loesung Nr. 4:
        // Vorteil: Möglichkeit der parallelen Verarbeitung der Liste möglich
        // Erhöht die Performance der Anwendung
        liste.parallelStream().forEach(System.out::println);


        // Variante fuer die Variante Nr. 4.2:
		// Achtung: diese Variante ist im Gegensatz zur 4.2 nicht parallelisierbar!
		List<Integer> newListe2 = new ArrayList<>();
		for (Member p : liste) {
			if (p.getID() > 20 && p.getID()<1000) {
				newListe2.add(p.getID());
			}
		}
		System.out.println(newListe2);
	}

	/*
	 * Interne Methode zur Ermittlung einer Member
	 * 
	 */
	private @Nullable Member getMember(Integer id) {
		for (Member rec : liste) {
			if (id == rec.getID().intValue()){
				return rec;
			}
		}
		return null;
	}
}
