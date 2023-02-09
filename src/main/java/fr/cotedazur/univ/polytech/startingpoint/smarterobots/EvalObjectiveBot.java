package fr.cotedazur.univ.polytech.startingpoint.smarterobots;

import fr.cotedazur.univ.polytech.startingpoint.gameplay.Player;
import fr.cotedazur.univ.polytech.startingpoint.objectives.*;
import fr.cotedazur.univ.polytech.startingpoint.tools.MarkObjectiveComparator;

import java.security.SecureRandom;
import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.gameplay.Game.getBoard;
import static fr.cotedazur.univ.polytech.startingpoint.tools.GardenerObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PandaObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration.*;

public class EvalObjectiveBot extends Player {
    public EvalObjectiveBot(String name) {
        super(name);
        rand = new SecureRandom();
        byte bytes[] = new byte[20];
        rand.nextBytes(bytes);
    }
    public List<MarkObjective> getMarkedUnMetObjectives(){
        List<MarkObjective> list = new ArrayList<>();
        for (Objective obj:getUnMetObjectives()) {
            MarkObjective current = new MarkObjective(obj);
            list.add(current);
        }
        return list;
    }
    public static void sortMarkObjectiveList(List<MarkObjective> list) {
        Collections.sort(list, new MarkObjectiveComparator());
    }

    public static List<MarkObjective> getTopMarkObjectives(List<MarkObjective> list) {
        sortMarkObjectiveList(list);
        int maxMark = list.get(list.size() - 1).getMark();
        List<MarkObjective> result = new ArrayList<>();
        for (MarkObjective mo : list) {
            if (mo.getMark() == maxMark) {
                result.add(mo);
            }
        }
        return result;
    }
    public MarkObjective evaluatObjectif(){
        List<MarkObjective> list= new ArrayList<>();
        list.addAll(EvaluateGardenerObjective());
        list.addAll(evaluatePlotObjective());
        list.addAll(EvaluatePandaObjective());
        List<MarkObjective> result = new ArrayList<>();
        result.addAll(getTopMarkObjectives(list));
        int randNumber = rand.nextInt(result.size());
        return  result.get(randNumber);

    }
    public List<MarkObjective> evaluatePlotObjective(){
        PlotObjectiveDetector detector = new PlotObjectiveDetector(getBoard());
        List<MarkObjective> list = getMarkedUnMetObjectives();
        List<MarkObjective> result = new ArrayList<>();
        for (MarkObjective ob:list) {
            Objective obj = ob.getObjective();
            int mark=obj.getNumberOfPoints();
            boolean trouve= false;
            if(obj instanceof PlotObjective plotObjective){
                if( ( plotObjective.getConfiguration()==
                        DIRECTSAMEPLOTS && detector.findDirectSamePlots(plotObjective.getColor()))) {
                    trouve=true;
                }
                else if( ( plotObjective.getConfiguration()== INDIRECTSAMEPLOTS
                        && detector.findInDirectSamePlots(plotObjective.getColor()))) {
                    trouve=true;
                }
                else if( ( plotObjective.getConfiguration()== QUADRILATERALSAMEPLOTS
                        && detector.findQuadrilateralSamePlots(plotObjective.getColor()))) {
                    trouve=true;
                }
                else if( ( plotObjective.getConfiguration()== QUADRILATERALSAMEPLOTSGP
                        && detector.findQuadrilateralPlotsGP())) {
                    trouve=true;
                }
                else if( ( plotObjective.getConfiguration()== QUADRILATERALSAMEPLOTSGY
                        && detector.findQuadrilateralPlotsGY())) {
                    trouve=true;
                }
                else if( ( plotObjective.getConfiguration()== QUADRILATERALSAMEPLOTSPY
                        && detector.findQuadrilateralPlotsPY())) {
                    trouve=true;
                }
                if(trouve){
                    ob.setMark(mark*5);
                    result.add(ob);
                }

            }
        }
        return removeDuplicates(result);
    }
    public List<MarkObjective> EvaluatePandaObjective(){
        PandaObjectiveDetector detector = new PandaObjectiveDetector(this);
        List<MarkObjective> list = getMarkedUnMetObjectives();
        List<MarkObjective> result = new ArrayList<>();

        for (MarkObjective ob:list) {
            Objective obj = ob.getObjective();
            int mark=obj.getNumberOfPoints();
            boolean trouve=false;
            if(obj instanceof PandaObjective pandaObjective){

                if( ( pandaObjective.getConfiguration()==TWO_YELLOW && detector.findTwoYellow())) {
                    trouve=true;
                }else if( ( pandaObjective.getConfiguration()==TWO_GREEN && detector.findTwoGreen())) {
                    trouve=true;
                }
                else if( ( pandaObjective.getConfiguration()==TWO_PINK && detector.findTwoPink())) {
                    trouve=true;
                }
                else if( ( pandaObjective.getConfiguration()==THREE_GREEN && detector.findThreeGreen())) {
                    trouve=true;
                }
                else if( ( pandaObjective.getConfiguration()==ONE_OF_EACH && detector.findOneOfEach())) {
                    trouve=true;
                }
                if(trouve){
                    ob.setMark(mark*5+3);
                    result.add(ob);
                }

            }

        }
         return removeDuplicates(result);
    }
    public List<MarkObjective> EvaluateGardenerObjective(){
        GardenerObjectiveDetector detector = new GardenerObjectiveDetector(this);
        List<MarkObjective> list = getMarkedUnMetObjectives();
        List<MarkObjective> result = new ArrayList<>();
        for (MarkObjective ob:list) {
            Objective obj = ob.getObjective();
            int mark=obj.getNumberOfPoints();
            Boolean trouve=false;
            if(obj instanceof GardenerObjective gardenerObjective){

                if( ( gardenerObjective.getConfiguration()==FOUR_AND_FERTILIZER && detector.findFourAndFertilizer()!=null)) {
                    ob.setMark(mark*5);
                    trouve=true;
                }

                else if( ( gardenerObjective.getConfiguration()==FOUR_NO_IMPOROVEMENT && detector.findFourNoImprovement()!=null)) {
                    trouve=true;
                }

                else if( ( gardenerObjective.getConfiguration()==THREE_GREEN_X4 && detector.findThreeGreenX4()!=null)) {
                    trouve=true;
                }
                if(trouve){
                    ob.setMark(mark*5);
                    result.add(ob);
                }
            }

        }
        return removeDuplicates(result);
    }
    public static List<MarkObjective> removeDuplicates(List<MarkObjective> inputList) {
        Set<MarkObjective> set = new HashSet<>(inputList);
        return new ArrayList<>(set);
    }

}
