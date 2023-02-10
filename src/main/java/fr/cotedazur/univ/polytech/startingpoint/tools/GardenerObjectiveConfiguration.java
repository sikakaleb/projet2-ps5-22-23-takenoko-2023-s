package fr.cotedazur.univ.polytech.startingpoint.tools;

/**
 * @class GardenerObjectiveConfiguration de type enum
 * Types de configuration pour les objectifs de jardinier :
 */

public enum GardenerObjectiveConfiguration {

    /** Un bambou de taille 4 avec un aménagement engrais. */
    FOUR_AND_FERTILIZER,

    /** Un bambou de taille 4 avec un aménagement bassin. */
    FOUR_AND_POOL,

    /** Un bambou de taille 4 sur une parcelle sans aménagement. */
    FOUR_NO_IMPOROVEMENT,

    /** Un bambou de taille 4 avec un aménagement enclos. */
    FOUR_AND_FENCE,

    /** Au moins 4 bambous verts exactement de taille 3 sur des
     parcelles (adjacentes ou non) avec ou sans aménagement. */
    THREE_GREEN_X4,

    /** Au moins 3 bambous jaunes exactement de taille 3 sur des
     parcelles (adjacentes ou non) avec ou sans aménagement. */
    THREE_YELLOW_X3,

    /** Au moins 2 bambous roses exactement de taille 3 sur des
     parcelles (adjacentes ou non) avec ou sans aménagement. */
    THREE_PINK_X2,


}
