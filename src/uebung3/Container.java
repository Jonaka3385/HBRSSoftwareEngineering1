package uebung3;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

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

	public List<Member> getCurrentList() {
		return liste;
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

	public void store() throws PersistenceException {
        var p = new PersistenceStrategyStream<Member>();
        p.save(liste);
    }

	public void load() throws PersistenceException {
		var p = new PersistenceStrategyStream<Member>();
		liste = p.load();
	}
}
