package fr.cotedazur.univ.polytech.startingpoint.objectives;

import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import fr.cotedazur.univ.polytech.startingpoint.tools.PandaObjectiveConfiguration;

import java.util.Arrays;

/**
 * @class PandaObjective
 * @extends Objective
 * Traite les Objectifs de Panda
 * */
public class PandaObjective extends Objective{
    public static int  NumberOfPandaObjective = 0;
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
        pandaObjectiveId=++NumberOfPandaObjective;
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
        return "PandaObjective{" +
                "pandaObjectiveId=" + pandaObjectiveId +
                ", configuration=" + configuration +
                ", colors=" + Arrays.toString(colors) +
                '}';
    }
}
