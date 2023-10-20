package uebung2;

public class DoubleMember implements Member {
    Integer id;
    double data;

    public DoubleMember(Integer id, double data) {
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
