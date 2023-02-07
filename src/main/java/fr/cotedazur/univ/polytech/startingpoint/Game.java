package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;
import fr.cotedazur.univ.polytech.startingpoint.objectives.Objective;
import fr.cotedazur.univ.polytech.startingpoint.supplies.*;
import fr.cotedazur.univ.polytech.startingpoint.tools.Action;
import fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement;

import java.util.*;
import java.util.function.Consumer;

import static fr.cotedazur.univ.polytech.startingpoint.tools.Action.GameAction.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement.FENCE;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Strategy.Fa3STRATEGY;

public class Game {
    /**Attribut de la classe Game**/
    public static Board board;
    public static IrrigationStock irrigationStock;
    public static DeckOfPlots deckOfPlots;
    public static BambooStock bambooStock;
    public static DeckOfObjectifs listOfObjectives;
    public static DeckOfImprovements deckOfImprovements;
    public static Panda panda;
    public static Gardener gardener;
    public List<Player> playerList;
    public static Map<Action.GameAction, Consumer<Player>> actions;
    public Action.GameAction[] playerActions;

    /**le ou Les constructeurs de la classe**/
    public Game(Player p1, Player p2) {
        bambooStock = new BambooStock();
        deckOfPlots = new DeckOfPlots();
        listOfObjectives = new DeckOfObjectifs();
        deckOfImprovements = new DeckOfImprovements();
        irrigationStock = new IrrigationStock();
        board = new Board();
        panda = new Panda(new HexPlot());
        gardener = new Gardener(new HexPlot());
        playerList = new ArrayList<>();
        initPlayer(p1,p2);
        playerActions = new Action.GameAction[2];
        actions = Map.of(
                PICK_PLOT, this::choicePlot,
                PICK_OBJECTIVE, this::choiceObjective,
                //COMPLETE_OBJECTIVE, this::completeObjective,
                MOVE_PANDA, this::movePanda,
                MOVE_GARDENER, this::moveGardener,
                PLACE_IMPROVEMENT, this::placeImprovement,
                //PICK_IRRIGATION, this::choiceAnIrrigation,
                PLACE_IRRIGATION, this::placeAnIrrigation
        );
    }

    /**InitPlayer ajoute les joueurs au jeu*/
    public void initPlayer(Player p1, Player p2){
        playerList.add(p1);
        playerList.add(p2);
    }

    /**Acesseur et mutateur de la classe Game**/


    public List<Objective> getObjective() {
        return listOfObjectives;
    }

    public void setObjectives(DeckOfObjectifs objectives) {
        this.listOfObjectives = objectives;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public List<HexPlot> getDeckOfPlots() {
        return deckOfPlots;
    }
    /*Methodes particulieres de la classe*/

    /****
     *
     * Fais appelle au methode
     * pour faire jouer le joueur
     * Pour l'instant le joeur
     * fait obligatoire 2 action
     * choisir un objectif et ajouter une
     * parcelle
     */

    public Boolean play(Player player){

        if (deckOfPlots.size()==0){
            player.getStrategy().noMorePlots();
        }
        if (listOfObjectives.size()==0){
            player.getStrategy().noMoreObjectives();
        }

        Action.GameAction[] twoActions;
        if (player.getStrategy()==Fa3STRATEGY && player.getUnMetObjectives().size() < 5){
            twoActions = player.getStrategy().pickDifferent(PICK_OBJECTIVE);
        } else {
            twoActions = player.getStrategy().pickTwoDistinct();
        }
        playerActions[0] = twoActions[0];
        playerActions[1] = twoActions[1];

        Dice.Condition weather = new Dice().roll();
        Display.printMessage("Le dé météo tombe sur "+weather);
        actOnWeather(weather, player);

        Display.printMessage(player.getName()+" choisit les actions : "+playerActions[0]+" & "+playerActions[1]);
        Consumer<Player> action1 = actions.get(playerActions[0]);
        Consumer<Player> action2 = actions.get(playerActions[1]);
        action1.accept(player);
        action2.accept(player);

        return player.detectObjective();
    }
    /**
     * choiceObjective fonction qui permet a un joueur de choisir un objectif
     * @param player {player}
     * @return {Boolean}
     */
    public Boolean choiceObjective(Player player){
        if(listOfObjectives.size()>0 && player.getUnMetObjectives().size()<5){
            Random rand = new Random();
            int randNumber = rand.nextInt(listOfObjectives.size());
            player.addNewObjective((Objective) listOfObjectives.get(randNumber));
            listOfObjectives.remove(randNumber);
            return true;
        }
        else if(listOfObjectives.size()==0 && player.unMetObjectives.size()==0){
            throw new IndexOutOfBoundsException("Il y a plus d'objectifs dans la liste");
        }
        Display.printMessage(player.getName()+" ne peut plus choisir d'objectif");
        Display.printMessage(player.getName()+" a 5 objectif non validé et est prié de les validé");
        Display.printMessage( player.getUnMetObjectives().toString());
        return false;
    }

    /**
     * choicePlot fonction qui permet a un joueur de choisir une parcelle et de l'ajouter au jeu
     * @param player {player}
     * @return {Boolean}
     */
    public Boolean choicePlot(Player player){
        if (deckOfPlots.size()!=0 ) {
            board.ChoicePlot(deckOfPlots.pickPlot());
            /* le board ajoute deja dans son add modifié
            un bambou a l'ajout de la parcelle au jeu
             */
            //addBambooToPlot(board.getLastHexPlot());
            Display.printMessage(player.getName()+" a ajouté la parcelle suivante :"+board.getLastHexPlot());
            Display.printMessage("la liste des parcelles dans le jeu aprés le choix:"+board);
            return true;
        }else if(deckOfPlots.size()==0  && player.getUnMetObjectives().size()==0){
            throw new IndexOutOfBoundsException("Il y a plus de parcelles a posé");
        }
        return false;
    }
    /**
     * ChoiceAnIrrigation fonction qui permet a un joueur de choisir une irrigation
     * et de le mettre dans sa liste d'irrigation
     * @param p {player}
     * @return {Boolean}
     */
    public Boolean choiceAnIrrigation(Player p){
        Optional<IrrigationCanal> canal = irrigationStock.getOneUnused();
        if(canal.isPresent()){
            p.addAnIrrigation(canal.get());
            return true;
        }
        Display.printMessage("Il y a plus de canal d'irrigation disponible");
        return false;

    }
    /**
     * PlaceAnIrrigation fonction qui permet a un joueur de poser une irrigation sur le board
     * @param p {player}
     * @return {Boolean}
     */
    public Boolean placeAnIrrigation(Player p){

        if (p.getCanalList().isEmpty()){
            choiceAnIrrigation(p);
        }

        irrigationStock.primordialCanal(board);
        int exist = 2;
        Optional<IrrigationCanal> canal = p.returnAnIrrigation();
        if(canal.isEmpty()) return false;
        Optional<HexPlot> src = p.findAnAvailableIrrigationSource(irrigationStock);
        if(src.isEmpty()){
            exist--;
            return false;
        }
        Optional<HexPlot> dst = p.findAnAvailableIrrigationDest(board,src.get());
        if ((dst.isEmpty())){
            exist--;
        }
        if(exist==2 && irrigationStock.add(canal.get(),src.get(),dst.get(),board)){
            Display.printMessage( String.valueOf(canal.get()));
        }else{
            p.addAnIrrigation(canal.get());
            Display.printMessage("Impossible de placer une irrigation ici");
            return false;
        }
        return true;
}

    public  Board getBoard() {
        return board;
    }

    public IrrigationStock getIrrigationStock() {
        return irrigationStock;
    }

    /**
     * movePanda fonction qui fait deplacer le panda
     * il recherche les parcelles dans lequel il peut se deplacer et effectue le choix selon
     * @param  {}
     * @return {Boolean}
     */
    public boolean movePanda(Player player){
        Display.printMessage("la position du panda avant deplacement "+panda.getPosition());
        Display.printMessage("la liste des parcelles dans le jeu :"+board);
        Random rand = new Random();
        List<HexPlot> movePossibilities= board.getNewPositionPossibilities();
        if(movePossibilities.size()!=0){
            int randNumber = rand.nextInt(movePossibilities.size());
            HexPlot next = movePossibilities.get(randNumber);
            panda.pandaMove(next);
            Display.printMessage("la position du panda aprés deplacement"+panda.getPosition());
            eatIfBamboo(next, player);
            return true;
        }
        Display.printMessage("Impossible de faire deplacer le panda");
        return false;
    }
 
    public boolean moveGardener(Player player){
        Display.printMessage("La position du jardinier avant deplacement "+gardener.getPosition());
        Random rand = new Random();
        List<HexPlot> movePossibilities = board.getNewPositionPossibilities();
        if(movePossibilities.size()!=0){
            int randNumber = rand.nextInt(movePossibilities.size());
            HexPlot next = movePossibilities.get(randNumber);
            gardener.move(next);
            Display.printMessage("La position du jardinier aprés deplacement "+gardener.getPosition());
            next.addBamboo();
            next.plotNeighbor()
                    .stream()
                    .filter(neighbor -> board.contains(neighbor) && !neighbor.isPond() && neighbor.getColor()!=null)
                    .forEach( neighbor -> neighbor.addBamboo());
            return true;
        }
        Display.printMessage("Impossible de déplacer le jardinier");
        return false;
    }

    public boolean eatIfBamboo(HexPlot plot, Player player){
        if(plot.getBamboos().size()!=0){
            Display.printMessage("il y a de bambou sur cette parcelle");

            if (plot.getImprovement()==FENCE)
                Display.printMessage("cette parcelle est protégée par un enclos");
            else {
                Display.printMessage("panda mange un bambou de couleur " + plot.getColor());
                player.eatenBamboos.add(plot.getBamboos().get(0));
                plot.getBamboos().remove(0);
            }
            return true;
        }else{
            Display.printMessage("il y a pas de bambou sur cette parcelle");
            return false;
        }
    }

    public boolean placeImprovement(Player player){
        HexPlot plotForImrovement = board.choosePlotForImprovement();
        if (deckOfImprovements.pick() == null || plotForImrovement == null)
            return false;
        PlotImprovement improvement = deckOfImprovements.pick();
        plotForImrovement.setImprovement(improvement);
        Display.printMessage("La parcelle " + plotForImrovement + " a été amélioré par " + improvement);
        return true;
    }

    /**
     * after throwing the dice, act on the weather condition
     * @param weatherCondition {Dice.Condition}
     */
    public void actOnWeather(Dice.Condition weatherCondition, Player player){
        switch (weatherCondition) {

            case SUN:
                List<Action.GameAction> actionsToPickFrom = player.getStrategy().getActions();
                actionsToPickFrom.remove(actions.get(playerActions[0]));
                actionsToPickFrom.remove(actions.get(playerActions[1]));
                int pick = new Random().nextInt(actionsToPickFrom.size());
                Display.printMessage(player.getName()+" choisit une action supplémentaire : "+actionsToPickFrom.get(pick));

                Consumer<Player> additionnalAction = actions.get(actionsToPickFrom.get(pick));
                additionnalAction.accept(player);
                break;

            case RAIN:
                HexPlot plotForBamboo = board.choosePlotForBamboo();
                if (bambooStock.isEmpty()|| plotForBamboo == null)
                    break;
                plotForBamboo.addBamboo();
                break;

            case WIND:
                int index = (int) Math.round( Math.random());
                if (index == 0) playerActions[1]=playerActions[0];
                else playerActions[0]=playerActions[1];
                break;

            case STORM:
                int rnd = new Random().nextInt(board.size());
                HexPlot next = board.get(rnd);
                panda.pandaMove(next);
                Display.printMessage(player.getName()+" déplace le panda à : "+panda.getPosition());
                boolean hasEaten = eatIfBamboo(next, player);
                if (! hasEaten) {
                    Bamboo bamboo = new Bamboo(next.getColor());
                    player.eatenBamboos.add(bamboo);
                    bambooStock.remove(bamboo);
                    Display.printMessage("panda mange un bambou de couleur " + next.getColor());
                }
                break;

            case CLOUDS :
                placeImprovement(player);
                break;

            case MYSTERY:
                // dans les premiers tours Fa3STRATEGY n'a que 2 actions
                if (player.getStrategy() == Fa3STRATEGY && player.getStrategy().getActions().size()==2){
                    choiceAnIrrigation(player);
                }
                else {
                    Dice.Condition weather = new Dice().roll();
                    actOnWeather(weather, player);
                }
                break;

            default : break;
        }
    }

    /**
     *  display affiche les joueurs de la classe
     */
    public void display(){
        for (Player p:playerList) {
         Display.printMessage( String.valueOf(p));
        }
    }
}
