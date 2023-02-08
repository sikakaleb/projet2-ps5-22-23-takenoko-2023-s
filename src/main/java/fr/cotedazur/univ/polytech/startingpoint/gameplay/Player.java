package fr.cotedazur.univ.polytech.startingpoint.gameplay;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;
import fr.cotedazur.univ.polytech.startingpoint.objectives.*;
import fr.cotedazur.univ.polytech.startingpoint.supplies.*;
import fr.cotedazur.univ.polytech.startingpoint.tools.GardenerObjectiveConfiguration;
import fr.cotedazur.univ.polytech.startingpoint.tools.PandaObjectiveConfiguration;
import fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration;
import fr.cotedazur.univ.polytech.startingpoint.tools.Strategy;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.gameplay.Game.bambooStock;
import static fr.cotedazur.univ.polytech.startingpoint.gameplay.Game.board;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.PICK_OBJECTIVE;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.GardenerObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PandaObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration.*;
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

    private Random rand;

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

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
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
        Display.printMessage( name+" a gagne "+objectiveAchieved.getNumberOfPoints()+" points ✅");
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
            Display.printMessage(this.name+" ajoute l'objectif "+newObjective);
            Display.printMessage("liste d'objectifs non valide de "+this.name+" :"+this.unMetObjectives);
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
        this.getStrategy().add(PICK_OBJECTIVE);

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

    /**
     * Détection des objectifs :
     */
    public PlotObjective detectPlotObjective(){

        PlotObjectiveDetector detector = new PlotObjectiveDetector(board);
        for (Objective obj:unMetObjectives) {

            if(obj instanceof PlotObjective){
                PlotObjective objective = (PlotObjective) obj;
                PlotObjectiveConfiguration config = objective.getConfiguration();

                if (    config==DIRECTSAMEPLOTS && detector.findDirectSamePlots(objective.getColor())
                    ||  config==INDIRECTSAMEPLOTS && detector.findInDirectSamePlots(objective.getColor())
                    ||  config==QUADRILATERALSAMEPLOTS && detector.findQuadrilateralSamePlots(objective.getColor())
                    ||  config==QUADRILATERALSAMEPLOTS_G_P && detector.findQuadrilateralPlots_G_P()
                    ||  config==QUADRILATERALSAMEPLOTS_G_Y && detector.findQuadrilateralPlots_G_Y()
                    ||  config==QUADRILATERALSAMEPLOTS_P_Y && detector.findQuadrilateralPlots_P_Y()
                ) {
                    Display.printMessage(name+" a detecté un "+config+" \uD83D\uDC4F\uD83D\uDC4F ");
                    return objective;
                }
            }
        }
        return null;
    }

    public PandaObjective detectPandaObjective(){

        PandaObjectiveDetector detector = new PandaObjectiveDetector(this);
        for (Objective obj:unMetObjectives) {

            if(obj instanceof PandaObjective){
                PandaObjectiveConfiguration config = ((PandaObjective) obj).getConfiguration();

                if(    config==TWO_YELLOW && detector.findTwoYellow()
                    || config==TWO_GREEN && detector.findTwoGreen()
                    || config==TWO_PINK && detector.findTwoPink()
                    || config==THREE_GREEN && detector.findThreeGreen()
                    || config==ONE_OF_EACH && detector.findOneOfEach()) {

                    switch (config) {
                        case TWO_YELLOW:
                            eatenBamboos.removeTwoYellow();
                            bambooStock.addTwoYellow();
                            break;

                        case TWO_GREEN:
                            eatenBamboos.removeTwoGreen();
                            bambooStock.addTwoGreen();
                            break;

                        case TWO_PINK:
                            eatenBamboos.removeTwoPink();
                            bambooStock.addTwoPink();
                            break;

                        case THREE_GREEN:
                            eatenBamboos.removeThreeGreen();
                            bambooStock.addThreeGreen();
                            break;

                        case ONE_OF_EACH:
                            eatenBamboos.removeOneOfEach();
                            bambooStock.addOneOfEach();
                            break;
                    }
                    Display.printMessage(name + " a detecté un " + config + " \uD83D\uDC4F\uD83D\uDC4F ");
                    return (PandaObjective) obj;
                }
            }
        }
        return null;
    }

    public GardenerObjective detectGardenerObjective(){

        GardenerObjectiveDetector detector = new GardenerObjectiveDetector(this);
        for (Objective obj:unMetObjectives) {

            if(obj instanceof GardenerObjective){
                GardenerObjectiveConfiguration config = ((GardenerObjective) obj).getConfiguration();

                if(config==FOUR_AND_FERTILIZER && detector.findFourAndFertilizer()!=null) {
                    HexPlot found = detector.findFourAndFertilizer();
                    eatenBamboos.addMultiple(4, found.getColor());
                    found.setBamboos(null);
                }

                else if(config==FOUR_NO_IMPOROVEMENT && detector.findFourNoImprovement()!=null) {
                    HexPlot found = detector.findFourNoImprovement();
                    eatenBamboos.addMultiple(4, found.getColor());
                    found.setBamboos(null);
                }

                else if(config==THREE_GREEN_X4 && detector.findThreeGreenX4()!=null) {
                    List<HexPlot> found = detector.findThreeGreenX4();
                    eatenBamboos.addMultiple(12, GREEN);
                    found.forEach( hexPlot -> hexPlot.setBamboos(null));
                }
                Display.printMessage(name+" a detecté un "+config+" \uD83D\uDC4F\uD83D\uDC4F ");
                return (GardenerObjective) obj;
            }

        }
        return null;
    }

    public Boolean detectObjective(){

        List<Objective> detectedObjectives = Arrays.asList(new Objective[]{detectPlotObjective(), detectPandaObjective(), detectGardenerObjective()});
        for (Objective obj : detectedObjectives){
            if (obj != null) {
                validateUnMetObjectives(obj);
            }
        }
        if (detectedObjectives.stream().anyMatch(objective -> objective != null)){
            return true;
        }
        else {
            Display.printMessage("Aucun objectif detecté");
            Display.printMessage("Nombre d'objectif validé :"+this.getObjectiveAchieved().size());
            Display.printMessage("la liste d'objectif validé :"+this.getObjectiveAchieved());
            return false;
        }
    }

    /**Rdefinition des methodes**/
    @Override
    public String toString() {
        return name+" : "+score+" points et "+objectiveAchieved.size()+" objectifs atteints.";
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
        canalList.remove(canal);
        return Optional.of(canal);
    }

    public Optional<HexPlot> findAnAvailableIrrigationSource(IrrigationStock irrigationStock){
        Set<HexPlot> validsSource = new HashSet<>();
        Set<HexPlot> Sources = irrigationStock.getAllHexplotFrom();
        Sources.forEach(i->{
            if(!i.equals(new HexPlot())){
                validsSource.add(i);
            }
        });/*
        throw new RuntimeException(""+Sources);*/
        if(validsSource.isEmpty()) {
            Display.printMessage("Pas de source d'irrigarion valable dans le jeu");
        }else{
            rand = new Random();
            int randNumber = rand.nextInt(validsSource.size());
            List<HexPlot> listValidSource= validsSource.stream().toList();
            HexPlot hex =listValidSource.get(randNumber);
            return Optional.of(hex);
        }
        return Optional.empty();

    }

    public Optional<HexPlot> findAnAvailableIrrigationDest(Board bd, HexPlot hex){
        Set<HexPlot> valids = hex.plotNeighbor();
        valids.remove(new HexPlot());
        Set<HexPlot> validsDest = new HashSet<>();
        for (HexPlot h1:bd) {
            for (HexPlot h2:valids) {
                if(h1.getQ() == h2.getQ() && h1.getR() == h2.getR() && h1.getS() == h2.getS())
                    validsDest.add(h1);
            }
        }

        if(validsDest.isEmpty()) {
            Display.printMessage("Pas de destination de canal de : "+hex+" valables dans le jeu");
        }else {
            rand = new Random();
            int randNumber = rand.nextInt(validsDest.size());
            List<HexPlot> listValidDest= validsDest.stream().toList();
            HexPlot destPlot =listValidDest.get(randNumber);
            return Optional.of(destPlot);
        }
        return Optional.empty();
    }

    public int countObjectifPanda(){
        int result =0;
        for (Objective obj:objectiveAchieved) {
            if(obj instanceof PandaObjective) result++;
        }
        return result;
    }

    public void pickEmperor(){
        Display.printMessage( name + " pioche la carte Empereur et gagne 2 points");
        this.score += 2;
    }

}
