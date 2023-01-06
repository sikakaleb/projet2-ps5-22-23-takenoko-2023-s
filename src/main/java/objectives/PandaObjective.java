package objectives;

import tools.Color;
import tools.PandaObjectiveConfiguration;

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
}