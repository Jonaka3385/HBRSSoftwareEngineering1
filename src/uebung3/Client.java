package uebung3;

import uebung3.persistence.PersistenceException;
import uebung3.persistence.PersistenceStrategy;
import uebung3.persistence.PersistenceStrategyMongoDB;
import uebung3.persistence.PersistenceStrategyStream;

public class Client {

    public static void main(String[] args) {
        Container container = Container.getInstance();
        Member m1 = new MemberKonkret(1);
        MemberView view = new MemberView();
        PersistenceStrategy<Member> strategy = new PersistenceStrategyStream<>();
        container.setStrategy(strategy);
        try {
            strategy.openConnection();
        }
        catch (PersistenceException e) {
            throw new RuntimeException("openConnection failed: " + e);
        }
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
        try {
            strategy.closeConnection();
        }
        catch (PersistenceException e) {
            throw new RuntimeException("closeConnection failed: " + e);
        }

        strategy = new PersistenceStrategyMongoDB<>();
        container.setStrategy(strategy);
        try {
            strategy.openConnection();
        }
        catch (Exception e) {
            System.out.println("Trying open MongoDB Connection: " + e);
        }
    }
}
