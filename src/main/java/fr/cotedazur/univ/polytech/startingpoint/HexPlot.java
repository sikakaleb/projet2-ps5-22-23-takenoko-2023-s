package fr.cotedazur.univ.polytech.startingpoint;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.VectorDirection.*;

/** Creation d'une classe HexPlot represant un parcelle
 avec des coordonnées cartesienne 3D et avec Couleur**/
public class HexPlot {
    /** Atrribut de la classe**/
    public static final VectorDirection[] DIRECTION = new VectorDirection[]{Q_UP, Q_DOWN, S_UP, S_DOWN, R_LEFT, R_RIGHT};
    private int q;
    private int r;
    private int s;

    private Color color;

    /**le ou Les constructeurs de la classe**/
    public HexPlot(int q, int s, int r, Color color) {
        this.q = q;
        this.s = s;
        this.r = r;
        this.color= color;
    }

    /**Constructeur par defaut
     * initialise avec les
     * coordonnées de la
     * parcelles etang
     */
    public  HexPlot(){
        this(0,0,0, Color.POND);
    }

    /** Les accesseurs de la classe **/

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    public int getS() {
        return s;
    }

    public Color getColor() {
        return color;
    }

    /** Les methodes particulieres de la classe **/

    /*
     *PlotAdd ajout une parcelle Hexagonal
     * Au jeu Suivant une direction
     */
    public HexPlot plotAdd(VectorDirection vec, Color color){
        return  new HexPlot(this.q+ vec.getQ(), this.s+vec.getS(),this.r+ vec.getR(), color);
    }
    /**
     * PlotNeighbor renvoie Set de Plots
     *Voisins a lui
     */

    public Set<HexPlot> plotNeighbor(){
        Set<HexPlot> neighborHexPlotList= new HashSet<>();
        for (VectorDirection vec:DIRECTION) {
            neighborHexPlotList.add(plotAdd(vec, Color.BLANK));
        }
        return neighborHexPlotList;
    }

    /**
     * Renvoie s'il s'agit de la parcelle spéciale (étang)
     * @return {boolean}
     */
    public boolean isPond(){
        return (this.q==0 && this.s==0 && this.r==0 && this.color== Color.POND);
    }

    /** Les methodes redefinies de la classe **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HexPlot hexPlot)) return false;
        return q == hexPlot.q && r == hexPlot.r && s == hexPlot.s && color == hexPlot.color;
    }

    @Override
    public String toString() {
        return "HexPlot{" +
                "q=" + q +
                ", s=" + s +
                ", r=" + r +
                ", color=" + color +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(q, s, r, color);
    }
}
