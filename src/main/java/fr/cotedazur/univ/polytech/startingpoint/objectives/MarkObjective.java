package fr.cotedazur.univ.polytech.startingpoint.objectives;

import java.util.Objects;

/*
* cette classe permet d'evaluer les objectifs
* pour savoir lequel utiliser pour remplir un certain objectif
* */
public class MarkObjective {
    private Objective objective;

    private int mark;

    public MarkObjective(Objective objective) {
        this.objective = objective;
        mark=1;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarkObjective that)) return false;
        return getObjective().equals(that.getObjective());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObjective());
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "MarkObjective{" +
                "objective=" + objective +
                ", mark=" + mark +
                '}';
    }
}
