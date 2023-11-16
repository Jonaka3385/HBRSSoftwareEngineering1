package uebung4;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uebung4.persistence.PersistenceException;
import uebung4.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/*
 * Klasse zum Abspeichern von Objekten in einer Liste
 * c/o Sascha Alda, H-BRS, 2020-2023
 */
public class Container<T> {

	// Interne ArrayList zur Abspeicherung der Objekte
	private List<T> liste;

	private Modus modus = Modus.LIST_TYPE_ARRAY;

	// Statische Klassen-Variable, um die Referenz
	// auf das einzige Container-Objekt abzuspeichern
	// Dynamische Belegung: nur falls Methode getInstance geladen
	// wird, dann wird nach Bedarf die Variable mit einer Referenz gefüllt
	private static Container<?> instance = null;

	// Reference to the internal strategy (e.g. MongoDB or Stream)
	private PersistenceStrategy<T> strategy = null;

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
	public static Container<?> getInstance() {
		if (instance == null) {
			newInstance();
		}
		return instance;
	}

	/*
	 * Auslagern der newInstance() Methode um getInstance() performanter zu machen.
	 * Unter normalen Umständen wird newInstance sehr selten, bzw. meist nur einmal, aufgerufen.
	 * Das Auslagern der instance Erstellung führt dazu dass getInstance() nicht mehr synchronized sein muss.
	 */
	private static synchronized void newInstance() {
		if (instance == null) {
			instance = new Container<>();
			System.out.println("Objekt vom Typ Container wurde instanziiert!");
		}
	}

	/*
	 * Ueberschreiben des Konstruktors. Durch die Sichtbarkeit private
	 * kann man von aussen die Klasse nicht mehr instanziieren,
	 * sondern nur noch kontrolliert ueber die statische Methode
	 * der Klasse Container!
	 */
	private Container(){
		System.out.println("Container ist instanziiert (Konstruktor)!");
		switch (modus) {
			case Modus.LIST_TYPE_ARRAY:
				liste = new ArrayList<>();
				break;
			case Modus.LIST_TYPE_LINKED:
				liste = new LinkedList<>();
				break;
			case Modus.LIST_TYPE_CUSTOM:
				System.out.println("No custom Mode implemented. Using default (Array).");
				modus = Modus.LIST_TYPE_ARRAY;
				liste = new ArrayList<>();
				break;
			default:
				System.out.println("No mode set. Using default (Array).");
				modus = Modus.LIST_TYPE_ARRAY;
				liste = new ArrayList<>();
				break;
		}
	}

	public void setModusWithString(@NotNull String str) {
		switch (str) {
			case "Array":
				modus = Modus.LIST_TYPE_ARRAY;
				break;
			case "Linked":
				modus = Modus.LIST_TYPE_LINKED;
				break;
			case "Custom":
				modus = Modus.LIST_TYPE_CUSTOM;
				break;
			default: break;
		}
	}

	/**
	 * Method for getting the internal list. e.g. from a View-object
	 * @return list
	 */
	public List<T> getCurrentList() {
		return liste;
	}

	/*
	 * Method for adding T-objects
	 * @param t T
	 * @throws ContainerException exception
	 */
	public void addObject(T t) throws ContainerException {
		if (contains(t)) {
			System.out.println("Duplikat: " + t);
			ContainerException exception = new ContainerException(ContainerException.ExceptionType.DuplicateObject);
			exception.setObject(t);
            throw exception;
		}
		liste.add(t);
	}

	/*
	 * Methode zur Ueberpruefung, ob ein T-Objekt in der Liste enthalten ist
	 */
	private boolean contains(@NotNull T t) {
		for (T rec : liste) {
			if (Objects.equals(rec, t)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Method for deleting an object with a given id.
	 */
	public String deleteObject(T t) {
		T rec = getObject(t);
		if (rec == null) return "Object nicht enthalten - ERROR"; else {
			liste.remove(rec);
			return "Object " + t + " konnte geloescht werden";
		}
	}

	/*
	 * Method for getting the number of currently stored objects
	 */
	public int size(){
		return liste.size();
	}

	/*
	 * Interne Methode zur Ermittlung eines T-Objects
	 */
	private @Nullable T getObject(T t) {
		for (T rec : liste) {
			if (Objects.equals(rec, t)){
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
		if (strategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Strategy not initialized");
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
	public void setPersistenceStrategie(PersistenceStrategy<T> persistenceStrategy) {
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
		if (strategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Strategy not initialized");
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

	public void selfDelete() {
		try {
			closeConnection();
		} catch (Exception e) {
			System.out.println("SelfDelete Exception: " + e);
		}

		switch (modus) {
			case Modus.LIST_TYPE_ARRAY:
				liste = new ArrayList<>();
				break;
			case Modus.LIST_TYPE_LINKED:
				liste = new LinkedList<>();
				break;
			case Modus.LIST_TYPE_CUSTOM:
				System.out.println("No custom Mode implemented. Using default (Array).");
				modus = Modus.LIST_TYPE_ARRAY;
				liste = new ArrayList<>();
				break;
			default:
				System.out.println("No mode set. Using default (Array).");
				modus = Modus.LIST_TYPE_ARRAY;
				liste = new ArrayList<>();
				break;
		}

		instance = null;
	}
}
