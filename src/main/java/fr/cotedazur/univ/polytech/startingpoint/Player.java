package fr.cotedazur.univ.polytech.startingpoint;


import org.jetbrains.annotations.NotNull;
import org.paukov.combinatorics3.Generator;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.Game.listOfObjectives;
import static fr.cotedazur.univ.polytech.startingpoint.Game.listOfPlots;
import static fr.cotedazur.univ.polytech.startingpoint.PlotColor.*;
import static fr.cotedazur.univ.polytech.startingpoint.PlotObjectiveConfiguration.*;

public class Player {
    /**Attributs de la classe**/
    private static int numberOfPlayer=0;
    private int playerId;

    /**Begin Attributs CACA  --> **/
    private int age;
    private int height;

    /**End Attributs CACA  --> **/
    private String name;
    private int cumulOfpoint;
    private int maxUnmetObj;
    public List<Objective> objectiveAchieved ;
    public List<Objective> unMetObjectives;

   /**Le ou Les constructeurs de la classe **/
    public Player(int age, int height, String name) {
        playerId=++numberOfPlayer;
        this.age = age;
        this.height = height;
        this.name = name;
        this.cumulOfpoint=0;
        objectiveAchieved = new ArrayList<>();
        unMetObjectives = new ArrayList<>();
        maxUnmetObj=1;

    }
    public Player(String name){
        this(1,1,name);
    }
    /**Acesseur et mutateur de la classe**/
    public int getPlayerId() {
        return playerId;
    }

    /**Getteur CACA**/
    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public List<Objective> getObjectiveAchieved() {
        return objectiveAchieved;
    }
    /**New**/
    public void setMaxUnmetObj(int maxUnmetObj) {
        this.maxUnmetObj = maxUnmetObj;
    }

    /****
     * Ajoute un objectif validé
     * a la liste d'objectif validé
     * du joueur
     */

    public void addObjectiveAchieved(Objective objectiveAchieved) {
        this.objectiveAchieved.add(objectiveAchieved);
        this.cumulOfpoint+=objectiveAchieved.getNumberOfPoints();
        System.out.println(name+" a gagné "+objectiveAchieved.getNumberOfPoints()+" points ✅");
    }

    public List<Objective> getUnMetObjectives() {
        return unMetObjectives;
    }
    /****
     * Ajoute un objectif piocher
     * dans le jeu
     * a la liste d'objectif Nonvalidé
     * du joueur
     */
    public void addNewObjective(Objective newObjective) {
        if(unMetObjectives.size()<maxUnmetObj){
            this.unMetObjectives.add(newObjective);
        }
    }

    /****
     * retire un objectif
     * dans la liste d'objectif Nonvalidé
     * du joueur
     */
    public Boolean withdrawUnMetObjective(Objective unMetObjective) {
        if(this.unMetObjectives.contains(unMetObjective)){
            this.unMetObjectives.remove(unMetObjective);
            return true;
        }
        return false;
    }

    /****
     *Effectue les 2
     * methodes precedentes
     * lors de son appel
     */

    public Boolean validateUnMetObjectives(Objective objective){
        withdrawUnMetObjective(objective);
        addObjectiveAchieved(objective);
        return true;
    }

    public int getCumulOfpoint() {
        return cumulOfpoint;
    }

    /**
     * Vérifier la contrainte :
     * " la parcelle est adjacente à la parcelle Spéciale (étang)  "
     *
     * @param hex {HexPlot}
     * @return {boolean}
     */
    public boolean checkPondNeighbor(@NotNull HexPlot hex) {
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
    public boolean checkTwoPlotNeighbors(@NotNull HexPlot hex) {
        Set<HexPlot> intersection = hex.plotNeighbor();
        intersection.retainAll(listOfPlots);
        return intersection.size() >= 2;
    }

    /**
     * Trouver les emplacements disponibles pour une parcelle, ie
     * - les voisins de hex qui ne sont pas dans les parcelles déjà posées
     * - et qui respectent les contraintes
     * @param hex {HexPlot}
     * @return neighborSet {Set<HexPlot>}
     */
    public Set<HexPlot> findAvailableNeighbors(@NotNull HexPlot hex){
        Set<HexPlot> neighborSet = hex.plotNeighbor();

        // Retirer les emplacements indisponibles
        // parcelles déjà posées
        neighborSet.removeAll(listOfPlots);

        // parcelles non adjacentes à l'étang ou non adjacentes à 2 parcelles
        Set<HexPlot> invalidNeighbors = new HashSet<>();
        neighborSet.forEach( hexPlot -> {
            if (! (checkPondNeighbor(hexPlot) || checkTwoPlotNeighbors(hexPlot)) )
                invalidNeighbors.add(hexPlot);
        });
        neighborSet.removeAll(invalidNeighbors);

        return neighborSet;
    }

    /****
     *Le joueur ajoute
     * une parcelle au jeu
     * en tenant compte de l'agencement
     * a une parcelle deja existante
     * Ici il choisit un parcelle
     * existante et appelle la methode
     * ChoicePlot()
     */
    public void addAplotToGame(){
        HexPlot[] allArrayPlots = listOfPlots.toArray(new HexPlot[listOfPlots.size()]);
        Random rand = new Random();
        int randNumber = rand.nextInt(listOfPlots.size());
        ChoicePlot(allArrayPlots[randNumber]);
    }

    /****
     * ChoicePlot permet au joueur
     * de choisir une parcelle inexistante
     * dans le jeu pour le moment
     * et agencent un une autre deja existante
     */

    public void ChoicePlot(@NotNull HexPlot hex){
        Set<HexPlot> neighborSet = findAvailableNeighbors(hex);
        HexPlot[] arrayPlots = neighborSet.toArray(new HexPlot[neighborSet.size()]);
        Random rand = new Random();
        int randNumber = rand.nextInt(neighborSet.size());
        listOfPlots.add(arrayPlots[randNumber]);
    }

    /****
     *Retourne les combinaisons de liste(n)
     * de parcelle dans le jeu privé du parcelle etang(0,0,0)
     */

    public  List<List<HexPlot>> listOfCombinations(int n){
        Set<HexPlot> tempList = new HashSet<>();
        listOfPlots.forEach(hexPlot -> {
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
    public Map<String,Map<Integer,Integer>> extractPlotsData(List<HexPlot> listPlots)
    {
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
                counter.put(hex.getR(), counter.get(hex.getR()) + 1);   // équivalent counter.get(card.getValeur())++
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
                counter.put(hex.getS(), counter.get(hex.getS()) + 1);   // équivalent counter.get(card.getValeur())++
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
                counter.put(hex.getQ(), counter.get(hex.getQ()) + 1);   // équivalent counter.get(card.getValeur())++
            } else {
                counter.put(hex.getQ(), 1);
            }
        }

        return counter;
    }
    /**verifier si les coordonnées des Hexplot de la liste sont une suite**/
    public Boolean sSuite(Set<Integer> setOfInteger,int size){
        List<Integer> listInteger = new ArrayList<>(setOfInteger);
        ArrayList<Integer> ListeOrdonne = new ArrayList<>();
        for(int i = 0; i < listInteger.size(); i++){
            ListeOrdonne.add(listInteger.get(i));
        }
        Collections.sort(ListeOrdonne);
        for(int j = 1; j < listInteger.size(); j++){
            if(ListeOrdonne.get(j) != ListeOrdonne.get(j-1) + 1){
                return false;
            }
        }
        return listInteger.size()==size;
    }

    /** Verifier si une liste de 3 hexplot a la configuration DIRECTSAMEPLOTS  **/
    public Boolean isDirectSamePlots(List<HexPlot> listPlots){
        Map<String ,Map<Integer,Integer>> listPlotsData = extractPlotsData(listPlots);
        Set<String> copykeyList=listPlotsData.keySet();
        boolean result=true;

        for (String stringKey: listPlotsData.keySet()) {
            if(listPlotsData.get(stringKey).size()==1){
                System.out.println(listPlotsData);
                copykeyList.remove(stringKey);
                for (String remainingKeyOfTheCopy: copykeyList) {
                    result&= sSuite(listPlotsData.get(remainingKeyOfTheCopy).keySet(),3);
                }
                if(result){
                    return result;
                }
            }

        }
        return false;
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
    public Set<PlotColor> allColorInHexPlotList(List<HexPlot> plotList){
        Set<PlotColor> colorSet= new HashSet<>();
        plotList.forEach( hexPlot -> {
            colorSet.add(hexPlot.getColor());
        });
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
            if(sSuite(listPlotsData.get(stringKey).keySet(),1)
                    ||sSuite(listPlotsData.get(stringKey).keySet(),2)
                    ||sSuite(listPlotsData.get(stringKey).keySet(),3)){
                answerset.add(listPlotsData.get(stringKey).size());
            }else
                answerset.add(-1);
        }
        return answerset;
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

    /** Verifier si une liste de 4 hexplot a la configuration QUADRILATERALSAMEPLOTS  **/
    public Boolean isQuadrilateralSamePlots(List<HexPlot> listPlots){
        Map<String ,Map<Integer,Integer>> listPlotsData = extractPlotsData(listPlots);
        Set<Integer> answerset=new HashSet<>();
        answerset.add(2);
        answerset.add(3);
        return answerset.equals(checkSetSuitConf(listPlotsData)) && listPlots.size()==4;

    }
    /** Detection des variantes de QUADRILATERALSAMESPLOTS AVEC AJOUTS DES COULEURS**/
    /**
     * QUADRILATERAL AVEC DEUX PARCELLES PINK ADJACENT
     * ET DEUX PARCELLES YELLOW ADJACENT
     * **/
    public Boolean isQuadrilateralPlots_P_Y(List<HexPlot> listPlots){
        Set<PlotColor> colorSet= new HashSet<>();
        colorSet.add(PINK);
        colorSet.add(YELLOW);
        return isQuadrilateralSamePlots(listPlots)
                && checkPairAdjacentColor(listPlots)
                && colorSet.equals(allColorInHexPlotList(listPlots));
    }
    /**
     * QUADRILATERAL AVEC DEUX PARCELLES GREEN ADJACENT
     * ET DEUX PARCELLES PINK ADJACENT
     * **/
    public Boolean isQuadrilateralPlots_G_P(List<HexPlot> listPlots){
        Set<PlotColor> colorSet= new HashSet<>();
        colorSet.add(PINK);
        colorSet.add(GREEN);
        return isQuadrilateralSamePlots(listPlots)
                && checkPairAdjacentColor(listPlots)
                && colorSet.equals(allColorInHexPlotList(listPlots));
    }
    /**
     * QUADRILATERAL AVEC DEUX PARCELLES GREEN ADJACENT
     * ET DEUX PARCELLES YELLOW ADJACENT
     * **/
    public Boolean isQuadrilateralPlots_G_Y(List<HexPlot> listPlots){
        Set<PlotColor> colorSet= new HashSet<>();
        colorSet.add(GREEN);
        colorSet.add(YELLOW);
        return isQuadrilateralSamePlots(listPlots)
                && checkPairAdjacentColor(listPlots)
                && colorSet.equals(allColorInHexPlotList(listPlots));
    }
    /** Trouver un Objectif DIRECTSAMEPLOTS dans tout le jeux**/
    public Boolean findDirectSamePlots(){
        List<List<HexPlot>> allCombinationOfthreeHexplots = listOfCombinations(3);
        for (List<HexPlot> hexPlotList:allCombinationOfthreeHexplots) {
            if(isDirectSamePlots(hexPlotList))
            {
                System.out.println(name+" a detecté un DIRECTSAMEPLOTS \uD83D\uDC4F\uD83D\uDC4F "+hexPlotList);
                return true;
            }
        }
        return false;
    }
    /** Trouver un Objectif INDIRECTSAMEPLOTS dans tout le jeu**/
    public boolean findInDirectSamePlots(PlotColor color) {
        List<List<HexPlot>> allCombinationOfthreeHexplots = listOfCombinations(3);
        for (List<HexPlot> hexPlotList:allCombinationOfthreeHexplots) {
            if(isIndirectSamePlots(hexPlotList) && allColorInHexPlotList(hexPlotList).contains(color))
            {
                System.out.println(name+" a detecté un INDIRECTSAMEPLOTS \uD83D\uDC4F\uD83D\uDC4F "+hexPlotList);
                return true;
            }
        }
        return false;
    }

    /** Trouver un Objectif QUADRILATERALSAMEPLOTS dans tout le jeu**/
    public boolean findQuadrilateralSamePlots() {
        List<List<HexPlot>> allCombinationOfthreeHexplots = listOfCombinations(4);
        for (List<HexPlot> hexPlotList:allCombinationOfthreeHexplots) {
            if(isQuadrilateralSamePlots(hexPlotList))
            {
                System.out.println(name+" a detecté un QUADRILATERALSAMEPLOTS \uD83D\uDC4F\uD83D\uDC4F "+hexPlotList);
                return true;
            }
        }
        return false;
    }
    /** Trouver un Objectif QUADRILATERALSAMEPLOTS dans tout le jeu
     * Avec deux parcelles PINK adjacent
     * et deux parcelles YELLOW adjacent**/
    public boolean findQuadrilateralPlots_P_Y() {
        List<List<HexPlot>> allCombinationOfthreeHexplots = listOfCombinations(4);
        for (List<HexPlot> hexPlotList:allCombinationOfthreeHexplots) {
            if(isQuadrilateralPlots_P_Y(hexPlotList))
            {
                System.out.println(name+" a detecté un isQuadrilateralPlots_PINK_YELLOW \uD83D\uDC4F\uD83D\uDC4F "+hexPlotList);
                return true;
            }
        }
        return false;
    }
    /** Trouver un Objectif QUADRILATERALSAMEPLOTS dans tout le jeu
     * Avec deux parcelles GREEN adjacent
     * et deux parcelles PINK adjacent**/
    public boolean findQuadrilateralPlots_G_P() {
        List<List<HexPlot>> allCombinationOfthreeHexplots = listOfCombinations(4);
        for (List<HexPlot> hexPlotList:allCombinationOfthreeHexplots) {
            if(isQuadrilateralPlots_G_P(hexPlotList))
            {
                System.out.println(name+" a detecté un isQuadrilateralPlots_PINK_GREEN \uD83D\uDC4F\uD83D\uDC4F "+hexPlotList);
                return true;
            }
        }
        return false;
    }
    /** Trouver un Objectif QUADRILATERALSAMEPLOTS dans tout le jeu
     * Avec deux parcelles GREEN adjacent
     * et deux parcelles YELLOW adjacent**/
    public boolean findQuadrilateralPlots_G_Y() {
        List<List<HexPlot>> allCombinationOfthreeHexplots = listOfCombinations(4);
        for (List<HexPlot> hexPlotList:allCombinationOfthreeHexplots) {
            if(isQuadrilateralPlots_G_Y(hexPlotList))
            {
                System.out.println(name+" a detecté un isQuadrilateralPlots_PINK_GREEN \uD83D\uDC4F\uD83D\uDC4F "+hexPlotList);
                return true;
            }
        }
        return false;
    }



    /*****
     * Le joueur appelle
     * la methode pour detecter
     * les objectifs qu'il pourrait
     * remplir a son tour
     */
    public Boolean dectectObjective(){
        for (Objective obj:unMetObjectives) {
            if(obj instanceof PlotObjective){
                if( ( ((PlotObjective) obj).getConfiguration()==
                        DIRECTSAMEPLOTS && findDirectSamePlots()))
                {
                    validateUnMetObjectives(obj);
                    return true;
                }else if( ( ((PlotObjective) obj).getConfiguration()== INDIRECTSAMEPLOTS
                        && findInDirectSamePlots(((PlotObjective) obj).getColor())))
                {
                    return  validateUnMetObjectives(obj);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== QUADRILATERALSAMEPLOTS
                        && findQuadrilateralSamePlots()))
                {
                    return  validateUnMetObjectives(obj);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== QUADRILATERALSAMEPLOTS_G_P
                        && findQuadrilateralPlots_G_P()))
                {
                    return  validateUnMetObjectives(obj);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== QUADRILATERALSAMEPLOTS_G_Y
                        && findQuadrilateralPlots_G_Y()))
                {
                    return  validateUnMetObjectives(obj);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== QUADRILATERALSAMEPLOTS_P_Y
                        && findQuadrilateralPlots_P_Y()))
                {
                    return  validateUnMetObjectives(obj);
                }

            }
        }
        return false;
    }
    /****
     *
     * Fais appelle au methode
     * pour faire jouer le joueur
     * Pour l'instant le joeur
     * fait obligatoire 2 action
     * choisir un objectif et ajouter une
     * parcelle
     */


    public Boolean play(){
        addNewObjective(listOfObjectives);
        addAplotToGame();
        return dectectObjective();
    }

    /**Rdefinition des methodes**/
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", cumulOfpoint=" + cumulOfpoint +
                '}';
    }

}
