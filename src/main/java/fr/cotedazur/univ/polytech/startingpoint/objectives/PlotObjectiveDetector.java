package fr.cotedazur.univ.polytech.startingpoint.objectives;

import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import org.paukov.combinatorics3.Generator;
import fr.cotedazur.univ.polytech.startingpoint.supplies.Board;
import fr.cotedazur.univ.polytech.startingpoint.supplies.HexPlot;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;

/**
 * @class PlotObjectiveDetector
 * Responsible for finding plot objective configurations on the board
 */
public class PlotObjectiveDetector {

    private Board board;

    /**
     * @cosntructor
     * @param board {Board}
     */
    public PlotObjectiveDetector(Board board){
        this.board = board;
    }

    /****
     *Retourne les combinaisons de liste(n)
     * de parcelle dans le jeu privé du parcelle etang(0,0,0)
     */

    public  List<List<HexPlot>> listOfCombinations(int n){
        Set<HexPlot> tempList = new HashSet<>();
        this.board.forEach(hexPlot -> {
            if(!hexPlot.equals(new HexPlot())){
                tempList.add(hexPlot);
            }
        });
        return Generator.combination(tempList)
                .simple(n)
                .stream()
                .toList();
    }
    /****
     *Extrait Toutes les coordonnées de parcelles
     * dans une liste
     * et les ordonnes
     */
    public Map<String, Map<Integer,Integer>> extractPlotsData(List<HexPlot> listPlots) {
        Map<String,Map<Integer,Integer>> result = new HashMap<>();
        result.put("Q",countQ(listPlots));
        result.put("S",countS(listPlots));
        result.put("R",countR(listPlots));
        return result;

    }

    /****
     *Extrait les coordonnées R de parcelles
     * dans une liste
     * et les ordonnes
     */
    public Map<Integer, Integer> countR(List<HexPlot> listPlots) {
        HashMap<Integer, Integer> counter = new HashMap<>();

        for (HexPlot hex  : listPlots) {
            if (counter.containsKey(hex.getR())) {
                counter.put(hex.getR(), counter.get(hex.getR()) + 1);
            } else {
                counter.put(hex.getR(), 1);
            }
        }

        return counter;
    }

    /****
     *Extrait les coordonnées S de parcelles
     * dans une liste
     * et les ordonnes
     */
    public Map<Integer, Integer> countS(List<HexPlot> listPlots) {
        HashMap<Integer, Integer> counter = new HashMap<>();

        for (HexPlot hex  : listPlots) {
            if (counter.containsKey(hex.getS())) {
                counter.put(hex.getS(), counter.get(hex.getS()) + 1);
            } else {
                counter.put(hex.getS(), 1);
            }
        }

        return counter;
    }

    /****
     *Extrait les coordonnées Q de parcelles
     * dans une liste
     * et les ordonnes
     */
    public Map<Integer, Integer> countQ(List<HexPlot> listPlots) {
        HashMap<Integer, Integer> counter = new HashMap<>();

        for (HexPlot hex  : listPlots) {
            if (counter.containsKey(hex.getQ())) {
                counter.put(hex.getQ(), counter.get(hex.getQ()) + 1);
            } else {
                counter.put(hex.getQ(), 1);
            }
        }

        return counter;
    }

    /**verifier si les coordonnées des Hexplot de la liste sont une suite**/
    public Boolean sSuite(Set<Integer> setOfInteger,int size){
        List<Integer> listInteger = new ArrayList<>(setOfInteger);
        ArrayList<Integer> listeOrdonne = new ArrayList<>();
        for(int i = 0; i < listInteger.size(); i++){
            listeOrdonne.add(listInteger.get(i));
        }
        Collections.sort(listeOrdonne);
        for(int j = 1; j < listInteger.size(); j++){
            if(listeOrdonne.get(j) != listeOrdonne.get(j-1) + 1){
                return false;
            }
        }
        return listInteger.size()==size;
    }

    /** Verifier si une liste de 3 hexplot a la configuration DIRECTSAMEPLOTS  **/
    public Boolean isDirectSamePlots(List<HexPlot> listPlots){
        Map<String ,Map<Integer,Integer>> listPlotsData = extractPlotsData(listPlots);
        Set<Integer> answerset=new HashSet<>();
        answerset.add(1);
        answerset.add(3);
        return answerset.equals(checkSetSuitConf(listPlotsData))
                && listPlots.size()==3
                && allColorInHexPlotList(listPlots).size()==1;
    }
    /**
     * Verifie si les plots de la liste
     * sont adjacents à un autre
     * ayant les mm couleur qu'eux.
     * Elle est utile pour les trois
     * cas critique de QUADRILATERALSAMEPLOTS
     * **/
    public Boolean checkPairAdjacentColor(List<HexPlot> plotList){
        Boolean result = true;
        for (HexPlot hex: plotList) {
            result&=hex.isAdjacentWithAnotherOnSameColor(plotList);
        }
        return result;
    }
    /**
     * Elle retourne un SET
     * Des couleurs dans une liste
     * Uilisé dans la dectection
     * des Objectifs Plots
     * **/
    public Set<Color> allColorInHexPlotList(List<HexPlot> plotList){
        Set<Color> colorSet= new HashSet<>();
        plotList.forEach( hexPlot ->
            colorSet.add(hexPlot.getColor())
        );
        return colorSet;
    }
    /***
     * verifie si chaque clé de String du map
     * possede une valeur qui est un SET
     * et qui presente une suite
     * */
    public Set<Integer> checkSetSuitConf(Map<String ,Map<Integer,Integer>> listPlotsData ){
        Set<Integer> answerset=new HashSet<>();
        for (String stringKey: listPlotsData.keySet()) {
            if(Boolean.TRUE.equals(sSuite(listPlotsData.get(stringKey).keySet(),1)
                    ||sSuite(listPlotsData.get(stringKey).keySet(),2)
                    ||sSuite(listPlotsData.get(stringKey).keySet(),3))){
                answerset.add(listPlotsData.get(stringKey).size());
            }else
                answerset.add(-1);
        }
        return answerset;
    }

    public Map<Integer,Integer> checkMapSuitConf(List<HexPlot> listPlots ){
        Map<Integer,Integer> counter=new HashMap<>();
        List<HexPlot> listPlotsCopy=new ArrayList<>();
        listPlots.forEach(x ->
            listPlotsCopy.add(new HexPlot(x.getQ(),x.getS(),x.getR()))
        );
        for (HexPlot hex  : listPlotsCopy) {
            Set<HexPlot> process=hex.plotNeighbor();
            process.retainAll(listPlotsCopy);
            if (counter.containsKey(process.size())) {
                counter.put(process.size(), counter.get(process.size()) + 1);
            } else {
                counter.put(process.size(), 1);
            }
        }

        return counter;
    }

    /** Verifier si une liste de 3 hexplot a la configuration INDIRECTSAMEPLOTS  **/
    public Boolean isIndirectSamePlots(List<HexPlot> listPlots){
        Map<String ,Map<Integer,Integer>> listPlotsData = extractPlotsData(listPlots);
        Set<Integer> answerset=new HashSet<>();
        answerset.add(2);
        answerset.add(3);
        return answerset.equals(checkSetSuitConf(listPlotsData))
                && listPlots.size()==3
                && allColorInHexPlotList(listPlots).size()==1;
    }

    /** Verifier si une liste de 4 hexplot a la configuration QUADRILATERALPLOTS  **/
    public Boolean isQuadrilateralPlots(List<HexPlot> listPlots){
        Map<Integer,Integer> answerset=new HashMap<>();
        answerset.put(2,2);
        answerset.put(3,2);
        return answerset.equals(checkMapSuitConf(listPlots)) && listPlots.size()==4 ;

    }

    /** Verifier si une liste de 4 hexplot a la configuration QUADRILATERALPLOTS  **/
    public Boolean isQuadrilateralSamePlots(List<HexPlot> listPlots){
        Map<Integer,Integer> answerset=new HashMap<>();
        answerset.put(2,2);
        answerset.put(3,2);
        return isQuadrilateralPlots(listPlots)
                &&allColorInHexPlotList(listPlots).size()==1 ;

    }

    /** Detection des variantes de QUADRILATERALSAMESPLOTS AVEC AJOUTS DES COULEURS**/
    /**
     * QUADRILATERAL AVEC DEUX PARCELLES PINK ADJACENT
     * ET DEUX PARCELLES YELLOW ADJACENT
     * **/
    public Boolean isQuadrilateralPlotsPY(List<HexPlot> listPlots){
        Set<Color> colorSet= new HashSet<>();
        colorSet.add(PINK);
        colorSet.add(YELLOW);
        return isQuadrilateralPlots(listPlots)
                && checkPairAdjacentColor(listPlots)
                && colorSet.equals(allColorInHexPlotList(listPlots));
    }
    /**
     * QUADRILATERAL AVEC DEUX PARCELLES GREEN ADJACENT
     * ET DEUX PARCELLES PINK ADJACENT
     * **/
    public Boolean isQuadrilateralPlotsGP(List<HexPlot> listPlots){
        Set<Color> colorSet= new HashSet<>();
        colorSet.add(PINK);
        colorSet.add(GREEN);
        return isQuadrilateralPlots(listPlots)
                && checkPairAdjacentColor(listPlots)
                && colorSet.equals(allColorInHexPlotList(listPlots));
    }

    /**
     * QUADRILATERAL AVEC DEUX PARCELLES GREEN ADJACENT
     * ET DEUX PARCELLES YELLOW ADJACENT
     * **/
    public Boolean isQuadrilateralPlotsGY(List<HexPlot> listPlots){
        Set<Color> colorSet= new HashSet<>();
        colorSet.add(GREEN);
        colorSet.add(YELLOW);
        return isQuadrilateralPlots(listPlots)
                && checkPairAdjacentColor(listPlots)
                && colorSet.equals(allColorInHexPlotList(listPlots));
    }

    /** Trouver un Objectif DIRECTSAMEPLOTS dans tout le jeu**/
    public Boolean findDirectSamePlots(Color color){
        List<List<HexPlot>> allCombinationOfthreeHexplots = listOfCombinations(3);
        for (List<HexPlot> hexPlotList:allCombinationOfthreeHexplots) {
            if(Boolean.TRUE.equals(isDirectSamePlots(hexPlotList)&& allColorInHexPlotList(hexPlotList).contains(color))) {
                return true;
            }
        }
        return false;
    }

    /** Trouver un Objectif INDIRECTSAMEPLOTS dans tout le jeu**/
    public boolean findInDirectSamePlots(Color color) {
        List<List<HexPlot>> allCombinationOfthreeHexplots = listOfCombinations(3);
        for (List<HexPlot> hexPlotList:allCombinationOfthreeHexplots) {
            if(Boolean.TRUE.equals(isIndirectSamePlots(hexPlotList) && allColorInHexPlotList(hexPlotList).contains(color)) ){
                return true;
            }
        }
        return false;
    }

    /** Trouver un Objectif QUADRILATERALSAMEPLOTS dans tout le jeu**/
    public boolean findQuadrilateralSamePlots(Color color) {
        List<List<HexPlot>> allCombinationOfthreeHexplots = listOfCombinations(4);
        for (List<HexPlot> hexPlotList:allCombinationOfthreeHexplots) {
            if(Boolean.TRUE.equals(isQuadrilateralSamePlots(hexPlotList)&& allColorInHexPlotList(hexPlotList).contains(color)) ){
                return true;
            }
        }
        return false;
    }

    /** Trouver un Objectif QUADRILATERALSAMEPLOTS dans tout le jeu
     * Avec deux parcelles PINK adjacent
     * et deux parcelles YELLOW adjacent**/
    public boolean findQuadrilateralPlotsPY() {
        List<List<HexPlot>> allCombinationOfthreeHexplots = listOfCombinations(4);
        for (List<HexPlot> hexPlotList:allCombinationOfthreeHexplots) {
            if(isQuadrilateralPlotsPY(hexPlotList)) {
                return true;
            }
        }
        return false;
    }

    /** Trouver un Objectif QUADRILATERALSAMEPLOTS dans tout le jeu
     * Avec deux parcelles GREEN adjacent
     * et deux parcelles PINK adjacent**/
    public boolean findQuadrilateralPlotsGP() {
        List<List<HexPlot>> allCombinationOfthreeHexplots = listOfCombinations(4);
        for (List<HexPlot> hexPlotList:allCombinationOfthreeHexplots) {
            if(isQuadrilateralPlotsGP(hexPlotList)) {
                return true;
            }
        }
        return false;
    }

    /** Trouver un Objectif QUADRILATERALSAMEPLOTS dans tout le jeu
     * Avec deux parcelles GREEN adjacent
     * et deux parcelles YELLOW adjacent**/
    public boolean findQuadrilateralPlotsGY() {
        List<List<HexPlot>> allCombinationOfthreeHexplots = listOfCombinations(4);
        for (List<HexPlot> hexPlotList:allCombinationOfthreeHexplots) {
            if(isQuadrilateralPlotsGY(hexPlotList)) {
                return true;
            }
        }
        return false;
    }


}
