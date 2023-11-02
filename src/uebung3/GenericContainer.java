package uebung3;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GenericContainer <E extends Member> {
	
	/*
	 * Leerer Konstruktor
	 */
	public GenericContainer(){
	}

	/* 
	 * Interne ArrayList zur Abspeicherung der Objekte
	 */
	private List<E> liste = new ArrayList<E>(); // key, value
	
	/*
	 * Methode zum Hinzufuegen einer Member.
	 * @throws ContainerException
	 */ 
	public void addMember (E r) throws ContainerException {
		if (contains(r)) {
            throw new ContainerException(r.getID().toString());
		}
		liste.add(r);
	} 
	
	/*
	 * Methode zur Ueberpruefung, ob ein Member-Objekt in der Liste enthalten ist
	 * 
	 */
	private boolean contains(@NotNull E r) {
		Integer ID = r.getID();
		for (E rec : liste) {
			// wichtig: Check auf die Values innerhalb der Integer-Objekte!
			if ((rec).getID().intValue() == ID.intValue()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Methode zum Loeschen einer Member
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
	public int getAnzahl(){
		return liste.size();
	}

	
	/*
	 * Methode zur Ausgabe aller IDs der Member-Objekte
	 * 
	 */
	public void dump(){
		System.out.println("Ausgabe aller Members: ");
		for ( E p : liste ) {
			System.out.println(p.toString());
		}
	}

	/*
	 * Interne Methode zur Ermittlung einer Member
	 * 
	 */
	private @Nullable Member getMember(Integer id) {
		for (E rec : liste) {
			if (id == (rec).getID().intValue() ){
				return rec;
			}
		}
		return null;
	}
}
