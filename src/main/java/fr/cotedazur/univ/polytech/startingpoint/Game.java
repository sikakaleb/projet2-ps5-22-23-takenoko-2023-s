package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.objectives.Objective;
import fr.cotedazur.univ.polytech.startingpoint.supplies.*;
import fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static fr.cotedazur.univ.polytech.startingpoint.tools.BotIntelligence.PANDASTRATEGY;
import static fr.cotedazur.univ.polytech.startingpoint.tools.BotIntelligence.PLOTSTRATEGY;

public class Game {
    /**Attribut de la classe Game**/
    public static Board board;
    public static DeckOfPlots deckOfPlots;
    public static BambooStock bambooStock;
    public static DeckOfObjectifs listOfObjectives;
    public static DeckOfImprovements deckOfImprovements;
    public static Panda panda;
    public List<Player> playerList;

    /**le ou Les constructeurs de la classe**/
    public Game(Player p1, Player p2) {
        bambooStock = new BambooStock();
        deckOfPlots = new DeckOfPlots();
        listOfObjectives = new DeckOfObjectifs();
        deckOfImprovements = new DeckOfImprovements();
        board = new Board();
        panda = new Panda(new HexPlot());
        playerList = new ArrayList<>();
        initPlayer(p1,p2);
    }

    /**InitPlayer ajoute les joueurs au jeu*/
    public void initPlayer(Player p1, Player p2){
        playerList.add(p1);
        playerList.add(p2);
    }

    /**Acesseur et mutateur de la classe Game**/


    public List<Objective> getObjective() {
        return listOfObjectives;
    }

    public void setObjectives(DeckOfObjectifs objectives) {
        this.listOfObjectives = objectives;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public List<HexPlot> getDeckOfPlots() {
        return deckOfPlots;
    }
    /*Methodes particulieres de la classe*/

    /****
     *
     * Fais appelle au methode
     * pour faire jouer le joueur
     * Pour l'instant le joeur
     * fait obligatoire 2 action
     * choisir un objectif et ajouter une
     * parcelle
     */

    public Boolean play(Player player){
        Dice.Condition weather = new Dice().roll();
        actOnWeather(weather);

        if(listOfObjectives.size()==0){
            throw new IndexOutOfBoundsException("Il y a plus d'objectifs dans la liste");
        }
        Random rand = new Random();
        int randNumber = rand.nextInt(2);
        if(player.getStrategy()==PANDASTRATEGY){
            choiceObjective(player);
            Boolean temp = player.movePanda();
            return player.dectectPandaObjective();
        }else if(player.getStrategy()==PLOTSTRATEGY){
            choiceObjective(player);
            choicePlot(player);
            return player.detectPlotObjective();
        }
        if (randNumber==0){
            choiceObjective(player);
            Boolean temp = player.movePanda();
        } else if (randNumber==1) {
            choiceObjective(player);
            choicePlot(player);
        }

        return player.detectPlotObjective()&&player.dectectPandaObjective();
    }
    /**
     * choiceObjective fonction qui permet a un joueur de choisir un objectif
     * @param player {player}
     * @return {Boolean}
     */
    public Boolean choiceObjective(Player player){
        if(listOfObjectives.size()>0 && player.getUnMetObjectives().size()<5){
            Random rand = new Random();
            int randNumber = rand.nextInt(listOfObjectives.size());
            player.addNewObjective((Objective) listOfObjectives.get(randNumber));
            listOfObjectives.remove(randNumber);
            return true;
        }
        else if(listOfObjectives.size()==0 && player.unMetObjectives.size()==0){
            throw new IndexOutOfBoundsException("Il y a plus d'objectifs dans la liste");
        }
        System.out.println(player.getName()+" ne peut plus choisir d'objectif");
        System.out.println(player.getName()+" a 5 objectif non validé et est prié de les validé");
        System.out.println(player.getUnMetObjectives());
        return false;
    }

    /**
     * choicePlot fonction qui permet a un joueur de choisir une parcelle et de l'ajouter au jeu
     * @param player {player}
     * @return {Boolean}
     */
    public Boolean choicePlot(Player player){
        if (deckOfPlots.size()!=0 ) {
            board.ChoicePlot(deckOfPlots.pickPlot());
            /* le board ajoute deja dans son add modifié
            un bambou a l'ajout de la parcelle au jeu
             */
            //addBambooToPlot(board.getLastHexPlot());
            System.out.println(player.getName()+" a ajouté la parcelle suivante :"+board.getLastHexPlot());
            System.out.println("la liste des parcelles dans le jeu aprés le choix:"+board);
            return true;
        }else if(deckOfPlots.size()==0  && player.getUnMetObjectives().size()==0){
            throw new IndexOutOfBoundsException("Il y a plus de parcelles a posé");
        }
        return false;
    }

    /* display affiche les joueurs de la classe
     */
    public void display(){
        for (Player p:playerList) {
         System.out.println(p);
        }
    }

    /**
     * after throwing the dice, act on the weather condition
     * @param weatherCondition {Dice.Condition}
     */
    public void actOnWeather(Dice.Condition weatherCondition){
        switch (weatherCondition) {
            case CLOUDS :
                if(deckOfImprovements.pick() != null) {
                    PlotImprovement improvement = deckOfImprovements.pick();
                    if (board.choosePlotForImprovement() != null) {
                        board.choosePlotForImprovement().setImprovement(improvement);
                        System.out.println("La parcelle " + board.choosePlotForImprovement() + " a été amélioré par " + improvement);
                    }
                }
            default : break;
        }
    }
}
