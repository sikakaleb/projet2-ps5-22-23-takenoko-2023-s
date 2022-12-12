package fr.cotedazur.univ.polytech.startingpoint;

/**Enumeration de type de configuration parcelle
 * Que nous pouvons aobtenir**/
public enum PlotObjectiveConfiguration {

    /*
     * DIRECTSAMEPLOTS
     * 3 Parcelle Alignée
     *  #-#-#
     */
    DIRECTSAMEPLOTS,
    /*
     * INDIRECTSAMEPLOTS
     * ressemblant au suivant mais 2 parcelle
     * sont se
     *  #-()-#
     *    #
     */
    INDIRECTSAMEPLOTS,
    /*
     * INDIRECTSAMEPLOTS
     * 3 Parcelle Alignée
     *  #-#
     *   #
     */
    TRIANGULARSAMEPLOTS,
    /*
     * QUADRILATERALSAMEPLOTS
     * 3 Parcelle Alignée
     *    #-#
     *   #-#
     */
    QUADRILATERALSAMEPLOTS,
    /*
     * P -->Parcelle de couleur rose (pink)
     * Y -->Parcelle de couleur jaune (Yellow)
     * QUADRILATERALSAMEPLOTS_P_Y PINK ET YELLOW
     * 3 Parcelle Alignée
     *    P-P
     *   Y-Y
     */
    QUADRILATERALSAMEPLOTS_P_Y,
    /*
     * P -->Parcelle de couleur rose (pink)
     * G -->Parcelle de couleur verte (Green)
     * QUADRILATERALSAMEPLOTS_G_P PINK ET YELLOW
     * 3 Parcelle Alignée
     *    P-P
     *   G-G
     */
    QUADRILATERALSAMEPLOTS_G_P,
    /*
     * P -->Parcelle de couleur rose (pink)
     * Y -->Parcelle de couleur jaune (Yellow)
     * QUADRILATERALSAMEPLOTS_P_Y PINK ET YELLOW
     * 3 Parcelle Alignée
     *    G-G
     *   Y-Y
     */
    QUADRILATERALSAMEPLOTS_G_Y;
}
