package fr.cotedazur.univ.polytech.startingpoint.objectives;

import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import fr.cotedazur.univ.polytech.startingpoint.tools.PandaObjectiveConfiguration;

/**
 * @class PandaObjective
 * @extends Objective
 * Traite les Objectifs de Panda
 * */
public class PandaObjective extends Objective{
    private static int numberOfPandaObjective = 0;
    private int pandaObjectiveId;
    private PandaObjectiveConfiguration configuration;
    private Color[] colors;

    /**
     * @constructor
     * @param numberOfPoints {int}
     * @param configuration {PandaObjectiveConfiguration}
     * @param colors
     */
    public PandaObjective(int numberOfPoints, PandaObjectiveConfiguration configuration, Color[] colors) {
        super(numberOfPoints);
        this.configuration=configuration;
        pandaObjectiveId=++numberOfPandaObjective;
        this.colors=colors;
    }

    /**
     * Getters
     */
    public int getpandaObjectiveId() {
        return pandaObjectiveId;
    }

    public Color[] getColor() {
        return colors;
    }

    public PandaObjectiveConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public String toString() {
        return "{ Objectif panda: "+ configuration +'}';

    }
}
