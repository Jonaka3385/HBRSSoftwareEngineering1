package uebung3;

/**
 * Checked Exception (always subclasses from class Exception)
 * Checked during compilation, must be catched in the calling client class
 * In comparison: unchecked exception (subclass of RuntimeException) does not need to be catched
 */
public class ContainerException extends Exception {

	public ContainerException(String id) {
		super ("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");
	}

	public ContainerException() {
		super ("NULL-Werte dürfen nicht aufgenommen werden!");
	}
}
