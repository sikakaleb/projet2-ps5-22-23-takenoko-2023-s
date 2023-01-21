package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.tools.Color;
import fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement;
import fr.cotedazur.univ.polytech.startingpoint.tools.VectorDirection;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.Game.bambooStock;
import static fr.cotedazur.univ.polytech.startingpoint.Game.deckOfImprovements;
import static fr.cotedazur.univ.polytech.startingpoint.tools.PlotImprovement.*;
import static fr.cotedazur.univ.polytech.startingpoint.tools.VectorDirection.*;

/** Creation d'une classe HexPlot represant un parcelle
 avec des coordonnées cartesienne 3D et avec Couleur**/
public class HexPlot {
    /** Atrribut de la classe**/
    public static final VectorDirection[] DIRECTION = new VectorDirection[]{Q_UP, Q_DOWN, S_UP, S_DOWN, R_LEFT, R_RIGHT};
    private int q;
    private int s;
    private int r;
    private Color color;
    private ArrayList<Bamboo> bamboos = new ArrayList<>(4);
    private PlotImprovement improvement = null;
    private boolean irrigated = false;
    private boolean sprouted = false;

    /**le ou Les constructeurs de la classe**/
    public HexPlot(int q, int s, int r) {
        this.q = q;
        this.s = s;
        this.r = r;
        irrigated = isPondNeighbor();
    }
    /**
     * Ajout d'un constructeur avec couleur
     */
    public HexPlot(int q, int s, int r, Color color) {
        this.q = q;
        this.s = s;
        this.r = r;
        this.color= color;
        this.bamboos = new ArrayList<>();
        irrigated = isPondNeighbor();
    }

    /**
     * Used to created plots in the deck, not on board
     * @param color {Color}
     */
    public HexPlot(Color color) {
        this.color = color;
    }

    /**Constructeur par defaut
     * initialise avec les
     * coordonnées de la
     * parcelles etang
     */
    public HexPlot(){
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

    public void setQ(int q) {
        this.q = q;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setS(int s) {
        this.s = s;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public ArrayList<Bamboo> getBamboos() { return bamboos; }

    public boolean isIrrigated(){ return irrigated; }

    public void irrigate() {
        if ( isPondNeighbor() || this.improvement == POOL)
            this.irrigated = true;
    }

    public void sprout(){
        if (!sprouted && irrigated) {
            sprouted = true;
            addBamboo();
        }
    }

    public void setImprovement(PlotImprovement plotImprovement){
        if (this.improvement != null) {
            System.out.println("Il y a déjà un aménagement sur cette parcelle");
        }

        else if (! this.bamboos.isEmpty())
            System.out.println("Impossible de placer l'emplacement, il y a un bambou sur cette parcelle");

        else {
            this.improvement = plotImprovement;
            deckOfImprovements.remove(plotImprovement);

            if (this.improvement == POOL) {
                irrigated = true;
                sprout();
            }
        }
    }

    public PlotImprovement getImprovement() { return improvement; }

    /** Les methodes particulieres de la classe **/

    /*
     *PlotAdd ajout une parcelle Hexagonal
     * Au jeu Suivant une direction
     */
    public HexPlot plotAdd(VectorDirection vec, Color color){
        return  new HexPlot(this.q+ vec.getQ(), this.s+vec.getS(),this.r+ vec.getR(), color);
    }

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

    /**
     * Renvoie s'il s'agit de la parcelle spéciale (étang)
     * @return {boolean}
     */
    public boolean isPond(){
        return (this.q==0 && this.s==0 && this.r==0);
    }

    /**
     * @return {boolean}
     */
    public boolean isPondNeighbor(){
        if (isPond()) return false;
        return (this.q==0 || this.s==0 || this.r==0);
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
                ", s=" + s +
                ", r=" + r +
                ", color=" + color +
                ", irrigated=" + irrigated +
                ", bamboos=" + bamboos +
                '}';
    }

    /**
     * Add a bamboo to plot
     */

    public void addBamboo(){
        if (this.isPond())
            System.out.println("On ne pose pas un bamboo sur la parcelle Etang");

        else if (!irrigated)
            System.out.println("On ne pose pas un bambou sur une parcelle non irriguée");

        else if (bamboos.size() == 4)
            System.out.println("Il y a trop de bambous sur cette parcelle");

        else {
            bamboos.add(new Bamboo(getColor()));
            bambooStock.remove(bambooStock.getByColor(getColor()));
            System.out.println("Un bambou "+getColor()+" pousse sur la parcelle "+this);


            if (this.getImprovement() == FERTILIZER && this.getBamboos().size() < 4){
                bamboos.add(new Bamboo(getColor()));
                bambooStock.remove(bambooStock.getByColor(getColor()));
                System.out.println(this+" possède un aménagement ENGRAIS, un deuxième bambou "+getColor()+" pousse sur la parcelle");

            }

        }
    }

}
