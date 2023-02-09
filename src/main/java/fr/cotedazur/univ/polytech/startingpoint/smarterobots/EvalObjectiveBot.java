package fr.cotedazur.univ.polytech.startingpoint.smarterobots;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;
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
    private SecureRandom rand;
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
        list.addAll(EvaluatePlotObjective());
        list.addAll(EvaluatePandaObjective());
        List<MarkObjective> result = new ArrayList<>();
        result.addAll(getTopMarkObjectives(list));
        int randNumber = rand.nextInt(result.size());
        return  result.get(randNumber);

    }
    public List<MarkObjective> EvaluatePlotObjective(){
        PlotObjectiveDetector detector = new PlotObjectiveDetector(getBoard());
        List<MarkObjective> list = getMarkedUnMetObjectives();
        List<MarkObjective> result = new ArrayList<>();
        for (MarkObjective ob:list) {
            Objective obj = ob.getObjective();
            int mark=obj.getNumberOfPoints();
            if(obj instanceof PlotObjective plotObjective){
                if( ( plotObjective.getConfiguration()==
                        DIRECTSAMEPLOTS && detector.findDirectSamePlots(plotObjective.getColor()))) {
                    ob.setMark(mark*5);
                    result.add(ob);
                    continue;
                }
                else if( ( plotObjective.getConfiguration()== INDIRECTSAMEPLOTS
                        && detector.findInDirectSamePlots(plotObjective.getColor()))) {
                    ob.setMark(mark*5);
                    result.add(ob);
                    continue;
                }
                else if( ( plotObjective.getConfiguration()== QUADRILATERALSAMEPLOTS
                        && detector.findQuadrilateralSamePlots(plotObjective.getColor()))) {
                    ob.setMark(mark*5);
                    result.add(ob);
                    continue;
                }
                else if( ( plotObjective.getConfiguration()== QUADRILATERALSAMEPLOTSGP
                        && detector.findQuadrilateralPlotsGP())) {
                    ob.setMark(mark*5);
                    result.add(ob);
                    continue;
                }
                else if( ( plotObjective.getConfiguration()== QUADRILATERALSAMEPLOTSGY
                        && detector.findQuadrilateralPlotsGY())) {
                    ob.setMark(mark*5);
                    result.add(ob);
                    continue;
                }
                else if( ( plotObjective.getConfiguration()== QUADRILATERALSAMEPLOTSPY
                        && detector.findQuadrilateralPlotsPY())) {
                    ob.setMark(mark*5);
                    result.add(ob);
                    continue;
                }

            }
        }
        Display.printMessage("Aucun objectif parcelles detecté");
        Display.printMessage("Nombre d'objectif validé :"+this.getObjectiveAchieved().size());
        Display.printMessage("la liste d'objectif validé :"+this.getObjectiveAchieved());
        return removeDuplicates(result);
    }
    public List<MarkObjective> EvaluatePandaObjective(){
        PandaObjectiveDetector detector = new PandaObjectiveDetector(this);
        List<MarkObjective> list = getMarkedUnMetObjectives();
        List<MarkObjective> result = new ArrayList<>();

        for (MarkObjective ob:list) {
            Objective obj = ob.getObjective();
            int mark=obj.getNumberOfPoints();
            if(obj instanceof PandaObjective pandaObjective){

                if( ( pandaObjective.getConfiguration()==TWO_YELLOW && detector.findTwoYellow())) {
                    ob.setMark(mark*5+3);
                    result.add(ob);
                }else if( ( pandaObjective.getConfiguration()==TWO_GREEN && detector.findTwoGreen())) {
                    ob.setMark(mark*5+3);
                    result.add(ob);
                }
                else if( ( pandaObjective.getConfiguration()==TWO_PINK && detector.findTwoPink())) {
                    ob.setMark(mark*5+3);
                    result.add(ob);
                }
                else if( ( pandaObjective.getConfiguration()==THREE_GREEN && detector.findThreeGreen())) {
                    ob.setMark(mark*5+3);
                    result.add(ob);
                }
                else if( ( pandaObjective.getConfiguration()==ONE_OF_EACH && detector.findOneOfEach())) {
                    ob.setMark(mark*5+3);
                    result.add(ob);
                }

            }

        }
        Display.printMessage("Aucun objectif panda detecté");
        Display.printMessage("Nombre d'objectif validé :"+this.getObjectiveAchieved().size());
        Display.printMessage("la liste d'objectif validé :"+this.getObjectiveAchieved());
        return removeDuplicates(result);
    }
    public List<MarkObjective> EvaluateGardenerObjective(){
        GardenerObjectiveDetector detector = new GardenerObjectiveDetector(this);
        List<MarkObjective> list = getMarkedUnMetObjectives();
        List<MarkObjective> result = new ArrayList<>();
        for (MarkObjective ob:list) {
            Objective obj = ob.getObjective();
            int mark=obj.getNumberOfPoints();
            if(obj instanceof GardenerObjective gardenerObjective){

                if( ( gardenerObjective.getConfiguration()==FOUR_AND_FERTILIZER && detector.findFourAndFertilizer()!=null)) {
                    ob.setMark(mark*5);
                    result.add(ob);
                }

                else if( ( gardenerObjective.getConfiguration()==FOUR_NO_IMPOROVEMENT && detector.findFourNoImprovement()!=null)) {
                    ob.setMark(mark*5);
                    result.add(ob);
                }

                else if( ( gardenerObjective.getConfiguration()==THREE_GREEN_X4 && detector.findThreeGreenX4()!=null)) {
                    ob.setMark(mark*5);
                    result.add(ob);
                }
            }

        }
        Display.printMessage("Aucun objectif jardinier detecté");
        Display.printMessage("Nombre d'objectif validé :"+this.getObjectiveAchieved().size());
        Display.printMessage("la liste d'objectif validé :"+this.getObjectiveAchieved());
        return removeDuplicates(result);
    }
    public static List<MarkObjective> removeDuplicates(List<MarkObjective> inputList) {
        Set<MarkObjective> set = new HashSet<>(inputList);
        return new ArrayList<>(set);
    }

}
