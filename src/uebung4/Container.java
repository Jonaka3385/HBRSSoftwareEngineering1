package uebung4;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uebung4.persistence.PersistenceException;
import uebung4.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/*
 * Klasse zum Abspeichern von Objekten in einer Liste
 *
 * c/o Sascha Alda, H-BRS, 2020-2023
 *
 */
public class Container {

	// Interne ArrayList zur Abspeicherung der Objekte
	private List<Member> liste;

	//Statische Klassen-Variable, um die Referenz
	//auf das einzige Container-Objekt abzuspeichern
	// Dynamische Belegung: nur falls Methode getInstance geladen
	// wird, dann wird nach Bedarf die Variable mit einer Referenz gefüllt
	private static Container instance = null; // = new Container();

	// Reference to the internal strategy (e.g. MongoDB or Stream)
	private PersistenceStrategy<Member> strategy = null;

	// Flag to see, if a connection is opened
	private boolean connectionOpen = false;

	/*
	 * Statische Methode um die einzige Instanz der Klasse
	 * Container zu bekommen. Das Keyword synchronized bewirkt,
	 * dass garantiert nur ein Objekt den alleinigen Zugriff
	 * auf diese Methode hat. Ansonsten koennte es passieren, dass
	 * zwei parallel zugreifende Objekte zwei unterschiedliche
	 * Objekte erhalten (vgl. auch Erlaeuterung in Uebung)
	 */
	public static synchronized Container getInstance() {
		if (instance == null) {
			instance = new Container();
			System.out.println("Objekt vom Typ Container wurde instanziiert!");
		}
		return instance;
	}

	/*
	 * Ueberschreiben des Konstruktors. Durch die Sichtbarkeit private
	 * kann man von aussen die Klasse nicht mehr instanziieren,
	 * sondern nur noch kontrolliert ueber die statische Methode
	 * der Klasse Container!
	 */
	private Container(){
		System.out.println("Container ist instanziiert (Konstruktor)!");
		this.liste = new ArrayList<>();
	}

	/**
	 * Method for getting the internal list. e.g. from a View-object
	 * @return list
	 */
	public List<Member> getCurrentList() {
		return this.liste;
	}

	/*
	 * Method for adding Member-objects
	 * @param r member
	 * @throws ContainerException exception
	 */
	public void addMember (Member r) throws ContainerException {
		if (contains(r)) {
			System.out.println("Duplikat: " + r);
			ContainerException ex = new ContainerException(ContainerException.ExceptionType.DuplicateMember);
			ex.addID (r.getID());
			throw ex;
		}
		liste.add(r);
	}

	/*
	 * Methode zur Ueberpruefung, ob ein Member-Objekt in der Liste enthalten ist
	 */
	private boolean contains(@NotNull Member r) {
		Integer ID = r.getID();
		for (Member rec : liste) {
			if (Objects.equals(rec.getID(), ID)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Method for deleting an object with a given id.
	 */
	public String deleteMember(Integer id) {
		Member rec = getMember(id);
		if (rec == null) return "Member nicht enthalten - ERROR"; else {
			liste.remove(rec);
			return "Member mit der ID " + id + " konnte geloescht werden";
		}
	}

	/*
	 * Method for getting the number of currently stored objects
	 */
	public int size(){
		return liste.size();
	}

	/*
	 * Interne Methode zur Ermittlung eines Member
	 */
	private @Nullable Member getMember(Integer id) {
		for (Member rec : liste) {
			if (id == rec.getID().intValue()){
				return rec;
			}
		}
		return null;
	}

	/**
	 * Method for loading objects. Uses the internally stored strategy object
	 * @throws PersistenceException exception
	 */
	public void load() throws PersistenceException {
		if (strategy == null)
			throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Strategy not initialized");

		if (!connectionOpen) {
			openConnection();
			connectionOpen = true;
		}
        this.liste = strategy.load();
	}

	/**
	 * Method for setting the Persistence-Strategy from outside.
	 * If a new strategy is set, then the old one is closed before (if available)
	 * (not really relevant in the context of this assignment)
	 *
	 * @param persistenceStrategy strategy
	 */
	public void setPersistenceStrategie(PersistenceStrategy<Member> persistenceStrategy) {
		if (connectionOpen) {
			try {
				closeConnection();
			} catch (PersistenceException e) {
				e.printStackTrace();
			}
		}
		strategy = persistenceStrategy;
	}

	/**
	 * Method for storing objects. Uses the internally stored strategy object
	 * @throws PersistenceException exception
	 */
	public void store() throws PersistenceException {
		if (strategy == null)
			throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Strategy not initialized");

		if (!connectionOpen) {
			openConnection();
			connectionOpen = true;
		}
		strategy.save(this.liste);
	}

	private void openConnection() throws PersistenceException {
		try {
			strategy.openConnection();
			connectionOpen = true;
		} catch(UnsupportedOperationException e) {
			throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Not implemented!");
		}
	}

	private void closeConnection() throws PersistenceException {
		try {
			strategy.closeConnection();
			connectionOpen = false;
		} catch(UnsupportedOperationException e) {
			throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable , "Not implemented!");
		}
	}
}
