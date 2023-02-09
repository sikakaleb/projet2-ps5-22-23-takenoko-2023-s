package fr.cotedazur.univ.polytech.startingpoint.tools;

import fr.cotedazur.univ.polytech.startingpoint.objectives.MarkObjective;

import java.util.Comparator;

public class MarkObjectiveComparator implements Comparator<MarkObjective> {
    @Override
    public int compare(MarkObjective o1, MarkObjective o2) {
        return Integer.compare(o1.getMark(), o2.getMark());
    }
}

