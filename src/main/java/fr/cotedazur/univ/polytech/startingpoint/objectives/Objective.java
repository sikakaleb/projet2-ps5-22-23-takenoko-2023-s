package fr.cotedazur.univ.polytech.startingpoint.objectives;

public class Objective {
    /**Attributs de la classe**/
    public static  int NumberOfObjective=0;
    private int objectId;
    private  int NumberOfPoints;

    /**COntructeur de la classe**/
    public Objective(int numberOfPoints) {
        objectId=++NumberOfObjective;
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
