package fr.cotedazur.univ.polytech.startingpoint;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.PlotColor.*;
import static fr.cotedazur.univ.polytech.startingpoint.VectorDirection.*;

/** Creation d'une classe HexPlot represant un parcelle
 avec des coordonnées cartesienne 3D et sans Couleur**/
public class HexPlot {
    /** Atrribut de la classe**/
    public static final VectorDirection[] DIRECTION = new VectorDirection[]{Q_UP, Q_DOWN, S_UP, S_DOWN, R_LEFT, R_RIGHT};
    private int q;
    private int r;
    private int s;

    private PlotColor color;

    /**le ou Les constructeurs de la classe**/
    public HexPlot(int q, int s, int r) {
        this.q = q;
        this.s = s;
        this.r = r;
        this.color= NONE;
    }
    /**
     * Ajout d'un constructeur avec couleur
     */
    public HexPlot(int q, int s, int r,PlotColor color) {
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
        this(0,0,0);
        this.color=ETANG;
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

    public PlotColor getColor() {
        return color;
    }

    /** Les methodes particulieres de la classe **/

    /*
     *PlotAdd ajout une parcelle Hexagonal
     * Au jeu Suivant une direction
     */
    public HexPlot plotAdd(VectorDirection vec){
        return  new HexPlot(this.q+ vec.getQ(), this.s+vec.getS(),this.r+ vec.getR());
    }
    /**
     * PlotNeighbor renvoie Set de Plots
     *Voisins a lui
     */

    public Set<HexPlot> plotNeighbor(){
        Set<HexPlot> neighborHexPlotList= new HashSet<>();
        for (VectorDirection vec:DIRECTION) {
            neighborHexPlotList.add(plotAdd(vec));
        }
        return neighborHexPlotList;
    }
    /****
            * Verifie si 1 plot est adjacent a avec une autre de mm
     * couleur dans une liste de plots
     */
    public Boolean isAdjacentWithAnotherOnSameColor(List<HexPlot> list){
        for (HexPlot hex:list) {
            HexPlot tempHex= hex;
           if(getColor()==hex.getColor()) {
               for (HexPlot hexPlot:plotNeighbor()) {
                   if(hexPlot.getQ()==hex.getQ()
                           && hexPlot.getS()==hex.getS()
                           &&hexPlot.getR()== hex.getR()){
                       return true;
                   }
               }
           }
        }
        return false;
    }

    /** Les methodes redefinies de la classe **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HexPlot hexPlot)) return false;
        return getQ() == hexPlot.getQ() && getR() == hexPlot.getR() && getS() == hexPlot.getS() && getColor() == hexPlot.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQ(), getR(), getS(), getColor());
    }

    @Override
    public String toString() {
        return "HexPlot{" +
                "q=" + q +
                ", r=" + r +
                ", s=" + s +
                ", color=" + color +
                '}';
    }
}
