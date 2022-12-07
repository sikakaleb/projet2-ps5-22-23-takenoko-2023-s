package fr.cotedazur.univ.polytech.startingpoint;

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

    /**Constructeur de la classe**/
    public PlotObjective(int numberOfPoints,PlotObjectiveConfiguration configuration) {
        super(numberOfPoints);
        this.configuration=configuration;
        plotObjectiveId=++NumberOfPlotObjective;
    }

    /**Acesseur de la classe**/

    public int getPlotObjectiveId() {
        return plotObjectiveId;
    }

    public PlotObjectiveConfiguration getConfiguration() {
        return configuration;
    }
}
