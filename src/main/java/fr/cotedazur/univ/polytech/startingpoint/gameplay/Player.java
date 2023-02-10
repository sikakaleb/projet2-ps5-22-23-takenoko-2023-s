package fr.cotedazur.univ.polytech.startingpoint.gameplay;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;
import fr.cotedazur.univ.polytech.startingpoint.objectives.*;
import fr.cotedazur.univ.polytech.startingpoint.supplies.*;
import fr.cotedazur.univ.polytech.startingpoint.tools.GardenerObjectiveConfiguration;
import fr.cotedazur.univ.polytech.startingpoint.tools.PandaObjectiveConfiguration;
import fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration;
import fr.cotedazur.univ.polytech.startingpoint.tools.Strategy;

import java.security.SecureRandom;
import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.gameplay.Game.getBambooStock;
import static fr.cotedazur.univ.polytech.startingpoint.gameplay.Game.getBoard;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.PICK_OBJECTIVE;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.GREEN;
import static fr.cotedazur.univ.polytech.startingpoint.tools.GardenerObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotObjectiveConfiguration.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.WITHOUTSTRATEGY;

public class Player {
    /**Attributs de la classe**/
    private static int numberOfPlayer=0;
    private int playerId;

    /**Begin Attributs CACA  --> **/
    private int height;

    /**End Attributs CACA  --> **/
    private String name;
    private int score;
    private int maxUnmetObj;

    private Strategy strategy;
    private List<Objective> objectiveAchieved ;
    private List<Objective> unMetObjectives;

    private List<IrrigationCanal> canalList;
    private EatenBamboos eatenBamboos;

    protected SecureRandom rand;
    private byte[] bytes;

    /**Le ou Les constructeurs de la classe **/
    public Player(int height, String name) {
        playerId=++numberOfPlayer;
        this.height = height;
        this.name = name;
        this.strategy = WITHOUTSTRATEGY;
        this.score =0;
        objectiveAchieved = new ArrayList<>();
        unMetObjectives = new ArrayList<>();
        maxUnmetObj=5;
        eatenBamboos = new EatenBamboos();
        canalList = new ArrayList<>();
        rand = new SecureRandom();
        bytes = new byte[20];
        rand.nextBytes(bytes);
    }
    public Player(int height, String name, Strategy strategy) {
        playerId=++numberOfPlayer;
        this.height = height;
        this.name = name;
        this.strategy=strategy;
        this.score =0;
        objectiveAchieved = new ArrayList<>();
        unMetObjectives = new ArrayList<>();
        maxUnmetObj=5;
        eatenBamboos = new EatenBamboos();
        canalList = new ArrayList<>();
        rand = new SecureRandom();
        bytes = new byte[20];
        rand.nextBytes(bytes);
    }

    public Player(String name){
        this(1,name);
    }

    public Player(String name, Strategy strategy){
        this(1,name,strategy);
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
    public PlotObjective detectPlotObjective() {
        PlotObjectiveDetector detector = new PlotObjectiveDetector(getBoard());

        for (Objective obj:unMetObjectives) {
            if (!(obj instanceof PlotObjective)) {
                continue;
            }

            PlotObjective objective = (PlotObjective) obj;
            PlotObjectiveConfiguration config = objective.getConfiguration();

            String msg = name + " a detecté un " + config + " \uD83D\uDC4F\uD83D\uDC4F";
            boolean isDetected;
            switch (config) {
                case DIRECTSAMEPLOTS:
                    isDetected = detector.findDirectSamePlots(objective.getColor());
                    break;
                case INDIRECTSAMEPLOTS:
                    isDetected = detector.findInDirectSamePlots(objective.getColor());
                    break;
                case QUADRILATERALSAMEPLOTS:
                    isDetected = detector.findQuadrilateralSamePlots(objective.getColor());
                    break;
                case QUADRILATERALSAMEPLOTSGP:
                    isDetected = detector.findQuadrilateralPlotsGP();
                    break;
                case QUADRILATERALSAMEPLOTSGY:
                    isDetected = detector.findQuadrilateralPlotsGY();
                    break;
                case QUADRILATERALSAMEPLOTSPY:
                    isDetected = detector.findQuadrilateralPlotsPY();
                    break;
                default:
                    isDetected = false;
            }

            if (Boolean.TRUE.equals(isDetected)) {
                Display.printMessage(msg);
                return objective;
            }
        }

        return null;
    }


    public PandaObjective detectPandaObjective() {
        PandaObjectiveDetector detector = new PandaObjectiveDetector(this);

        for (Objective obj : unMetObjectives) {
            if (obj instanceof PandaObjective pandaObjective) {
                PandaObjectiveConfiguration config = pandaObjective.getConfiguration();

                if (detectPandaObjective(config, detector)) {
                    updateBambooStock(config);
                    Display.printMessage(name + " a detecté un " + config + " .");
                    return pandaObjective;
                }
            }
        }
        return null;
    }

    private boolean detectPandaObjective(PandaObjectiveConfiguration config, PandaObjectiveDetector detector) {
        switch (config) {
            case TWO_YELLOW:
                return detector.findTwoYellow();

            case TWO_GREEN:
                return detector.findTwoGreen();

            case TWO_PINK:
                return detector.findTwoPink();

            case THREE_GREEN:
                return detector.findThreeGreen();

            case ONE_OF_EACH:
                return detector.findOneOfEach();

            default:
                return false;
        }
    }

    private void updateBambooStock(PandaObjectiveConfiguration config) {
        switch (config) {
            case TWO_YELLOW:
                eatenBamboos.removeTwoYellow();
                getBambooStock().addTwoYellow();
                break;

            case TWO_GREEN:
                eatenBamboos.removeTwoGreen();
                getBambooStock().addTwoGreen();
                break;

            case TWO_PINK:
                eatenBamboos.removeTwoPink();
                getBambooStock().addTwoPink();
                break;

            case THREE_GREEN:
                eatenBamboos.removeThreeGreen();
                getBambooStock().addThreeGreen();
                break;

            case ONE_OF_EACH:
                eatenBamboos.removeOneOfEach();
                getBambooStock().addOneOfEach();
                break;
        }
    }


    public GardenerObjective detectGardenerObjective(){

        GardenerObjectiveDetector detector = new GardenerObjectiveDetector(this);
        for (Objective obj:unMetObjectives) {

            if(obj instanceof GardenerObjective gardenerObjective){
                GardenerObjectiveConfiguration config = ((GardenerObjective) obj).getConfiguration();

                if(config==FOUR_AND_FERTILIZER && detector.findFourAndFertilizer()!=null) {
                    HexPlot found = detector.findFourAndFertilizer();
                    eatenBamboos.addMultiple(4, found.getColor());
                    found.setBamboos(new ArrayList<>(4));
                }

                else if(config==FOUR_AND_POOL && detector.findFourAndPool()!=null) {
                    HexPlot found = detector.findFourAndPool();
                    eatenBamboos.addMultiple(4, found.getColor());
                    found.setBamboos(new ArrayList<>(4));
                }

                else if(config==FOUR_AND_FENCE && detector.findFourAndFence()!=null) {
                    HexPlot found = detector.findFourAndFence();
                    eatenBamboos.addMultiple(4, found.getColor());
                    found.setBamboos(new ArrayList<>(4));
                }

                else if(config==FOUR_NO_IMPOROVEMENT && detector.findFourNoImprovement()!=null) {
                    HexPlot found = detector.findFourNoImprovement();
                    eatenBamboos.addMultiple(4, found.getColor());
                    found.setBamboos(new ArrayList<>(4));
                }

                else if(config==THREE_GREEN_X4 && detector.findThreeGreenX4()!=null) {
                    List<HexPlot> found = detector.findThreeGreenX4();
                    eatenBamboos.addMultiple(12, GREEN);
                    found.forEach( hexPlot -> hexPlot.setBamboos(new ArrayList<>(4)));
                }
                Display.printMessage(name+" a  detecté un "+config+" .");
                return gardenerObjective;
            }

        }
        return null;
    }

    public Boolean detectObjective(){

        List<Objective> detectedObjectives = Arrays.asList(detectPlotObjective(), detectPandaObjective(), detectGardenerObjective());
        for (Objective obj : detectedObjectives){
            if (obj != null) {
                validateUnMetObjectives(obj);
            }
        }
        if (detectedObjectives.stream().anyMatch(Objects::nonNull)){
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
        if(Boolean.TRUE.equals(!canal.getAvailable())){
            canalList.add(canal);
            return true;
        }
        return false;
    }

    public Optional<IrrigationCanal> returnAnIrrigation(){
        if(canalList.isEmpty()) return Optional.empty();
        IrrigationCanal canal = canalList.get(0);
        canalList.remove(canal);
        return Optional.of(canal);
    }

    public Optional<HexPlot> findAnAvailableIrrigationSource(IrrigationStock irrigationStock){
        Set<HexPlot> validsSource = new HashSet<>();
        Set<HexPlot> sources = irrigationStock.getAllHexplotFrom();
        sources.forEach(i->{
            if(!i.equals(new HexPlot())){
                validsSource.add(i);
            }
        });
        if(validsSource.isEmpty()) {
            Display.printMessage("Pas de source d'irrigarion valable dans le jeu");
        }else{
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

    public void setObjectiveAchieved(List<Objective> objectives) {
        objectiveAchieved=objectives;
    }

    public void setCanalList(List<IrrigationCanal> canalList) {
        this.canalList = canalList;
    }

    public EatenBamboos getEatenBamboos() {
        return eatenBamboos;
    }

    public void setEatenBamboos(EatenBamboos eatenBamboos) {
        this.eatenBamboos = eatenBamboos;
    }


}
