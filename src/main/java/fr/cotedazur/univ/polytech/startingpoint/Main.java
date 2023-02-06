package fr.cotedazur.univ.polytech.startingpoint;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;
import java.util.Map;

import static fr.cotedazur.univ.polytech.startingpoint.Game.board;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.PANDASTRATEGY;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.PLOTSTRATEGY;

public class Main {

    @Parameter(names = { "--2thousands" }, description = "2 x 1000 parties")
    private boolean twothousand;

    @Parameter(names = "--demo", description = "Mode démo d’un seule partie avec log complet")
    private boolean demo;

    @Parameter(names = "--csv", description = "Simulation à plusieurs parties avec relecture de \"stats/gamestats.csv\" s’il existe et ajout des nouvelles statistiques")
    private boolean csv;

    /*
    * JeReflechis() utilisé pour marquer un temps de pause
    * la transition entre les tours de jeu de chaque joueurs
    */
    public static void  jeReflechis() {
        try {
            for (int i = 0; i < 6; i++) {
                Thread.sleep(6);
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
     * Main dans lequel se trouve une similation du jeu entre 2 joueurs
     */

    public static void main(String... argv) {

        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(argv);
        //main.test();
        main.runGame();
    }

    public void test() {
        System.out.println("twothousand="+twothousand+", demo="+demo+", csv="+csv);
    }

    private void runGame(){
        Boolean loop = true, lastRound = false;
        Player p1= new Player("Ted", PLOTSTRATEGY);
        Player p2 = new Player("Willfried",PANDASTRATEGY);
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
        // 2 : on fixe nombre de tours prédéterminé
        int nbRound = 0, maxRounds = 30;

        System.out.println(board);

        System.out.println("---------------BEGIN----------------");
        while (loop && nbRound < maxRounds){

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
        if (nbRound == maxRounds)
            System.out.println("Le jeu se termine au bout de "+nbRound+" tours.");

        emperor.judgement();
        System.exit(0);
    }

}
