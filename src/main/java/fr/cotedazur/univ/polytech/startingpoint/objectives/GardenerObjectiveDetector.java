package fr.cotedazur.univ.polytech.startingpoint.objectives;

import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.supplies.HexPlot;

import java.util.ArrayList;
import java.util.List;

import static fr.cotedazur.univ.polytech.startingpoint.Game.board;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.GREEN;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement.FERTILIZER;

/**
 * @class GardenerObjectiveDetector
 */
public class GardenerObjectiveDetector {

    private Player player;

    /**
     * @constructor
     * @param player {Player}
     */
    public GardenerObjectiveDetector(Player player){
        this.player = player;
    }

    /**
     * Trouver un Objectif FOUR_AND_FERTILIZER dans tout le jeu
     * @return {boolean}
     */
    public HexPlot findFourAndFertilizer(){
        for (HexPlot hexPlot : board){
            if (hexPlot.getImprovement()==FERTILIZER && hexPlot.getBamboos().size()==4)
                return hexPlot;
        }
        return null;
    }
    /**
     * Trouver un Objectif FOUR_NO_IMPOROVEMENT dans tout le jeu
     * @return {boolean}
     */
    public HexPlot findFourNoImprovement(){
        for (HexPlot hexPlot : board){
            if (hexPlot.getImprovement()==null && hexPlot.getBamboos().size()==4)
                return hexPlot;
        }
        return null;
    }
    /**
     * Trouver un Objectif THREE_GREEN_X4 dans tout le jeu
     * @return {boolean}
     */
    public List<HexPlot> findThreeGreenX4(){
        List<HexPlot> fourPlots = new ArrayList<>();
        for (HexPlot hexPlot : board){
            if (hexPlot.getColor()==GREEN && hexPlot.getBamboos().size()==3)
                fourPlots.add(hexPlot);
        }
        return fourPlots.size()==4 ? fourPlots : null ;
    }

}
