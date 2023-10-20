package uebung2;

public class StringMember implements Member {
    Integer id;
    String data;

    public StringMember(Integer id, String data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public String toString() {
        return "Member (ID = " + id + ")";
    }
}
