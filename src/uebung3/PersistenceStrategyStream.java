package uebung3;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E extends Serializable> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "/src/uebung3/file.ser";   //tried with .ser, .tmp, .txt

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save.
     */
    @Override
    public void openConnection() throws PersistenceException {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(location);
        }
        catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "No File found");
        }
        try {
            oos = new ObjectOutputStream(fos);
        }
        catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "ObjectOutputStream fail");
        }

        FileInputStream fis;
        try {
            fis = new FileInputStream(location);
        }
        catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "No File found");
        }
        try {
            ois = new ObjectInputStream(fis);
        }
        catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "ObjectOutputStream fail");
        }
    }

    /**
     * Method for closing the connections to a stream
     */
    @Override
    public void closeConnection() throws PersistenceException {
        try {
            oos.close();
            ois.close();
        }
        catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "closing failed");
        }
    }

    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    @Override
    public void save(@NotNull List<E> list) throws PersistenceException {
        openConnection();
        try {
            oos.writeObject(list);
            oos.flush();
        }
        catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "writing failed");
        }
        closeConnection();
    }

    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    @Override
    public List<E> load() throws PersistenceException  {
        List<E> list = null;
        openConnection();
        try {
            Object obj = ois.readObject();
            if (obj instanceof List<?> newList) list = (List<E>) newList;
        }
        catch (ClassNotFoundException | IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "reading failed");
        }
        closeConnection();
        return list;
    }
}
