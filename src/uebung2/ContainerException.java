package uebung2;

public class ContainerException extends Exception {
    public ContainerException(String id) {
        super("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");
    }
}
