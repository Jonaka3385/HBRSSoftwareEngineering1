package uebung4;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ContainerView {
    public void dump(@NotNull List<?> liste) {
        System.out.println("Ausgabe aller Member-Objekte: ");
        for (Object t : liste) {
            System.out.println("ID: " + t);
        }
    }
}
