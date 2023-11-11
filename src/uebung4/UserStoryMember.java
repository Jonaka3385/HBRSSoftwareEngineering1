package uebung4;

import java.io.Serializable;

public class UserStoryMember implements Member, Serializable {

    private Integer id;

    public UserStoryMember(Integer id){
        this.id = id;
    }

    public UserStoryMember(Integer id, String ignoredStr) {
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
