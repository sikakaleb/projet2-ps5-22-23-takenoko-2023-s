package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.objectives.*;
import fr.cotedazur.univ.polytech.startingpoint.supplies.EatenBamboos;
import fr.cotedazur.univ.polytech.startingpoint.supplies.HexPlot;
import fr.cotedazur.univ.polytech.startingpoint.tools.BotIntelligence;

import java.util.*;
import static fr.cotedazur.univ.polytech.startingpoint.Game.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.BotIntelligence.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PandaObjectiveConfiguration.*;

public class Player {
    /**Attributs de la classe**/
    private static int numberOfPlayer=0;
    private int playerId;

    /**Begin Attributs CACA  --> **/
    private int age;
    private int height;

    /**End Attributs CACA  --> **/
    private String name;
    private int cumulOfpoint;
    private int maxUnmetObj;

    private BotIntelligence strategy;
    public List<Objective> objectiveAchieved ;
    public List<Objective> unMetObjectives;
    public EatenBamboos eatenBamboos;


   /**Le ou Les constructeurs de la classe **/
    public Player(int age, int height, String name) {
        playerId=++numberOfPlayer;
        this.age = age;
        this.height = height;
        this.name = name;
        this.strategy=WITHOUTSTRATEGY;
        this.cumulOfpoint=0;
        objectiveAchieved = new ArrayList<>();
        unMetObjectives = new ArrayList<>();
        maxUnmetObj=5;
        eatenBamboos = new EatenBamboos();
    }
    public Player(int age, int height, String name,BotIntelligence strategy) {
        playerId=++numberOfPlayer;
        this.age = age;
        this.height = height;
        this.name = name;
        this.strategy=strategy;
        this.cumulOfpoint=0;
        objectiveAchieved = new ArrayList<>();
        unMetObjectives = new ArrayList<>();
        maxUnmetObj=5;
        eatenBamboos = new EatenBamboos();
    }

    public Player(String name){
        this(1,1,name);
    }

    public Player(String name,BotIntelligence strategy){
        this(1,1,name,strategy);
    }
    /**Acesseur et mutateur de la classe**/
    public int getPlayerId() {
        return playerId;
    }

    public BotIntelligence getStrategy() {
        return strategy;
    }

    /**Getteur CACA**/
    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public List<Objective> getObjectiveAchieved() {
        return objectiveAchieved;
    }
    /**New**/
    public void setMaxUnmetObj(int maxUnmetObj) {
        this.maxUnmetObj = maxUnmetObj;
    }

    /****
     * Ajoute un objectif validé
     * a la liste d'objectif validé
     * du joueur
     */

    public void addObjectiveAchieved(Objective objectiveAchieved) {
        this.objectiveAchieved.add(objectiveAchieved);
        this.cumulOfpoint+=objectiveAchieved.getNumberOfPoints();
        System.out.println(name+" a gagné "+objectiveAchieved.getNumberOfPoints()+" points ✅");
    }

    public List<Objective> getUnMetObjectives() {
        return unMetObjectives;
    }
    /****
     * Ajoute un objectif piocher
     * dans le jeu
     * a la liste d'objectif Nonvalidé
     * du joueur
     */
    public void addNewObjective(Objective newObjective) {
        if(unMetObjectives.size()<maxUnmetObj){
            this.unMetObjectives.add(newObjective);
        }
    }

    /****
     * retire un objectif
     * dans la liste d'objectif Nonvalidé
     * du joueur
     */
    public Boolean withdrawUnMetObjective(Objective unMetObjective) {
        if(this.unMetObjectives.contains(unMetObjective)){
            this.unMetObjectives.remove(unMetObjective);
            return true;
        }
        return false;
    }

    /****
     *Effectue les 2
     * methodes precedentes
     * lors de son appel
     */

    public Boolean validateUnMetObjectives(Objective objective){
        withdrawUnMetObjective(objective);
        addObjectiveAchieved(objective);
        return true;
    }

    public int getCumulOfpoint() {
        return cumulOfpoint;
    }

    /*****
     * Le joueur appelle
     * la methode pour detecter
     * les objectifs qu'il pourrait
     * remplir a son tour
     */
    public Boolean detectPlotObjective(){
        PlotObjectiveDetector detector = new PlotObjectiveDetector(board);

        for (Objective obj:unMetObjectives) {
            if(obj instanceof PlotObjective){
                if( ( ((PlotObjective) obj).getConfiguration()==
                        DIRECTSAMEPLOTS && detector.findDirectSamePlots(((PlotObjective) obj).getColor()))) {
                    System.out.println(name+" a detecté un DIRECTSAMEPLOTS \uD83D\uDC4F\uD83D\uDC4F ");
                    return validateUnMetObjectives(obj);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== INDIRECTSAMEPLOTS
                        && detector.findInDirectSamePlots(((PlotObjective) obj).getColor()))) {
                    System.out.println(name+" a detecté un INDIRECTSAMEPLOTS \uD83D\uDC4F\uD83D\uDC4F ");
                    return validateUnMetObjectives(obj);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== QUADRILATERALSAMEPLOTS
                        && detector.findQuadrilateralSamePlots(((PlotObjective) obj).getColor()))) {
                    System.out.println(name+" a detecté un QUADRILATERALSAMEPLOTS \uD83D\uDC4F\uD83D\uDC4F ");
                    return validateUnMetObjectives(obj);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== QUADRILATERALSAMEPLOTS_G_P
                        && detector.findQuadrilateralPlots_G_P())) {
                    System.out.println(name+" a detecté un isQuadrilateralPlots_PINK_YELLOW \uD83D\uDC4F\uD83D\uDC4F ");
                    return validateUnMetObjectives(obj);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== QUADRILATERALSAMEPLOTS_G_Y
                        && detector.findQuadrilateralPlots_G_Y())) {
                    System.out.println(name+" a detecté un isQuadrilateralPlots_PINK_GREEN \uD83D\uDC4F\uD83D\uDC4F ");
                    return validateUnMetObjectives(obj);
                }
                else if( ( ((PlotObjective) obj).getConfiguration()== QUADRILATERALSAMEPLOTS_P_Y
                        && detector.findQuadrilateralPlots_P_Y())) {
                    System.out.println(name+" a detecté un isQuadrilateralPlots_PINK_GREEN \uD83D\uDC4F\uD83D\uDC4F ");
                    return validateUnMetObjectives(obj);
                }

            }
        }
        return false;
    }

    /*****
     * Le joueur appelle
     * la methode pour detecter
     * les objectifs qu'il pourrait
     * remplir a son tour
     */
    public Boolean dectectPandaObjective(){
        PandaObjectiveDetector detector = new PandaObjectiveDetector(this);

        for (Objective obj:unMetObjectives) {

            if(obj instanceof PandaObjective){

                if( ( ((PandaObjective) obj).getConfiguration()==TWO_YELLOW && detector.findTwoYellow())) {
                    System.out.println(name+" a detecté un TWO_YELLOW \uD83D\uDC4F\uD83D\uDC4F ");
                    eatenBamboos.removeTwoYellow();
                    bambooStock.addTwoYellow();
                    return validateUnMetObjectives(obj);
                }else if( ( ((PandaObjective) obj).getConfiguration()==TWO_GREEN && detector.findTwoGreen())) {
                    System.out.println(name+" a detecté un TWO_GREEN \uD83D\uDC4F\uD83D\uDC4F ");
                    eatenBamboos.removeTwoGreen();
                    bambooStock.addTwoGreen();
                    return validateUnMetObjectives(obj);
                }
                else if( ( ((PandaObjective) obj).getConfiguration()==TWO_PINK && detector.findTwoPink())) {
                    System.out.println(name+" a detecté un TWO_PINK \uD83D\uDC4F\uD83D\uDC4F ");
                    eatenBamboos.removeTwoPink();
                    bambooStock.addTwoPink();
                    return validateUnMetObjectives(obj);
                }
                else if( ( ((PandaObjective) obj).getConfiguration()==THREE_GREEN && detector.findThreeGreen())) {
                    System.out.println(name+" a detecté un THREE_GREEN \uD83D\uDC4F\uD83D\uDC4F ");
                    eatenBamboos.removeThreeGreen();
                    bambooStock.addThreeGreen();
                    return validateUnMetObjectives(obj);
                }
                else if( ( ((PandaObjective) obj).getConfiguration()==ONE_OF_EACH && detector.findOneOfEach())) {
                    System.out.println(name+" a detecté un ONE_OF_EACH \uD83D\uDC4F\uD83D\uDC4F ");
                    eatenBamboos.removeOneOfEach();
                    bambooStock.addOneOfEach();
                    return validateUnMetObjectives(obj);
                }

            }

        }
        return false;
    }
    /**
     * movePanda fonction qui fait deplacer le panda
     * il recherche les parcelles dans lequel il peut se deplacer et effectue le choix selon
     * @param  {}
     * @return {Boolean}
     */

    public boolean movePanda(){
        Random rand = new Random();
        List<HexPlot> movePossibilities= board.pandaNewPositionPossibilities();
        if(movePossibilities.size()!=0){
            int randNumber = rand.nextInt(movePossibilities.size());
            HexPlot next = movePossibilities.get(randNumber);
            panda.pandaMove(next);
            if(next.getBamboos().size()!=0){
                this.eatenBamboos.add(next.getBamboos().get(0));
                next.getBamboos().remove(0);
            }
            return true;
        }
        return false;
    }


    /**Rdefinition des methodes**/
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", cumulOfpoint=" + cumulOfpoint +
                '}';
    }

}
