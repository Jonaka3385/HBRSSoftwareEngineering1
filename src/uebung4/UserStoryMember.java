package uebung4;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class UserStoryMember implements Member, Serializable, Comparable<UserStoryMember> {

    private final Integer id;
    private final String beschreibung;
    private final String kriterium;
    private final int aufwand;
    private final int mehrwert;
    private final int strafe;
    private final int risiko;
    private final int prio;

    public UserStoryMember(Integer id, String beschreibung, String kriterium, int aufwand, int mehrwert, int strafe, int risiko) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.kriterium = kriterium;
        this.aufwand = aufwand;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.risiko = risiko;
        prio = ((this.mehrwert + this.strafe) / (this.aufwand + this.risiko));
    }

    public Integer getID() {
        return id;
    }

    @Override
    public String toString() {
        return id + "; " + beschreibung + "; " + kriterium + "; " + aufwand + "; " + mehrwert + "; " + strafe + "; " + risiko + "; " + prio;
    }

    @Override
    public int compareTo(@NotNull UserStoryMember o) {
        return (this.prio*100) - (o.prio*100);
    }
}
