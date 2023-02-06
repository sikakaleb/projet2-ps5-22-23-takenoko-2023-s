package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Emperor {


    private Game game;

    public Emperor(Game game) {
        this.game = game;
    }

    public Optional<Player> judgement() {
        boolean equality= false;
        System.out.println("The Emperor will choose a winner...");
        Player p1=game.getPlayerList().get(0);
        Player p2=game.getPlayerList().get(1);
        Player winner = null;
        if(p1.getScore()>p2.getScore()) winner= p1;
        else if (p1.getScore()<p2.getScore()) winner= p2;

        if(winner==null){
            System.out.println("il y a egalité");
            /*nous avons 2 joueurs pour l'instant donc je ne vais pas encore le rendre dynamique/*
             */
            if(p1.countObjectifPanda()>p2.countObjectifPanda()) winner= p1;
            else if (p1.countObjectifPanda()<p2.countObjectifPanda()) winner= p2;
            else equality=true;
        }
        if(equality){
            System.out.println("Equality ,The winners are, "
                    +p1.getName()+" and "+p2.getName()+",\nthe won with "
                    +p1.getScore()+" Points.\n");
            return Optional.empty();
        }
        System.out.println("The winner is, "
                +winner.getName()+",\nhe won with "
                +winner.getScore()+" Points.\nhis achievedObjectifsList is  :\n"
                +winner.getObjectiveAchieved()+"\nhis unMetObjectifsList is :\n"
                +winner.getUnMetObjectives());
        return Optional.of(winner);
    }

}
