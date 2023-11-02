package uebung3;

public class Client {

    public static void main(String[] args) {
        Container container = Container.getInstance();
        Member m1 = new MemberKonkret(1);
        try {
            container.addMember(m1);
            MemberView view = new MemberView();
            view.dump(container.getCurrentList());
        }
        catch (Exception e) {
            throw new RuntimeException("add failed");
        }
        try {
            container.store();
            MemberView view = new MemberView();
            view.dump(container.getCurrentList());
        }
        catch (Exception e) {
            throw new RuntimeException("store failed");
        }
        try {
            container.deleteMember(1);
            MemberView view = new MemberView();
            view.dump(container.getCurrentList());
        }
        catch (Exception e) {
            throw new RuntimeException("delete failed");
        }
        try {
            container.load();
            MemberView view = new MemberView();
            view.dump(container.getCurrentList());
        }
        catch (Exception e) {
            throw new RuntimeException("load failed");
        }
    }
}
