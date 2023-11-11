package uebung4.persistence;

import java.util.List;

/**
 * Interface for defining basic methods for a persistence mechanism
 * Each concrete algorithm (i.e. strategy) must implement this method
 * This interface corresponds to the abstract strategy w.r.t. to the
 * Strategy Design Pattern (GoF).
 * The following protocol applies:
 * 1. openConnection
 * 2. { load | save }  (many times)
 * 3. closeConnection
 *
 * @param <E>
 */

public interface PersistenceStrategy<E> {
    void openConnection() throws PersistenceException;
    void closeConnection() throws PersistenceException;
    void save(List<E> member) throws PersistenceException;
    List<E> load() throws PersistenceException;
}
