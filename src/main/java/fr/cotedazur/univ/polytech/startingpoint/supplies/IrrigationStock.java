package fr.cotedazur.univ.polytech.startingpoint.supplies;

import java.util.*;
import java.util.logging.Level;
import java.util.stream.IntStream;

import static fr.cotedazur.univ.polytech.startingpoint.display.Display.LOGGER;

/**
 * @class IrrigationStock
 * @extend ArrayList
 * a list of placed HexPlots
 */
public class IrrigationStock  {
    /**
     * @constructor
     * Created a IrrigationStock with a single HexPlot, the pond
     */
    private List<IrrigationCanal> usedCanal;
    private List<IrrigationCanal> unUsedCanal;
    public IrrigationStock() {
        usedCanal=new ArrayList<>();
        unUsedCanal = new ArrayList<>();
        init();
    }
    /**
     * Create deck with  20 irrigated canal
     */
    public void init(){
        IntStream.range(0, 26).forEach(i -> {
            unUsedCanal.add(new IrrigationCanal());
        });
    }

    @Override
    public String toString() {
        return "IrrigationStock{\n" +
                "usedCanal=" + usedCanal +
                "\n, unUsedCanal=" + unUsedCanal +
                "\n}";
    }

    /**
     * @return {List<Hexplo>}
     * Returns the Set of plots irrigated with canals and plots that are automatically irrigated
     */
    public Set<HexPlot> getAllHexplotFrom(){
        Set<HexPlot> result=new HashSet<>();
        if(usedCanal.size()!=0){
            usedCanal.forEach(irrigationCanal->{
                if(irrigationCanal.getAvailable()==true){
                    Optional<HexPlot> source = irrigationCanal.getSourcePlot();
                    Optional<HexPlot> dest = irrigationCanal.getDestPlot();
                    result.add(source.get());
                    result.add(dest.get());
                }
            });
        }
        if (result.size()==0)result.add(new HexPlot());
        return result;
    }

    public boolean add(IrrigationCanal irrigationCanal,HexPlot src,HexPlot dst,Board board) {
        primordialCanal(board);
        if(this.getAllHexplotFrom().contains(src) && board.contains(dst)&& !exist(new IrrigationCanal(src,dst))
        && CanBeAddToSomeNetwork(new IrrigationCanal(src,dst)) && src.isAneighbor(dst)){
            irrigationCanal.setAvailableToTrue();
            if(!dst.isIrrigated()){
                dst.setIrrigatedToTrue();
                dst.addBamboo();
            }
            irrigationCanal.setSourcePlot(Optional.of(src));
            irrigationCanal.setDestPlot(Optional.of(dst));
            usedCanal.add(irrigationCanal);
            return true;
        }
        unUsedCanal.add(irrigationCanal);
        LOGGER.log(Level.FINEST,"PAS VALABLE");
        return false;


    }
    public Optional<IrrigationCanal> initAdd(IrrigationCanal irrigationCanal,HexPlot src,HexPlot dst) {
        if(!exist(new IrrigationCanal(src,dst))) {
            irrigationCanal.setAvailableToTrue();
            irrigationCanal.setSourcePlot(Optional.of(src));
            irrigationCanal.setDestPlot(Optional.of(dst));
            usedCanal.add(irrigationCanal);
            return Optional.of(irrigationCanal);
        }
        LOGGER.log(Level.FINE,"Veuillez choisir un autre emplacement");
        unUsedCanal.add(irrigationCanal);
        return Optional.empty();
    }

    public Optional<IrrigationCanal> getOneUnused(){
        if(unUsedCanal.size()!=0){
            IrrigationCanal temp=unUsedCanal.get(0);
            unUsedCanal.remove(temp);
            return Optional.of(temp);
        }
        return Optional.empty();
    }

    public List<IrrigationCanal> getUsedCanal() {
        return usedCanal;
    }

    public void setUsedCanal(List<IrrigationCanal> usedCanal) {
        this.usedCanal = usedCanal;
    }

    public List<IrrigationCanal> getUnUsedCanal() {
        return unUsedCanal;
    }

    public void setUnUsedCanal(List<IrrigationCanal> unUsedCanal) {
        this.unUsedCanal = unUsedCanal;
    }

    public void primordialCanal(Board bd){
        List<HexPlot> v2= new ArrayList<>();
        v2.add(new HexPlot(0,1,-1));
        v2.add(new HexPlot(0,-1,1));
        v2.add(new HexPlot(1,0,-1));
        v2.add(new HexPlot(1,-1,0));
        v2.add(new HexPlot(-1,0,1));
        v2.add(new HexPlot(-1,1,0));

        bd.forEach(hexPlot -> {
            if(v2.contains(hexPlot)){
                if (!exist(new IrrigationCanal(new HexPlot(),hexPlot))){
                    Optional<IrrigationCanal> res=initAdd(getOneUnused().get(),new HexPlot(),hexPlot);
                }
            }
        });
        return;
    }
    public Boolean exist(IrrigationCanal canal){
        Boolean res=false;
        for (IrrigationCanal can : usedCanal) {
            if (can.getSourcePlot().equals(canal.getSourcePlot()) &&
                    can.getDestPlot().equals(canal.getDestPlot())) {
                LOGGER.log(Level.FINE,canal+" ce canal existe deja");
                res=true;
                continue;
            }
        }
        return res;
    }
    private Boolean CanBeAddToSomeNetwork(IrrigationCanal canal){
        Boolean res=false;
        for (IrrigationCanal can : usedCanal) {
            if (can.canbeIntheSameNetwork(canal)) {
                res=true;
                continue;
            }
        }
        LOGGER.log(Level.FINE,"il n'existe pas de canal valable auquel on pourrait le relier");
        return res;
    }
}
