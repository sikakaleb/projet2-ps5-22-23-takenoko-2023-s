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
    private byte[] bytes;
    public EvalObjectiveBot(String name) {
        super(name);
        rand = new SecureRandom();
        bytes = new byte[20];
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
        list.addAll(evaluateGardenerObjective());
        list.addAll(evaluatePlotObjective());
        list.addAll(evaluatePandaObjective());
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
            if(Boolean.TRUE.equals((obj instanceof PlotObjective plotObjective)&&
                 ( plotObjective.getConfiguration()== DIRECTSAMEPLOTS && detector.findDirectSamePlots(plotObjective.getColor())
                    ||
                    ( plotObjective.getConfiguration()== INDIRECTSAMEPLOTS && detector.findInDirectSamePlots(plotObjective.getColor()))
                   ||
                    ( plotObjective.getConfiguration()== QUADRILATERALSAMEPLOTS && detector.findQuadrilateralSamePlots(plotObjective.getColor()))
                    ||
                    ( plotObjective.getConfiguration()== QUADRILATERALSAMEPLOTSGP && detector.findQuadrilateralPlotsGP())
                    ||
                    ( plotObjective.getConfiguration()== QUADRILATERALSAMEPLOTSGY && detector.findQuadrilateralPlotsGY())
                    ||
                    ( plotObjective.getConfiguration()== QUADRILATERALSAMEPLOTSPY && detector.findQuadrilateralPlotsPY()))))
                {
                    ob.setMark(mark*5);
                    result.add(ob);
                }

            }

        return removeDuplicates(result);
    }
    public List<MarkObjective> evaluatePandaObjective(){
        PandaObjectiveDetector detector = new PandaObjectiveDetector(this);
        List<MarkObjective> list = getMarkedUnMetObjectives();
        List<MarkObjective> result = new ArrayList<>();

        for (MarkObjective ob:list) {
            Objective obj = ob.getObjective();
            int mark=obj.getNumberOfPoints();
            if(Boolean.TRUE.equals(
                    (obj instanceof PandaObjective pandaObjective) &&(
                 ( pandaObjective.getConfiguration()==TWO_YELLOW && detector.findTwoYellow())
                  ||
                 ( pandaObjective.getConfiguration()==TWO_GREEN && detector.findTwoGreen())
                   ||
                 ( pandaObjective.getConfiguration()==TWO_PINK && detector.findTwoPink())
                    ||
                 ( pandaObjective.getConfiguration()==THREE_GREEN && detector.findThreeGreen())
                    ||
                ( pandaObjective.getConfiguration()==ONE_OF_EACH && detector.findOneOfEach())) ))
                {
                    ob.setMark(mark*5+3);
                    result.add(ob);
                }


        }
         return removeDuplicates(result);
    }
    public List<MarkObjective> evaluateGardenerObjective(){
        GardenerObjectiveDetector detector = new GardenerObjectiveDetector(this);
        List<MarkObjective> list = getMarkedUnMetObjectives();
        List<MarkObjective> result = new ArrayList<>();
        for (MarkObjective ob:list) {
            Objective obj = ob.getObjective();
            int mark=obj.getNumberOfPoints();
            if(Boolean.TRUE.equals((obj instanceof GardenerObjective gardenerObjective)&&(
                   ( gardenerObjective.getConfiguration()==FOUR_AND_FERTILIZER && detector.findFourAndFertilizer()!=null)
                    ||( gardenerObjective.getConfiguration()==FOUR_NO_IMPOROVEMENT && detector.findFourNoImprovement()!=null)
                ||( gardenerObjective.getConfiguration()==THREE_GREEN_X4 && detector.findThreeGreenX4()!=null))))
                {
                        ob.setMark(mark*5);
                        result.add(ob);
                }
            }
        return removeDuplicates(result);
    }
    public static List<MarkObjective> removeDuplicates(List<MarkObjective> inputList) {
        Set<MarkObjective> set = new HashSet<>(inputList);
        return new ArrayList<>(set);
    }

}
