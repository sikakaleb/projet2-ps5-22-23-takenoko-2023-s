package fr.cotedazur.univ.polytech.startingpoint.objectives;

public abstract class Objective {
    /**Attributs de la classe**/
    private static  int numberOfObjective =0;
    private int objectId;
    private  int numberOfPoints;

    /**COntructeur de la classe**/
    protected Objective(int nmberOfPoints) {
        objectId=++numberOfObjective;
        numberOfPoints = nmberOfPoints;

    }

    /**Acesseur de la classe*/
    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    @Override
    public String toString() {
        return "NumberOfPoints=" + numberOfPoints +
                '}';
    }
}
