package fr.cotedazur.univ.polytech.startingpoint.objectives;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.supplies.HexPlot;

import java.util.ArrayList;
import java.util.List;

import static fr.cotedazur.univ.polytech.startingpoint.gameplay.Game.board;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement.*;

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

    /**
     * Trouver un Objectif FOUR_AND_POOL dans tout le jeu
     * @return {boolean}
     */
    public HexPlot findFourAndPool(){
        for (HexPlot hexPlot : board){
            if (hexPlot.getImprovement()==POOL && hexPlot.getBamboos().size()==4)
                return hexPlot;
        }
        return null;
    }

    /**
     * Trouver un Objectif FOUR_AND_FENCE dans tout le jeu
     * @return {boolean}
     */
    public HexPlot findFourAndFence(){
        for (HexPlot hexPlot : board){
            if (hexPlot.getImprovement()==FENCE && hexPlot.getBamboos().size()==4)
                return hexPlot;
        }
        return null;
    }

    /**
     * Trouver un Objectif THREE_YELLOW_X3 dans tout le jeu
     * @return {boolean}
     */
    public List<HexPlot> findThreeYellowX3(){
        List<HexPlot> threePlots = new ArrayList<>();
        for (HexPlot hexPlot : board){
            if (hexPlot.getColor()==YELLOW && hexPlot.getBamboos().size()==3)
                threePlots.add(hexPlot);
        }
        return threePlots.size()==3 ? threePlots : null ;
    }

    /**
     * Trouver un Objectif THREE_PINK_X2 dans tout le jeu
     * @return {boolean}
     */
    public List<HexPlot> findThreePinkX2(){
        List<HexPlot> twoPlots = new ArrayList<>();
        for (HexPlot hexPlot : board){
            if (hexPlot.getColor()==PINK && hexPlot.getBamboos().size()==3)
                twoPlots.add(hexPlot);
        }
        return twoPlots.size()==2 ? twoPlots : null ;
    }

}
