package uebung4;

import uebung4.persistence.PersistenceStrategy;
import uebung4.persistence.PersistenceStrategyStream;

import java.util.Scanner;

public class UserStoriesManager {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Container container = Container.getInstance();
        PersistenceStrategy<Member> strategy = new PersistenceStrategyStream<>();
        container.setPersistenceStrategie(strategy);
    }
}
