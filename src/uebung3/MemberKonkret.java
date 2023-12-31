package uebung3;

public class MemberKonkret implements Member {
	private Integer id;
	
	public MemberKonkret(Integer id){
		this.id = id;
	}

	@Override
	public Integer getID() { 
		return this.id;
	}
	
	public void setID (Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MemberKonkret [id=" + id + "]";
	}
}
