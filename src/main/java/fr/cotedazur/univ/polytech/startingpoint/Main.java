package fr.cotedazur.univ.polytech.startingpoint;

import java.util.List;

import static fr.cotedazur.univ.polytech.startingpoint.Game.board;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.*;

public class Main {


    /*
    * JeReflechis() utilisé pour marquer un temps de pause
    * la transition entre les tours de jeu de chaque joueurs
    */
    public static void  jeReflechis() {
        try {
            for (int i = 0; i < 6; i++) {
                Thread.sleep(100);
            }
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    /*
     * Main dans lequel se trouve une similation du jeu
     * entre 2 joueur
     * Pour cette milestone
     * on s'interesse juste a afficher le premier joueur
     * qui validera un objectif et le jeu prend fin
     */

    public static void main(String... args) {
        Boolean loop =true;
        Player p1= new Player("Ted", PLOTSTRATEGY);
        Player p2 = new Player("Willfried",WITHOUTSTRATEGY);
        Game game = new Game(p1,p2);
        Referee referee = new Referee(game);
        //game.setObjective(new PlotObjective(2,INDIRECTSAMEPLOTS));
        List<Player> playerList = game.getPlayerList();
        System.out.println(board);

        System.out.println("---------------BEGIN----------------");
        while (loop){
            for(Player p : playerList ){
                System.out.println();
                if (game.getDeckOfPlots().size()==0){
                    System.out.println("Plus de plots, fin");
                    System.out.println(p1);
                    System.out.println(p2);
                    referee.judgement();
                    System.exit(0);
                }
                else if(p.getObjectiveAchieved().size()==3) {
                    referee.judgement();
                    System.exit(0);
                }
                else {
                    System.out.println("C'est le tour de :" + p.getName());
                    jeReflechis();
                    if (game.play(p)) {
                      //  System.out.println(p.getUnMetObjectives());
                      //  System.out.println(p.getObjectiveAchieved());
                        game.display();

                    }
                }
            }

        }



    }

}
