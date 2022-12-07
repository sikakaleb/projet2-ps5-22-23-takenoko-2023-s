package fr.cotedazur.univ.polytech.startingpoint;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.VectorDirection.*;

/** Creation d'une classe HexPlot represant un parcelle
 avec des coordonnées cartesienne 3D et sans Couleur**/
public class HexPlot {
    /** Atrribut de la classe**/
    public static final VectorDirection[] DIRECTION = new VectorDirection[]{Q_UP, Q_DOWN, S_UP, S_DOWN, R_LEFT, R_RIGHT};
    private int q;
    private int r;
    private int s;

    /**le ou Les constructeurs de la classe**/
    public HexPlot(int q, int s, int r) {
        this.q = q;
        this.s = s;
        this.r = r;
    }

    /**Constructeur par defaut
     * initialise avec les
     * coordonnées de la
     * parcelles etang
     */
    public  HexPlot(){
        this(0,0,0);
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

    /** Les methodes redefinies de la classe **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HexPlot hexPlot)) return false;
        return q == hexPlot.q && r == hexPlot.r && s == hexPlot.s;
    }

    @Override
    public String toString() {
        return "HexPlot{" +
                "q=" + q +
                ", r=" + r +
                ", s=" + s +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(q, s, r);
    }
}
