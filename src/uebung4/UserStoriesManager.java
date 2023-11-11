package uebung4;

import uebung4.persistence.PersistenceException;
import uebung4.persistence.PersistenceStrategy;
import uebung4.persistence.PersistenceStrategyStream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserStoriesManager {
    static Scanner scanner;
    static Container container;
    static boolean running;

    public static void start() {
        scanner = new Scanner(System.in);
        container = Container.getInstance();
        PersistenceStrategy<Member> strategy = new PersistenceStrategyStream<>();
        container.setPersistenceStrategie(strategy);
        running = true;

        while (running) {
            listen();
        }
    }

    private static void listen() {
        System.out.print("> ");
        String line = scanner.nextLine();
        switch(line) {
            case "enter":
                enter();
                break;
            case "store":
                store();
                break;
            case "load":
                load();
                break;
            case "dump":
                dump();
                break;
            case "search":
                search();
                break;
            case "exit":
                exit();
                break;
            case "help":
                help();
                break;
            default: break;
        }
    }

    private static void enter() {
        System.out.println("Not yet implemented.");
    }

    private static void store() {
        try {
            container.store();
        }
        catch (PersistenceException e) {
            System.out.println("store failed: " + e);
        }
    }

    private static void load() {
        try {
            container.load();
        }
        catch (PersistenceException e) {
            System.out.println("load failed: " + e);
        }
    }

    private static void dump() {
        List<Member> list = container.getCurrentList();
        List<UserStoryMember> userStoryMemberList = new ArrayList<>();
        for (Member m : list) {
            if (m instanceof UserStoryMember) userStoryMemberList.add((UserStoryMember) m);
        }
        userStoryMemberList.sort(Comparator.naturalOrder());
        System.out.println("ID  Description Kriterium   Aufwand Mehrwert    Strafe  Risiko  Prio");
        for (UserStoryMember usm : userStoryMemberList) {
            System.out.println(usm);
        }
    }

    private static void search() {
        System.out.println("Not yet implemented.");
    }

    private static void exit() {
        running = false;
        scanner = null;
        container = null;
    }

    private static void help() {
        String[] befehle = {"enter (Eingabe einer User Story)",
                            "store (Persistentes Abspeichern von User Stories auf Datenträger)",
                            "load (Laden von User Stories von Datenträger)",
                            "dump (eine nach den berechneten Priorisierungen sortierte Ausgabe der User Stories inklusive aller eingegeben Angaben)",
                            "search (Suche nach User Stories nach Projekten, Suchwort = Bezeichnung des Projektes)",
                            "exit (Verlassen der Anwendung)",
                            "help (Anzeige aller möglichen Befehle)"};
        for (String b : befehle) {
            System.out.println(b);
        }
    }
}
