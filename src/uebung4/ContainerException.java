package uebung4;

public class ContainerException extends Exception {
	
	private String modus;
	private Integer id;
	private ExceptionType type;
	
	public ContainerException(ExceptionType type) {
		this.type = type;
	}
 
	@Override
	public void printStackTrace() {
		if (type == ExceptionType.DuplicateMember) {
			System.out.println("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");
		} else if (type == ExceptionType.InfoCastException) {
		    System.out.println("Das Member-Objekt mit der ID " + id + " konnte nicht gecastet werden!");
		} 
	} 

	public void addID(Integer id) {
		this.id = id;
	}
	
	public enum ExceptionType {
		InfoCastException, DuplicateMember
	}
}
