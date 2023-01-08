package fr.cotedazur.univ.polytech.startingpoint;

import objectives.*;
import supplies.*;
import tools.BotIntelligence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static tools.BotIntelligence.PANDASTRATEGY;
import static tools.BotIntelligence.PLOTSTRATEGY;

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

    /* A ce niveau de jeu notre jeu comprend juste un seul objectif
     * il evoluera en liste au prochain milestone
     */
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
            addBambooToPlot(board.getLastHexPlot());
            return true;
        }else if(deckOfPlots.size()==0  && player.getUnMetObjectives().size()==0){
            throw new IndexOutOfBoundsException("Il y a plus de parcelles a posé");
        }
        return false;
    }

    /**
     * Add bamboo to plot of same color
     * @param plot {HexPlot}
     */
    public void addBambooToPlot(HexPlot plot){
        if( !plot.isPond() && !bambooStock.areLeft(plot.getColor())) {
            throw new IndexOutOfBoundsException("Il y a plus de bambou " + plot.getColor());
        } else {
            plot.addBamboo();
        }
    }

    /* diplay affiche les joueurs de la classe
     */
    public void display(){
        for (Player p:playerList) {
         System.out.println(p);
        }
    }
}
