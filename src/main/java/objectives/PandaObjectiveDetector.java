package objectives;

import fr.cotedazur.univ.polytech.startingpoint.Player;
import tools.Color;

/**
 * @class PandaObjectiveDetector
 */
public class PandaObjectiveDetector {

    private Player player;

    /**
     * @constructor
     * @param player {Player}
     */
    public PandaObjectiveDetector(Player player){
        this.player = player;
    }

    /**
     * Trouver un Objectif TWO_YELLOW dans tout le jeu
     * @return {boolean}
     */
    public boolean findTwoYellow(){
        return player.eatenBamboos.stream().filter(bamboo -> bamboo.getColor() == Color.YELLOW).count() >= 2;
    }
    /**
     * Trouver un Objectif TWO_GREEN dans tout le jeu
     * @return {boolean}
     */
    public boolean findTwoGreen(){
        return player.eatenBamboos.stream().filter(bamboo -> bamboo.getColor() == Color.GREEN).count() >= 2;
    }
    /**
     * Trouver un Objectif TWO_Pink dans tout le jeu
     * @return {boolean}
     */
    public boolean findTwoPink(){
        return player.eatenBamboos.stream().filter(bamboo -> bamboo.getColor() == Color.PINK).count() >= 2;
    }

    /**
     * Trouver un Objectif THREE_GREEN dans tout le jeu
     * @return {boolean}
     */
    public boolean findThreeGreen(){
        return player.eatenBamboos.stream().filter(bamboo -> bamboo.getColor() == Color.GREEN).count() >= 3;
    }

    /**
     * Trouver un Objectif ONE_OF_EACH dans tout le jeu
     * @return {boolean}
     */
    public boolean findOneOfEach(){
        return
                player.eatenBamboos.stream().filter(bamboo -> bamboo.getColor() == Color.YELLOW).count() >= 1
                && player.eatenBamboos.stream().filter(bamboo -> bamboo.getColor() == Color.GREEN).count() >= 1
                && player.eatenBamboos.stream().filter(bamboo -> bamboo.getColor() == Color.PINK).count() >= 1
                ;
    }
}
