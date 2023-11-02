package uebung3.persistence;

/**
 * Checked Exception (always subclasses from class Exception)
 * Checked during compilation, must be catched in the calling client class
 * In comparision: unchecked exception (subclass of RuntimeException) do not need to be catched
 */
public class ContainerException extends Exception {

	private Integer id;

	public ContainerException(  String id  ) {
		super ("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");
	}

	public ContainerException( ) {
		super ("NULL-Werte dürfen nicht aufgenommen werden!");
	}

}
