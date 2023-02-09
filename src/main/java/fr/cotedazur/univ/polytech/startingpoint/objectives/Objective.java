package fr.cotedazur.univ.polytech.startingpoint.objectives;

public abstract class Objective {
    /**Attributs de la classe**/
    private static  int numberOfObjective =0;
    private int objectId;
    private  int NumberOfPoints;

    /**COntructeur de la classe**/
    protected Objective(int numberOfPoints) {
        objectId=++numberOfObjective;
        NumberOfPoints = numberOfPoints;

    }

    /**Acesseur de la classe*/

    public int getObjectId() {
        return objectId;
    }

    public int getNumberOfPoints() {
        return NumberOfPoints;
    }

    @Override
    public String toString() {
        return "NumberOfPoints=" + NumberOfPoints +
                '}';
    }
}
