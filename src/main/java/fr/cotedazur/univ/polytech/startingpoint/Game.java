package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    /**Attribut de la classe Game**/
    public List<HexPlot> deckOfPlots = new ArrayList<>();

    public static Set<HexPlot> listOfPlotsOnBoard;
    //public  List<Objective> listOfObjectives;

    public  static Objective listOfObjectives;

    public List<Player> playerList;

    /**le ou Les constructeurs de la classe**/
    public Game(Player p1, Player p2) {
        playerList = new ArrayList<>();
        for(int i=0;i<9;i++){
            deckOfPlots.add(new HexPlot(Color.GREEN));
            deckOfPlots.add(new HexPlot(Color.YELLOW));
            deckOfPlots.add(new HexPlot(Color.PINK));
        }
        this.listOfPlotsOnBoard=new HashSet<>();
        this.listOfPlotsOnBoard.add(new HexPlot());
        listOfObjectives=null;
        initPlayer(p1,p2);
    }

    /*InitPlayer ajoute les joueurs au jeu
     */

    public void initPlayer(Player p1, Player p2){
        playerList.add(p1);
        playerList.add(p2);
    }

    /**Acesseur et mutateur de la classe Game**/

    public static Set<HexPlot> getListOfPlotsOnBoard() {
        return listOfPlotsOnBoard;
    }
    /* A ce niveau de jeu notre jeu comprend juste un seul objectif
     * il evoluera en liste au prochain milestone
     */
    public Objective getObjective() {
        return listOfObjectives;
    }
    public void setObjective(Objective objective) {
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
