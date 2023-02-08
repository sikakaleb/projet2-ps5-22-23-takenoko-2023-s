package fr.cotedazur.univ.polytech.startingpoint.objectives;
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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
