package uebung4;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MemberView {

    public void dump(@NotNull List<Member> liste) {
        System.out.println("Ausgabe aller Member-Objekte: ");
        for (Member p : liste) {
            System.out.println("ID: " + p.toString());
        }
    }
}
