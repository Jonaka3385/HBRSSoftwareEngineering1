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
        String tmp;
        boolean incorrect = true;

        int id = 0;
        while (incorrect) {
            System.out.print("id: ");
            tmp = scanner.next();
            id = checkPositiveInt(tmp);
            id = contained(id);
            if (id != -1) incorrect = false;
        }
        incorrect = true;

        System.out.print("beschreibung: ");
        String beschreibung = scanner.next();

        System.out.print("kriterium: ");
        String kriterium = scanner.next();

        int aufwand = 0;
        while (incorrect) {
            System.out.print("aufwand: ");
            tmp = scanner.next();
            aufwand = checkPositiveInt(tmp);
            if (aufwand != -1) incorrect = false;
        }
        incorrect = true;

        int mehrwert = 0;
        while (incorrect) {
            System.out.print("mehrwert: ");
            tmp = scanner.next();
            mehrwert = checkIntBetween1to5(tmp);
            if (mehrwert != -1) incorrect = false;
        }
        incorrect = true;

        int strafe = 0;
        while (incorrect) {
            System.out.print("strafe: ");
            tmp = scanner.next();
            strafe = checkIntBetween1to5(tmp);
            if (strafe != -1) incorrect = false;
        }
        incorrect = true;

        int risiko = 0;
        while (incorrect) {
            System.out.print("risiko: ");
            tmp = scanner.next();
            risiko = checkIntBetween1to5(tmp);
            if (risiko != -1) incorrect = false;
        }

        try {
            container.addMember(new UserStoryMember(id, beschreibung, kriterium, aufwand, mehrwert, strafe, risiko));
        }
        catch (Exception e) {
            System.out.println("add failed:" + e);
        }
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

    private static void dump() {                                    // Noch zu Filter-Map-Reduce umschreiben!!!!!!!!!!!
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
        System.out.println("Not yet implemented.");                 // Wichtig!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

    private static int checkPositiveInt(String str) {
        int i;
        try {
            i = Integer.parseInt(str);
        }
        catch(Exception e) {
            System.out.println("Only Integer: " + e);
            return -1;
        }
        if (i < 0) {
            System.out.println("No negative");
            return -1;
        }
        return i;
    }

    private static int checkIntBetween1to5(String str) {
        int tmp = checkPositiveInt(str);
        if (tmp == -1) return -1;
        if (tmp < 1 || tmp > 5) {
            System.out.println("Only between 1 and 5");
            return -1;
        }
        return tmp;
    }

    private static int contained(int id) {
        List<Member> list = container.getCurrentList();
        List<UserStoryMember> userStoryMemberList = new ArrayList<>();
        for (Member m : list) {
            if (m instanceof UserStoryMember) userStoryMemberList.add((UserStoryMember) m);
        }
        for (UserStoryMember m : userStoryMemberList) {
            if (m.getID() == id) {
                System.out.println("ID: [" + id + "] already taken.");
                return -1;
            }
        }
        return id;
    }
}
