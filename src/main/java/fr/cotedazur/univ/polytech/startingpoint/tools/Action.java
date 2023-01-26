package fr.cotedazur.univ.polytech.startingpoint.tools;

import java.util.Random;

public class Action {

       public enum GameAction {
           /**
            * Le joueur pioche 3 parcelles et en choisit 1.
            */
           PICK_PLOT,

           /**
            * Le joueur pioche 1 carte objectif de la catégorie de son choix et l’ajoute à sa main.
            */
           PICK_OBJECTIVE,
           COMPLETE_OBJECTIVE,

           /**
            * Le joueur prend 1 irrigation dans la réserve.
            * Il peut l’utiliser immédiatement ou la conserver pour les tours suivants.
            */
           PICK_IRRIGATION,
           PLACE_IRRIGATION,

           /**
            * Le joueur déplace le panda en ligne droite, dans la direction de son choix.
            * Le panda mange 1 section de bambou sur la parcelle où il termine son mouvement.
            */
           MOVE_PANDA,

           /**
            * Le joueur place un aménagement
            */
           PLACE_IMPROVEMENT,

           /**
            * Le joueur déplace le jardinier en ligne droite, dans la direction de son choix.
            * Celui-ci fait pousser 1 section de bambou sur la parcelle irriguée où il termine
            * son déplacement ainsi que sur toutes les parcelles irriguées adjacentes de la même couleur.
            */
           //MOVE_GARDENER
        }

        public GameAction[] getActions(){
           return GameAction.values();
        }

        public GameAction pick(){
                int pick = new Random().nextInt(GameAction.values().length);
                return GameAction.values()[pick];
        }

        public GameAction[] pickTwoDistinct(){
                GameAction[] actions = new GameAction[2];
                actions[0] = pick();
                do {
                    actions[1] = pick();
                } while (actions[0] == actions[1]);
                return actions;
        }
}

