package fr.cotedazur.univ.polytech.startingpoint;

import java.util.List;
import java.util.Map;

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

    private static void pickEmperor(Player player){
        System.out.println(player.getName() + " picked the Emperor and wins 2 points");
        player.setScore(player.getScore() + 2);
    }

    /*
     * Main dans lequel se trouve une similation du jeu
     * entre 2 joueur
     * Pour cette milestone
     * on s'interesse juste a afficher le premier joueur
     * qui validera un objectif et le jeu prend fin
     */

    public static void main(String... args) {
        Boolean loop = true, lastRound = false;
        Player p1= new Player("Ted", PLOTSTRATEGY);
        Player p2 = new Player("Willfried",WITHOUTSTRATEGY);
        Game game = new Game(p1,p2);
        Emperor emperor = new Emperor(game);
        List<Player> playerList = game.getPlayerList();
        Map<Integer, Integer> objectivesForNbPlayers = Map.of(
                2, 9,
                3, 8,
                4, 7
        );
        int nbObjectivesToWin = objectivesForNbPlayers.get(game.getPlayerList().size());
        // Dans notre version, avec des bots peu intelligents, pour éviter que la partie ne soit interminable :
        // 1 : on réduit de 5 le nombre d'objectifs à atteindre pour gagner :
        nbObjectivesToWin -= 5;
        // 2 : on fixe nombre de tours prédéterminé à 20
        int nbRound = 0;

        System.out.println(board);

        System.out.println("---------------BEGIN----------------");
        while (loop && nbRound < 20){

            loop = !lastRound;

            for(Player p : playerList ){
                System.out.println();

                if (p.getObjectiveAchieved().size() == nbObjectivesToWin) {
                    System.out.println("Last round ! "+p.getName()+" has validated x objectives.");
                    pickEmperor(p);
                    lastRound = true;
                }

                System.out.println("C'est le tour de :" + p.getName());
                jeReflechis();
                if (game.play(p)) {
                    game.display();
                }
            }
        nbRound++;
        }
        emperor.judgement();
        System.exit(0);
    }

}
