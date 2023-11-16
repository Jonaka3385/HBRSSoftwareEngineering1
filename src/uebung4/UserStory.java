package uebung4;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class UserStory implements Serializable, Comparable<UserStory> {

    private final Integer id;
    private final String beschreibung;
    private final String kriterium;
    private final int aufwand;
    private final int mehrwert;
    private final int strafe;
    private final int risiko;
    private final double prio;
    private final String projekt;

    public UserStory(Integer id, String beschreibung, String kriterium, int aufwand, int mehrwert, int strafe, int risiko, String projekt) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.kriterium = kriterium;
        this.aufwand = aufwand;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.risiko = risiko;
        prio = ((double) (this.mehrwert + this.strafe) / (this.aufwand + this.risiko));
        this.projekt = projekt;
    }

    public UserStory(Integer id) {
        this.id = id;
        this.beschreibung = "Beschreibung";
        this.kriterium = "Kriterium";
        this.aufwand = 1;
        this.mehrwert = 1;
        this.strafe = 1;
        this.risiko = 1;
        prio = ((double) (this.mehrwert + this.strafe) / (this.aufwand + this.risiko));
        this.projekt = "Projekt";
    }

    public Integer getID() {
        return id;
    }

    @Override
    public String toString() {
        return id + "; " + beschreibung + "; " + kriterium + "; " + aufwand + "; " + mehrwert + "; " + strafe + "; " + risiko + "; " + prio + "; " + projekt;
    }

    public boolean sameDescription(@NotNull String description) {
        return description.equals(beschreibung);
    }

    public String getProjekt() {
        return projekt;
    }

    @Override
    public int compareTo(@NotNull UserStory o) {
        return (int) ((this.prio*100) - (o.prio*100));
    }
}
