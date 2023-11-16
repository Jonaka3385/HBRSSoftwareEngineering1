package uebung4;

public class ContainerException extends Exception {
	private Object object;
	private final ExceptionType type;
	
	public ContainerException(ExceptionType type) {
		this.type = type;
	}
 
	@Override
	public void printStackTrace() {
		if (type == ExceptionType.DuplicateObject) {
			System.out.println("Das Member-Objekt mit der ID " + object + " ist bereits vorhanden!");
		} else if (type == ExceptionType.InfoCastException) {
		    System.out.println("Das Member-Objekt mit der ID " + object + " konnte nicht gecastet werden!");
		} 
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	public enum ExceptionType {
		InfoCastException, DuplicateObject
	}
}
