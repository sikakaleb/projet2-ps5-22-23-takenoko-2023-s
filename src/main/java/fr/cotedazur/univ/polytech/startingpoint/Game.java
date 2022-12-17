package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static fr.cotedazur.univ.polytech.startingpoint.Color.*;
import static fr.cotedazur.univ.polytech.startingpoint.PlotObjectiveConfiguration.*;

public class Game {
    /**Attribut de la classe Game**/
    public static List<HexPlot> deckOfPlots = new ArrayList<>();

    public static Set<HexPlot> listOfPlots;
    public static BambooStock bambooStock;
    //public  List<Objective> listOfObjectives;

    public  static List<Objective> listOfObjectives;

    public List<Player> playerList;

    /**le ou Les constructeurs de la classe**/
    public Game(Player p1, Player p2) {
        playerList = new ArrayList<>();
        listOfObjectives=new ArrayList<>();
        for(int i=0;i<9;i++){
            deckOfPlots.add(new HexPlot(GREEN));
            deckOfPlots.add(new HexPlot(YELLOW));
            deckOfPlots.add(new HexPlot(PINK));
        }
        this.listOfPlots=new HashSet<>();
        this.listOfPlots.add(new HexPlot());
        bambooStock = new BambooStock();
        iniListOfObjective();
        initPlayer(p1,p2);
    }

    /*InitPlayer ajoute les joueurs au jeu
     */

    public void initPlayer(Player p1, Player p2){
        playerList.add(p1);
        playerList.add(p2);
    }

    public void iniListOfObjective(){
        listOfObjectives.add(new PlotObjective(2,DIRECTSAMEPLOTS,GREEN));
        listOfObjectives.add(new PlotObjective(3,DIRECTSAMEPLOTS,YELLOW));
        listOfObjectives.add(new PlotObjective(4,DIRECTSAMEPLOTS,PINK));
        listOfObjectives.add(new PlotObjective(2,INDIRECTSAMEPLOTS,GREEN));
        listOfObjectives.add(new PlotObjective(3,INDIRECTSAMEPLOTS,YELLOW));
        listOfObjectives.add(new PlotObjective(4,INDIRECTSAMEPLOTS,PINK));
        listOfObjectives.add(new PlotObjective(2,TRIANGULARSAMEPLOTS,GREEN));
        listOfObjectives.add(new PlotObjective(3,TRIANGULARSAMEPLOTS,YELLOW));
        listOfObjectives.add(new PlotObjective(4,TRIANGULARSAMEPLOTS,PINK));
        listOfObjectives.add(new PlotObjective(3,QUADRILATERALSAMEPLOTS,GREEN));
        listOfObjectives.add(new PlotObjective(4,QUADRILATERALSAMEPLOTS,YELLOW));
        listOfObjectives.add(new PlotObjective(5,QUADRILATERALSAMEPLOTS,PINK));
        listOfObjectives.add(new PlotObjective(3,QUADRILATERALSAMEPLOTS_G_Y));
        listOfObjectives.add(new PlotObjective(4,QUADRILATERALSAMEPLOTS_G_P));
        listOfObjectives.add(new PlotObjective(5,QUADRILATERALSAMEPLOTS_P_Y));
    }

    /**Acesseur et mutateur de la classe Game**/

    public static Set<HexPlot> getListOfPlotsOnBoard() {
        return listOfPlots;
    }
    /* A ce niveau de jeu notre jeu comprend juste un seul objectif
     * il evoluera en liste au prochain milestone
     */
    public List<Objective> getObjective() {
        return listOfObjectives;
    }
    public void setObjective(List<Objective> objective) {
        this.listOfObjectives = objective;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public List<HexPlot> getDeckOfPlots() {
        return deckOfPlots;
    }
    /*Methodes particulieres de la classe*/


    /* diplay affiche les joueurs de la classe
     */
    public void display(){
        for (Player p:playerList) {
         System.out.println(p);
        }
    }
}
