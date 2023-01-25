package fr.cotedazur.univ.polytech.startingpoint.supplies;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.IntStream;

import static fr.cotedazur.univ.polytech.startingpoint.Game.board;
import static fr.cotedazur.univ.polytech.startingpoint.tools.Color.*;

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
        IntStream.range(0, 20).forEach(i -> {
            unUsedCanal.add(new IrrigationCanal());
        });
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

    public boolean add(IrrigationCanal irrigationCanal,HexPlot src,HexPlot dst) {
        if(this.getAllHexplotFrom().contains(src) && board.contains(dst)){
            irrigationCanal.setAvailableToTrue();
        }else{
            throw new RuntimeException("La parcelle source n'est pas valable");
        }
        irrigationCanal.setSourcePlot(Optional.of(src));
        irrigationCanal.setDestPlot(Optional.of(dst));
        usedCanal.add(irrigationCanal);
        unUsedCanal.remove(irrigationCanal);
        return true;
    }

    public Optional<IrrigationCanal> getUnUsed(){
        if(unUsedCanal.size()!=0){
            return Optional.of(unUsedCanal.get(0));
        }
        return Optional.empty();
    }
}
