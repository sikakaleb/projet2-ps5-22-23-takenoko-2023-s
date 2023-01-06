package fr.cotedazur.univ.polytech.startingpoint;

import objectives.*;
import supplies.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    /**Attribut de la classe Game**/
    public static Board board;
    public static DeckOfPlots deckOfPlots;
    public static BambooStock bambooStock;
    public static DeckOfObjectifs listOfObjectives;
    public List<Player> playerList;

    /**le ou Les constructeurs de la classe**/
    public Game(Player p1, Player p2) {
        board = new Board();
        deckOfPlots = new DeckOfPlots();
        playerList = new ArrayList<>();
        listOfObjectives=new DeckOfObjectifs();
        bambooStock = new BambooStock();
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
        Random rand = new Random();
        if(listOfObjectives.size()==0){
            throw new IndexOutOfBoundsException("Il y a plus d'objectifs dans la liste");
        }
        int randNumber = rand.nextInt(listOfObjectives.size());
        player.addNewObjective((Objective) listOfObjectives.get(randNumber));
        listOfObjectives.remove(randNumber);
        board.ChoicePlot(deckOfPlots.pickPlot());
        board.addBambooToPlot(board.getLastHexPlot(),bambooStock.pickBamboo(board.getLastHexPlot().getColor()));
        return player.detectPlotObjective();
    }

    /**
     * Add bamboo to plot of same color
     * @param plot {HexPlot}
     * @param bamboo {Bamboo}
     */
    public void addBambooToPlot(HexPlot plot, Bamboo bamboo){
        if(bambooStock.areLeft(bamboo.getColor())) {
            plot.addBamboo(bamboo);
            bambooStock.pickBamboo(bamboo.getColor());
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
