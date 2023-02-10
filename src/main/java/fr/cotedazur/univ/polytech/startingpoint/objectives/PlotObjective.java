package fr.cotedazur.univ.polytech.startingpoint.objectives;

import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.BLANK;

/**
 * Cette classe est une specification
 * de la classe Objective
 * et Traite les Objectifs Parcelles
 * */
public class PlotObjective extends Objective{
    /**Attribut de la classe**/
    private PlotObjectiveConfiguration configuration;

    private Color color;

    /**Constructeur de la classe**/
    public PlotObjective(int numberOfPoints,PlotObjectiveConfiguration configuration) {
        super(numberOfPoints);
        this.configuration=configuration;
        this.color= BLANK;
    }

    public PlotObjective(int numberOfPoints, PlotObjectiveConfiguration configuration, Color color) {
        super(numberOfPoints);
        this.configuration=configuration;
        this.color=color;
    }

    /**Acesseur de la classe**/

    public Color getColor() {
        return color;
    }

    public PlotObjectiveConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public String toString() {
        return "{ Objectif parcelle: "+ configuration +'}';
    }
}
