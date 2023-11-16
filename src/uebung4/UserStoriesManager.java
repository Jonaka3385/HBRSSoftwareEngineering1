package uebung4;

import org.jetbrains.annotations.NotNull;
import uebung4.persistence.PersistenceException;
import uebung4.persistence.PersistenceStrategy;
import uebung4.persistence.PersistenceStrategyStream;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserStoriesManager {
    static Scanner scanner;
    static Container<UserStory> container;
    static boolean running;

    public static void start() {
        scanner = new Scanner(System.in);
        Container<?> tmp = Container.getInstance();
        container = (Container<UserStory>) tmp;
        PersistenceStrategy<UserStory> strategy = new PersistenceStrategyStream<>();
        container.setPersistenceStrategie(strategy);
        running = true;
        while (running) {
            listen();
        }
    }

    private static void listen() {
        System.out.print("> ");
        String input = scanner.nextLine();
        String[] strings = input.split(" ");
        switch(strings[0]) {
            case "enter":
                enter(strings);
                break;
            case "store":
                store();
                break;
            case "load":
                load();
                break;
            case "dump":
                dump(strings);
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

    private static void enter(String @NotNull [] followUps) {
        if (followUps.length <= 1) {
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

            System.out.print("Projekt: ");
            String projekt = scanner.next();

            try {
                container.addObject(new UserStory(id, beschreibung, kriterium, aufwand, mehrwert, strafe, risiko, projekt));
            } catch (Exception e) {
                System.out.println("add failed:" + e);
            }
        } else {
            String tmp;
            boolean incorrect = true;

            int id;
            tmp = followUps[1];
            id = checkPositiveInt(tmp);
            if (id == -1) return;
            id = contained(id);
            if (id != -1) incorrect = false;
            if (incorrect) {
                System.out.println("False ID");
                return;
            }
            incorrect = true;

            String beschreibung = followUps[2];

            String kriterium = followUps[3];

            int aufwand;
            tmp = followUps[4];
            aufwand = checkPositiveInt(tmp);
            if (aufwand != -1) incorrect = false;
            if (incorrect) {
                System.out.println("False Aufwand");
                return;
            }
            incorrect = true;

            int mehrwert;
            tmp = followUps[5];
            mehrwert = checkIntBetween1to5(tmp);
            if (mehrwert != -1) incorrect = false;
            if (incorrect) {
                System.out.println("False Mehrwert");
                return;
            }
            incorrect = true;

            int strafe;
            tmp = followUps[6];
            strafe = checkIntBetween1to5(tmp);
            if (strafe != -1) incorrect = false;
            if (incorrect) {
                System.out.println("False Strafe");
                return;
            }
            incorrect = true;

            int risiko;
            tmp = followUps[7];
            risiko = checkIntBetween1to5(tmp);
            if (risiko != -1) incorrect = false;
            if (incorrect) {
                System.out.println("False Risiko");
                return;
            }

            String projekt = followUps[8];

            try {
                container.addObject(new UserStory(id, beschreibung, kriterium, aufwand, mehrwert, strafe, risiko, projekt));
            } catch (Exception e) {
                System.out.println("add failed:" + e);
            }
        }
    }

    private static void store() {
        try {
            container.store();
        } catch (PersistenceException e) {
            System.out.println("store failed: " + e);
        }
    }

    private static void load() {
        try {
            container.load();
        } catch (PersistenceException e) {
            System.out.println("load failed: " + e);
        }
    }

    private static void dump(String @NotNull [] followUps) {
        if (followUps.length <= 1) {
            List<UserStory> list = container.getCurrentList();
            list.sort(Comparator.naturalOrder());
            System.out.println("ID; Description; Kriterium; Aufwand; Mehrwert; Strafe; Risiko; Prio");
            for (UserStory userStory : list) {
                System.out.println(userStory);
            }
        } else if (followUps[1].equalsIgnoreCase("projekt")) {
            List<UserStory> list = container.getCurrentList();
            list.sort(Comparator.naturalOrder());
            System.out.println("ID; Description; Kriterium; Aufwand; Mehrwert; Strafe; Risiko; Prio");
            list.stream().filter(userStory -> userStory.getProjekt().equalsIgnoreCase(followUps[2])).forEach(System.out::println);
        } else {
            System.out.println("Not allowed followUp-Statement");
        }
    }

    private static void search() {
        List<UserStory> list = container.getCurrentList();
        System.out.print("Description: ");
        String description = scanner.next();
        for (UserStory userStory : list) {
            if (userStory.sameDescription(description)) System.out.println(userStory);
            else System.out.println("Not Found");
        }
    }

    private static void exit() {
        running = false;
        scanner = null;
        container = null;
        System.out.println("exited");
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
        } catch(Exception e) {
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
        List<UserStory> list = container.getCurrentList();
        for (UserStory userStory : list) {
            if (userStory.getID() == id) {
                System.out.println("ID: [" + id + "] already taken.");
                return -1;
            }
        }
        return id;
    }
}
