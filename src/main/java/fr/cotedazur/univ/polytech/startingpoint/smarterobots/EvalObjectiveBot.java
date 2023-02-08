package fr.cotedazur.univ.polytech.startingpoint.smarterobots;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;
import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.objectives.*;

import java.util.ArrayList;
import java.util.List;

import static fr.cotedazur.univ.polytech.startingpoint.gameplay.Game.board;
import static fr.cotedazur.univ.polytech.startingpoint.tools.GardenerObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PandaObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration.*;

public class EvalObjectiveBot extends Player {
    public EvalObjectiveBot(String name) {
        super(name);
    }
    public List<MarkObjective> getMarkedUnMetObjectives(){
        List<MarkObjective> list = new ArrayList<>();
        for (Objective obj:getUnMetObjectives()) {
            MarkObjective current = new MarkObjective(obj);
            list.add(current);
        }
        return list;
    }
    /*public Objective evaluatObjectif(){
        for (:
             ) {

        }
    }*/
    public List<MarkObjective> EvaluatePlotObjective(){
        PlotObjectiveDetector detector = new PlotObjectiveDetector(board);
        List<MarkObjective> list = getMarkedUnMetObjectives();
        List<MarkObjective> result = new ArrayList<>();
        for (MarkObjective ob:list) {
            Objective obj = ob.getObjective();
            int mark=obj.getNumberOfPoints();
            if(obj instanceof PlotObjective){
                if( ( ((PlotObjective) obj).getConfiguration()==
                        DIRECTSAMEPLOTS && detector.findDirectSamePlots(((PlotObjective) obj).getColor()))) {
                    //Display.printMessage(name+" a detecté un DIRECTSAMEPLOTS \uD83D\uDC4F\uD83D\uDC4F ");
                    ob.setMark(mark*5);
                    result.add(ob);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== INDIRECTSAMEPLOTS
                        && detector.findInDirectSamePlots(((PlotObjective) obj).getColor()))) {
                    //Display.printMessage(name+" a detecté un INDIRECTSAMEPLOTS \uD83D\uDC4F\uD83D\uDC4F ");
                    ob.setMark(mark*5);
                    result.add(ob);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== QUADRILATERALSAMEPLOTS
                        && detector.findQuadrilateralSamePlots(((PlotObjective) obj).getColor()))) {
                    //Display.printMessage(name+" a detecté un QUADRILATERALSAMEPLOTS \uD83D\uDC4F\uD83D\uDC4F ");
                    ob.setMark(mark*5);
                    result.add(ob);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== QUADRILATERALSAMEPLOTS_G_P
                        && detector.findQuadrilateralPlots_G_P())) {
                    //Display.printMessage(name+" a detecté un isQuadrilateralPlots_PINK_YELLOW \uD83D\uDC4F\uD83D\uDC4F ");
                    ob.setMark(mark*5);
                    result.add(ob);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== QUADRILATERALSAMEPLOTS_G_Y
                        && detector.findQuadrilateralPlots_G_Y())) {
                   // Display.printMessage(name+" a detecté un isQuadrilateralPlots_PINK_GREEN \uD83D\uDC4F\uD83D\uDC4F ");
                    ob.setMark(mark*5);
                    result.add(ob);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== QUADRILATERALSAMEPLOTS_P_Y
                        && detector.findQuadrilateralPlots_P_Y())) {
                    //Display.printMessage(name+" a detecté un isQuadrilateralPlots_PINK_GREEN \uD83D\uDC4F\uD83D\uDC4F ");
                    ob.setMark(mark*5);
                    result.add(ob);
                }

            }
        }
        Display.printMessage("Aucun objectif parcelles detecté");
        Display.printMessage("Nombre d'objectif validé :"+this.getObjectiveAchieved().size());
        Display.printMessage("la liste d'objectif validé :"+this.getObjectiveAchieved());
        return result;
    }
    public List<MarkObjective> EvaluatePandaObjective(){
        PandaObjectiveDetector detector = new PandaObjectiveDetector(this);
        List<MarkObjective> list = getMarkedUnMetObjectives();
        List<MarkObjective> result = new ArrayList<>();

        for (MarkObjective ob:list) {
            Objective obj = ob.getObjective();
            int mark=obj.getNumberOfPoints();
            if(obj instanceof PandaObjective){

                if( ( ((PandaObjective) obj).getConfiguration()==TWO_YELLOW && detector.findTwoYellow())) {
                    //Display.printMessage(name+" a detecté un TWO_YELLOW \uD83D\uDC4F\uD83D\uDC4F ");
                    //eatenBamboos.removeTwoYellow();
                    //bambooStock.addTwoYellow();
                    ob.setMark(mark*5+3);
                    result.add(ob);
                }else if( ( ((PandaObjective) obj).getConfiguration()==TWO_GREEN && detector.findTwoGreen())) {
                    //Display.printMessage(name+" a detecté un TWO_GREEN \uD83D\uDC4F\uD83D\uDC4F ");
                    //eatenBamboos.removeTwoGreen();
                    //bambooStock.addTwoGreen();
                    ob.setMark(mark*5+3);
                    result.add(ob);
                }
                else if( ( ((PandaObjective) obj).getConfiguration()==TWO_PINK && detector.findTwoPink())) {
                    //Display.printMessage(name+" a detecté un TWO_PINK \uD83D\uDC4F\uD83D\uDC4F ");
                    //eatenBamboos.removeTwoPink();
                    //bambooStock.addTwoPink();
                    ob.setMark(mark*5+3);
                    result.add(ob);
                }
                else if( ( ((PandaObjective) obj).getConfiguration()==THREE_GREEN && detector.findThreeGreen())) {
                  //  Display.printMessage(name+" a detecté un THREE_GREEN \uD83D\uDC4F\uD83D\uDC4F ");
                  //  eatenBamboos.removeThreeGreen();
                   // bambooStock.addThreeGreen();
                    ob.setMark(mark*5+3);
                    result.add(ob);
                }
                else if( ( ((PandaObjective) obj).getConfiguration()==ONE_OF_EACH && detector.findOneOfEach())) {
                   // Display.printMessage(name+" a detecté un ONE_OF_EACH \uD83D\uDC4F\uD83D\uDC4F ");
                    //eatenBamboos.removeOneOfEach();
                    //bambooStock.addOneOfEach();
                    ob.setMark(mark*5+3);
                    result.add(ob);
                }

            }

        }
        Display.printMessage("Aucun objectif panda detecté");
        Display.printMessage("Nombre d'objectif validé :"+this.getObjectiveAchieved().size());
        Display.printMessage("la liste d'objectif validé :"+this.getObjectiveAchieved());
        return result;
    }
    public List<MarkObjective> EvaluateGardenerObjective(){
        GardenerObjectiveDetector detector = new GardenerObjectiveDetector(this);
        List<MarkObjective> list = getMarkedUnMetObjectives();
        List<MarkObjective> result = new ArrayList<>();
        for (MarkObjective ob:list) {
            Objective obj = ob.getObjective();
            int mark=obj.getNumberOfPoints();
            if(obj instanceof GardenerObjective){

                if( ( ((GardenerObjective) obj).getConfiguration()==FOUR_AND_FERTILIZER && detector.findFourAndFertilizer()!=null)) {
                    //Display.printMessage(name+" a detecté un FOUR_AND_FERTILIZER \uD83D\uDC4F\uD83D\uDC4F ");
                    //HexPlot found = detector.findFourAndFertilizer();
                    //eatenBamboos.addMultiple(4, found.getColor());
                    //found.setBamboos(null);
                    ob.setMark(mark*5);
                    result.add(ob);
                }

                else if( ( ((GardenerObjective) obj).getConfiguration()==FOUR_NO_IMPOROVEMENT && detector.findFourNoImprovement()!=null)) {
                    //Display.printMessage(name+" a detecté un FOUR_NO_IMPOROVEMENT \uD83D\uDC4F\uD83D\uDC4F ");
                    //HexPlot found = detector.findFourNoImprovement();
                    //eatenBamboos.addMultiple(4, found.getColor());
                    //found.setBamboos(null);
                    ob.setMark(mark*5);
                    result.add(ob);
                }

                else if( ( ((GardenerObjective) obj).getConfiguration()==THREE_GREEN_X4 && detector.findThreeGreenX4()!=null)) {
                    //Display.printMessage(name+" a detecté un THREE_GREEN_X4 \uD83D\uDC4F\uD83D\uDC4F ");
                    //List<HexPlot> found = detector.findThreeGreenX4();
                    //eatenBamboos.addMultiple(12, GREEN);
                    //found.forEach( hexPlot -> hexPlot.setBamboos(null));
                    ob.setMark(mark*5);
                    result.add(ob);
                }
            }

        }
        Display.printMessage("Aucun objectif jardinier detecté");
        Display.printMessage("Nombre d'objectif validé :"+this.getObjectiveAchieved().size());
        Display.printMessage("la liste d'objectif validé :"+this.getObjectiveAchieved());
        return result;
    }

}
