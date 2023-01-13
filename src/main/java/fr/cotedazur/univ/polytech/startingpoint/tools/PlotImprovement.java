package fr.cotedazur.univ.polytech.startingpoint.tools;

/**
 * @class PlotImprovement
 */
public enum PlotImprovement {

    /** L'enclos :
     * le panda ne peut pas manger le bambou de la parcelle où il se trouve
     */
    FENCE,

    /** L’engrais :
     * double la croissance du bambou de la parcelle où il se trouve.
     * (deux sections sont ajoutées au lieu d’une seule, dans la limite de 4 sections maximum).
     */
    FERTILIZER,

    /** Le bassin :
     * il procure de l'eau au bambou de la parcelle où il se trouve (sans être irriguée)
     * /!\ : un bassin ne peut pas créer un nouveau réseau d’irrigation
     */
    POOL
}
