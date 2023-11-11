package uebung4;

import java.io.Serializable;

public class MemberKonkret implements Member, Serializable {
	
	private Integer id;
	
 	public MemberKonkret(Integer id){
		this.id = id;  
	}

	public MemberKonkret(Integer id, String ignoredStr) {
		this.id = id;
	}

	public Integer getID() {
		return id;
	}
	
	public void setID (Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MemberKonkret [id=" + id + "]";
	}
}
