package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.objectives.*;
import fr.cotedazur.univ.polytech.startingpoint.supplies.*;
import fr.cotedazur.univ.polytech.startingpoint.tools.Strategy;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.Game.bambooStock;
import static fr.cotedazur.univ.polytech.startingpoint.Game.board;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PandaObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.PANDASTRATEGY;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.WITHOUTSTRATEGY;

public class Player {
    /**Attributs de la classe**/
    private static int numberOfPlayer=0;
    private int playerId;

    /**Begin Attributs CACA  --> **/
    private int age;
    private int height;

    /**End Attributs CACA  --> **/
    private String name;
    private int score;
    private int maxUnmetObj;

    private Strategy strategy;
    public List<Objective> objectiveAchieved ;
    public List<Objective> unMetObjectives;

    private List<IrrigationCanal> canalList;
    public EatenBamboos eatenBamboos;

   /**Le ou Les constructeurs de la classe **/
    public Player(int age, int height, String name) {
        playerId=++numberOfPlayer;
        this.age = age;
        this.height = height;
        this.name = name;
        this.strategy = WITHOUTSTRATEGY;
        this.score =0;
        objectiveAchieved = new ArrayList<>();
        unMetObjectives = new ArrayList<>();
        maxUnmetObj=5;
        eatenBamboos = new EatenBamboos();
        canalList = new ArrayList<>();
    }
    public Player(int age, int height, String name, Strategy strategy) {
        playerId=++numberOfPlayer;
        this.age = age;
        this.height = height;
        this.name = name;
        this.strategy=strategy;
        this.score =0;
        objectiveAchieved = new ArrayList<>();
        unMetObjectives = new ArrayList<>();
        maxUnmetObj=5;
        eatenBamboos = new EatenBamboos();
        canalList = new ArrayList<>();
    }

    public Player(String name){
        this(1,1,name);
    }

    public Player(String name, Strategy strategy){
        this(1,1,name,strategy);
    }
    /**Acesseur et mutateur de la classe**/
    public int getPlayerId() {
        return playerId;
    }

    public Strategy getStrategy() {
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
        this.score +=objectiveAchieved.getNumberOfPoints();
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
            System.out.println(this.name+" ajoute l'objectif "+newObjective);
            System.out.println("liste d'objectifs non validé de "+this.name+" :"+this.unMetObjectives);
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public List<IrrigationCanal> getCanalList() {
        return canalList;
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
        System.out.println("Aucun objectif parcelles detecté");
        System.out.println("Nombre d'objectif validé :"+this.getObjectiveAchieved().size());
        System.out.println("la liste d'objectif validé :"+this.getObjectiveAchieved());
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
        System.out.println("Aucun objectif panda detecté");
        System.out.println("Nombre d'objectif validé :"+this.getObjectiveAchieved().size());
        System.out.println("la liste d'objectif validé :"+this.getObjectiveAchieved());
        return false;
    }

    public Boolean detectObjective(){
        return this.getStrategy()==PANDASTRATEGY ? dectectPandaObjective() : detectPlotObjective();
    }

    /**Rdefinition des methodes**/
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", cumulOfpoint=" + score +
                '}';
    }
    public Boolean addAnIrrigation(IrrigationCanal canal){
        if(canal.getAvailable()==false){
            canalList.add(canal);
            return true;
        }
        return false;
    }
    public Optional<IrrigationCanal> returnAnIrrigation(){
        if(canalList.size()==0) return Optional.empty();
        IrrigationCanal canal = canalList.get(0);
        return Optional.of(canal);
    }
    public Optional<HexPlot> findAnAvailableIrrigationSource(IrrigationStock irrigationStock){
        Set<HexPlot> validsSource = irrigationStock.getAllHexplotFrom();
        if(validsSource.size()==0) {
            System.out.println("Pas de source d'irrigarion valable dans le jeu");
            return Optional.empty();
        }
        Random rand = new Random();
        int randNumber = rand.nextInt(validsSource.size());
        List<HexPlot> listValidSource= validsSource.stream().toList();
        HexPlot hex =listValidSource.get(randNumber);
        return Optional.of(hex);
    }
    public Optional<HexPlot> findAnAvailableIrrigationDest(Board bd, HexPlot hex){
        Set<HexPlot> valids = hex.plotNeighbor();
        System.out.println(valids+"  jjj");
        Set<HexPlot> validsDest = new HashSet<>();
        for (HexPlot h1:bd) {
            for (HexPlot h2:valids) {
               if(h1.getQ() == h2.getQ() && h1.getR() == h2.getR() && h1.getS() == h2.getS())
                   validsDest.add(h1);
            }

        }
        if(validsDest.size()==0) {
            System.out.println("Pas de destination de canal de : "+hex+" valables dans le jeu");
            return Optional.empty();
        }
        Random rand = new Random();
        int randNumber = rand.nextInt(validsDest.size());
        List<HexPlot> listValidDest= validsDest.stream().toList();
        HexPlot destPlot =listValidDest.get(randNumber);
        return Optional.of(destPlot);
    }


}
