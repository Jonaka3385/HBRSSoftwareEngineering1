package uebung3;

public class Client {

    public static void main(String[] args) {
        Container container = Container.getInstance();
        Member m1 = new MemberKonkret(1);
        MemberView view = new MemberView();
        container.setStrategy("Stream");
        try {
            container.addMember(m1);
            view.dump(container.getCurrentList());
        }
        catch (Exception e) {
            throw new RuntimeException("add failed");
        }
        container = Container.getInstance();
        try {
            view.dump(container.getCurrentList());
        }
        catch (Exception e) {
            throw new RuntimeException("add failed");
        }
        try {
            container.store();
            view.dump(container.getCurrentList());
        }
        catch (Exception e) {
            throw new RuntimeException("store failed");
        }
        try {
            container.deleteMember(1);
            view.dump(container.getCurrentList());
        }
        catch (Exception e) {
            throw new RuntimeException("delete failed");
        }
        try {
            container.load();
            view.dump(container.getCurrentList());
        }
        catch (Exception e) {
            throw new RuntimeException("load failed");
        }

        container.setStrategy("MongoDB");
        try {
            container.store();
        }
        catch (PersistenceException e) {
            System.out.println(e.getExceptionTypeType());
        }
        catch (UnsupportedOperationException e) {
            System.out.println(e);
        }
    }
}
