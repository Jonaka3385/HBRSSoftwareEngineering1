package uebung2;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.LinkedList;

public class Container {
    List<Member> list;

    public Container() {
        list = new LinkedList<>();
    }

    public void addMember(@NotNull Member member) throws ContainerException {
        if (containsAt(member.getID()) != -1) throw new ContainerException(member.getID().toString());
        else list.add(member);
    }

    public int deleteMember(Integer id) {
        int i = containsAt(id);
        if (i != -1) {
            list.remove(i);
            return 0;
        }
        else return 1;
    }   //Fehlerhandling 0 = in Ordnung; 1 = Fehler
        //Problem: Fehler kann ignoriert werden. Exceptions meiner Meinung immer besser.

    private int containsAt(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID() == id) return i;
        }
        return -1;
    }

    public void dump() {
        Member current;
        for (Member member : list) {
            System.out.println(member.toString());
        }
    }

    public int size() {
        return list.size();
    }
}
