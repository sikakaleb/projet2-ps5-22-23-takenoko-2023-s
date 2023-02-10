package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;

import java.util.*;
import java.util.stream.IntStream;

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
        IntStream.range(0, 26).forEach(i ->
            unUsedCanal.add(new IrrigationCanal())
        );
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
        if(!usedCanal.isEmpty()){
            usedCanal.forEach(irrigationCanal->{
                if(Boolean.TRUE.equals(irrigationCanal.getAvailable())){
                    Optional<HexPlot> source = irrigationCanal.getSourcePlot();
                    Optional<HexPlot> dest = irrigationCanal.getDestPlot();
                    if(source.isPresent()&&dest.isPresent()){
                    result.add(source.get());
                    result.add(dest.get());}
                }
            });
        }
        if (result.isEmpty())result.add(new HexPlot());
        return result;
    }

    public boolean add(IrrigationCanal irrigationCanal,HexPlot src,HexPlot dst,Board board) {
        primordialCanal(board);
        if(
        Boolean.TRUE.equals(this.getAllHexplotFrom().contains(src) && board.contains(dst)&& !exist(new IrrigationCanal(src,dst))
        && canBeAddToSomeNetwork(new IrrigationCanal(src,dst)) && src.isAneighbor(dst))
            ){
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
        return false;


    }
    public Optional<IrrigationCanal> initAdd(IrrigationCanal irrigationCanal,HexPlot src,HexPlot dst) {
        if(Boolean.TRUE.equals(!exist(new IrrigationCanal(src,dst)))) {
            irrigationCanal.setAvailableToTrue();
            irrigationCanal.setSourcePlot(Optional.of(src));
            irrigationCanal.setDestPlot(Optional.of(dst));
            usedCanal.add(irrigationCanal);
            return Optional.of(irrigationCanal);
        }
        Display.printMessage("Veuillez choisir un autre emplacement");
        unUsedCanal.add(irrigationCanal);
        return Optional.empty();
    }

    public Optional<IrrigationCanal> getOneUnused(){
        if(!unUsedCanal.isEmpty()){
            IrrigationCanal temp=unUsedCanal.get(0);
            unUsedCanal.remove(temp);
            return Optional.of(temp);
        }
        return Optional.empty();
    }

    public List<IrrigationCanal> getUsedCanal() {
        return usedCanal;
    }


    public List<IrrigationCanal> getUnUsedCanal() {
        return unUsedCanal;
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
                Optional<IrrigationCanal> can=getOneUnused();
                if (Boolean.TRUE.equals(!exist(new IrrigationCanal(new HexPlot(),hexPlot))&& can.isPresent())
                ){
                  initAdd(can.get(),new HexPlot(),hexPlot);
                }
            }
        });
    }
    public Boolean exist(IrrigationCanal canal){
        Boolean res=false;
        for (IrrigationCanal can : usedCanal) {
            if (can.getSourcePlot().equals(canal.getSourcePlot()) &&
                    can.getDestPlot().equals(canal.getDestPlot()) ||
                            can.getSourcePlot().equals(canal.getDestPlot()) &&
                                    can.getDestPlot().equals(canal.getSourcePlot())) {
                res=true;
            }
        }
        return res;
}
    private Boolean canBeAddToSomeNetwork(IrrigationCanal canal){
        Boolean res=false;
        for (IrrigationCanal can : usedCanal) {
            if (Boolean.TRUE.equals(can.canbeIntheSameNetwork(canal))) {
                res=true;
            }
        }
        Display.printMessage("il n'existe pas de canal valable auquel on pourrait le relier");
        return res;
    }
}
