package uebung3;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MemberView {
    public void dump(@NotNull List<Member> liste){
        System.out.println("Ausgabe aller Member-Objekte: ");
        liste.parallelStream().forEach(System.out::println);
    }
}
