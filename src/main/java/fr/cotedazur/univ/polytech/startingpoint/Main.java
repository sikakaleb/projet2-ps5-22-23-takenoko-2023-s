package fr.cotedazur.univ.polytech.startingpoint;

import java.util.List;

import static fr.cotedazur.univ.polytech.startingpoint.PlotObjectiveConfiguration.DIRECTSAMEPLOTS;

public class Main {


    /*
    * JeReflechis() utilisé pour marquer un temps de pause
    * la transition entre les tours de jeu de chaque joueurs
    */
    public static void  jeReflechis() {
        try {
            for (int i = 0; i < 6; i++) {
                Thread.sleep(1000);
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
        Player p2 = new Player("Willfried");
        Game game = new Game(p1,p2);
        game.setObjective(new PlotObjective(2,DIRECTSAMEPLOTS));
        List<Player> playerList = game.getPlayerList();

        System.out.println("---------------BEGIN----------------");
        while (loop){
            for(Player p : playerList ){
                System.out.println("C'est le tour de :"+p.getName());
                jeReflechis();
                if(p.play()){
                    game.display();
                    System.exit(0);
                }else{
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
