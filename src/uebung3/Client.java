package uebung3;

public class Client {

    public static void main(String[] args) {
        Container container = Container.getInstance();
        Member m1 = new MemberKonkret(1);
        MemberView view = new MemberView();
        container.setStrategy(new PersistenceStrategyStream<>());
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
            throw new RuntimeException("store failed: " + e);
        }
        try {
            container.deleteMember(1);
            view.dump(container.getCurrentList());
        }
        catch (Exception e) {
            throw new RuntimeException("delete failed: " + e);
        }
        try {
            container.load();
            view.dump(container.getCurrentList());
        }
        catch (Exception e) {
            throw new RuntimeException("load failed: " + e);
        }

        container.setStrategy(new PersistenceStrategyMongoDB<>());
        try {
            container.store();
        }
        catch (PersistenceException e) {
            System.out.println("PersistenceException: " + e.getExceptionTypeType());
        }
        catch (UnsupportedOperationException e) {
            System.out.println("Mongo not implemented: " + e);
        }
    }
}
