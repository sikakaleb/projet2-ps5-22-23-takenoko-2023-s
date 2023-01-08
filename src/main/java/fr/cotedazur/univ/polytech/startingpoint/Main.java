package fr.cotedazur.univ.polytech.startingpoint;

import tools.BotIntelligence;

import java.util.List;

import static fr.cotedazur.univ.polytech.startingpoint.Game.board;
import static tools.BotIntelligence.*;

public class Main {


    /*
    * JeReflechis() utilisé pour marquer un temps de pause
    * la transition entre les tours de jeu de chaque joueurs
    */
    public static void  jeReflechis() {
        try {
            for (int i = 0; i < 6; i++) {
                Thread.sleep(100);
                if(i%2==0)
                System.out.print("\uD83E\uDD78 ");
            }
            System.out.println();
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
        Player p1= new Player("Ted");
        Player p2 = new Player("Willfried",PANDASTRATEGY);
        Game game = new Game(p1,p2);
        //game.setObjective(new PlotObjective(2,INDIRECTSAMEPLOTS));
        List<Player> playerList = game.getPlayerList();
        System.out.println(board);

        System.out.println("---------------BEGIN----------------");
        while (loop){
            for(Player p : playerList ){
                if (game.getDeckOfPlots().size()==0){
                    System.out.println("Plus de plots, fin");
                    System.out.println(p1);
                    System.out.println(p2);
                    System.exit(0);
                }
                else if(p.getObjectiveAchieved().size()==4) {
                    System.out.println(p + " a gagné avec 9 objectifs");
                    System.exit(0);
                }

                else {
                    System.out.println("C'est le tour de :" + p.getName());
                    jeReflechis();
                    if (game.play(p)) {
                        System.out.println(p.getUnMetObjectives());
                        System.out.println(p.getObjectiveAchieved());
                        game.display();

                    } else {
                        System.out.println(p.getUnMetObjectives());
                        System.out.println(p.getObjectiveAchieved());
                        /*
                         *Emoji qui pleure pour precisez qu'n joueur n'a
                         * pas peu valider un objectif dans son tour
                         */

                        System.out.println("\uD83D\uDE2D");
                    }
                }
            }

        }



    }

}
