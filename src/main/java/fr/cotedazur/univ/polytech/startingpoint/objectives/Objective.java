package fr.cotedazur.univ.polytech.startingpoint.objectives;

public abstract class Objective {
    /**Attributs de la classe**/
    private  int numberOfPoints;

    /**COntructeur de la classe**/
    protected Objective(int nmberOfPoints) {
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
