package fr.cotedazur.univ.polytech.startingpoint.objectives;

import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import fr.cotedazur.univ.polytech.startingpoint.tools.GardenerObjectiveConfiguration;

/**
 * @class GardenerObjective
 * @extends Objective
 * Traite les Objectifs de Jardinier
 * */
public class GardenerObjective extends Objective{
    private static int numberOfGardenerObjective = 0;
    private int gardenerObjectiveId;
    private GardenerObjectiveConfiguration configuration;
    private Color[] colors;

    /**
     * @constructor
     * @param numberOfPoints {int}
     * @param configuration {GardenerObjectiveConfiguration}
     * @param colors
     */
    public GardenerObjective(int numberOfPoints, GardenerObjectiveConfiguration configuration, Color[] colors) {
        super(numberOfPoints);
        this.configuration=configuration;
        gardenerObjectiveId=++numberOfGardenerObjective;
        this.colors=colors;
    }

    /**
     * Getters
     */
    public Color[] getColor() {
        return colors;
    }

    public GardenerObjectiveConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public String toString() {
        return "{ Objectif jardinier: "+ configuration +'}';

    }
}
