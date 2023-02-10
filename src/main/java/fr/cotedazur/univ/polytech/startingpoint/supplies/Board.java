package fr.cotedazur.univ.polytech.startingpoint.supplies;


import fr.cotedazur.univ.polytech.startingpoint.display.Display;

import java.security.SecureRandom;
import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.gameplay.Game.getPanda;

/**
 * @clas Board
 * @extend ArrayList
 * a list of placed HexPlots
 */
public class Board extends ArrayList<HexPlot> {

    /**
     * @constructor
     * Created a board with a single HexPlot, the pond
     */
    private SecureRandom rand;
    private byte[] bytes;
    public Board(){
        this.add(new HexPlot());
        rand = new SecureRandom();
        bytes = new byte[20];
        rand.nextBytes(bytes);
    }

    public HexPlot getPond(){
        return this.get(0);
    }

    public HexPlot getLastHexPlot(){
        return this.get(this.size()-1);
    }

    /**
     * Vérifier la contrainte :
     * " la parcelle est adjacente à la parcelle Spéciale (étang)  "
     *
     * @param hex {HexPlot}
     * @return {boolean}
     */
    public boolean checkPondNeighbor(HexPlot hex) {
        Set<HexPlot> neighborSet = hex.plotNeighbor();
        if (neighborSet.isEmpty()) return false;
        for (HexPlot hexPlot : neighborSet) {
            if (hexPlot.isPond()) return true;
        }
        return false;
    }

    /**
     * Vérifier la contrainte :
     * " la parcelle est adjacente à au moins deux parcelles déjà en jeu "
     *
     * @param hex {HexPlot}
     * @return {boolean}
     */
    public boolean checkTwoPlotNeighbors(HexPlot hex) {
        Set<HexPlot> intersection = hex.plotNeighbor();
        Set<HexPlot> listOfPlotscopy = new HashSet<>();
        this.forEach(hexPlot ->
            listOfPlotscopy.add(new HexPlot(hexPlot.getQ(), hexPlot.getS(), hexPlot.getR())));
        intersection.retainAll(listOfPlotscopy);
        return intersection.size() >= 2;
    }

    /**
     * Trouver les emplacements disponibles pour une parcelle, ie
     * - les voisins de hex qui ne sont pas dans les parcelles déjà posées
     * - et qui respectent les contraintes
     * @param hex {HexPlot}
     * @return neighborSet {Set<HexPlot>}
     */
    public Set<HexPlot> findAvailableNeighbors(HexPlot hex){
        Set<HexPlot> neighborSet = hex.plotNeighbor();
        /** Retirer les emplacements indisponibles
         *parcelles déjà posées**/
        this.forEach(x -> {
            HexPlot equivalentHex=new HexPlot(x.getQ(),x.getS(),x.getR());
            if(neighborSet.contains(equivalentHex)){
                neighborSet.remove(equivalentHex);
            }
        });
        neighborSet.removeAll(this);
        /** parcelles non adjacentes à l'étang ou non adjacentes à 2 parcelles**/
        Set<HexPlot> invalidNeighbors = new HashSet<>();
        neighborSet.forEach( hexPlot -> {
            if (! (checkPondNeighbor(hexPlot) || checkTwoPlotNeighbors(hexPlot)) )
                invalidNeighbors.add(hexPlot);
        });
        neighborSet.removeAll(invalidNeighbors);
        return neighborSet;
    }

    /****
     * ChoicePlot permet au joueur
     * de choisir une parcelle inexistante
     * dans le jeu pour le moment
     * et agencent un une autre deja existante
     */

    public void choicePlot(HexPlot hex){
        Set<HexPlot> validPlotsSet = new HashSet<>();
        this.forEach(hexPlot ->
            validPlotsSet.addAll(findAvailableNeighbors(hexPlot)));
        HexPlot[] arrayPlots = validPlotsSet.toArray(new HexPlot[validPlotsSet.size()]);
        int randNumber = rand.nextInt(validPlotsSet.size());
        hex.setQ(arrayPlots[randNumber].getQ());
        hex.setR(arrayPlots[randNumber].getR());
        hex.setS(arrayPlots[randNumber].getS());
        Display.printMessage("nombre de place valide est :"+validPlotsSet.size());
        Display.printMessage("les places valides sont :"+validPlotsSet);
        Display.printMessage("la liste des parcelles dans le jeu avant le choix:"+this);
        this.add(hex);
    }

    public List<HexPlot> getNewPositionPossibilities(){
        List<HexPlot> linearHex = new ArrayList<>();
        HexPlot currentPosition= getPanda().getPosition();
        this.forEach(hexPlot -> {
            if((hexPlot.getQ()==currentPosition.getQ()||hexPlot.getR()== currentPosition.getR()
                    ||hexPlot.getS()==currentPosition.getS())&&
                    !hexPlot.isPond()&&
                    !currentPosition.equals(hexPlot)){
                linearHex.add(hexPlot) ;
            }
        });
        return linearHex;
    }

    /**
     * Ajout d'une parcelle sur le plateau
     * Si elle est déjà irriguée une section de bambou à sa couleur lui est ajoutée
     * @param hexPlot {HexPlot} à ajouter
     * @return {boolean} s'il a bien été ajouté
     */
    @Override
    public boolean add(HexPlot hexPlot){
        hexPlot.irrigate();
        hexPlot.sprout();
        return super.add(hexPlot);
    }

    /**
     * Pick a random plot from the board
     * @return {Hexplot} the random HexPlot
     */
    public HexPlot choosePlotForImprovement(){

        ArrayList<HexPlot> forImprovement = (ArrayList<HexPlot>) this.clone();
        forImprovement.removeIf( hexPlot ->Objects.isNull(hexPlot.getBamboos())||
                (hexPlot.isPond() || hexPlot.getImprovement()!=null || !hexPlot.getBamboos().isEmpty())
        );
        if(forImprovement.isEmpty()){
            Display.printMessage("Aucune parcelle aménageable");
            return null;
        }

        int rnd = rand.nextInt(forImprovement.size());
        return forImprovement.get(rnd);

    }

    /**
     * Pick a random plot from the board, where there are less than 4 bamboos
     * @return {Hexplot} the random HexPlot
     */
    public HexPlot choosePlotForBamboo(){

        ArrayList<HexPlot> forBamboo = (ArrayList<HexPlot>) this.clone();
        forBamboo.removeIf( hexPlot ->Objects.isNull(hexPlot.getBamboos())||
                        !hexPlot.isIrrigated() || (hexPlot.getBamboos().size()==4)
        );
        if(forBamboo.isEmpty()){
            Display.printMessage("Aucune parcelle sur laquelle placer un bambou");
            return null;
        }

        int rnd = rand.nextInt(forBamboo.size());
        return forBamboo.get(rnd);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board hexPlots)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(rand, hexPlots.rand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rand);
    }
}
