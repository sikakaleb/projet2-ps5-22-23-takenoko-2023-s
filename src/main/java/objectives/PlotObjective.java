package objectives;

import tools.Color;
import tools.PlotObjectiveConfiguration;

import static tools.Color.BLANK;

/**
 * Cette classe est une specification
 * de la classe Objective
 * et Traite les Objectifs Parcelles
 * */
public class PlotObjective extends Objective{
    /**Attribut de la classe**/
    public static int  NumberOfPlotObjective = 0;
    private int plotObjectiveId;
    private PlotObjectiveConfiguration configuration;

    private Color color;

    /**Constructeur de la classe**/
    public PlotObjective(int numberOfPoints,PlotObjectiveConfiguration configuration) {
        super(numberOfPoints);
        this.configuration=configuration;
        plotObjectiveId=++NumberOfPlotObjective;
        this.color= BLANK;
    }

    public PlotObjective(int numberOfPoints, PlotObjectiveConfiguration configuration, Color color) {
        super(numberOfPoints);
        this.configuration=configuration;
        plotObjectiveId=++NumberOfPlotObjective;
        this.color=color;
    }

    /**Acesseur de la classe**/

    public int getPlotObjectiveId() {
        return plotObjectiveId;
    }

    public Color getColor() {
        return color;
    }

    public PlotObjectiveConfiguration getConfiguration() {
        return configuration;
    }
}
