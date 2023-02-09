package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;

import java.util.Objects;
import java.util.Optional;

public class IrrigationCanal {
    private int canalId;
    private Optional<HexPlot> SourcePlot;
    private Optional<HexPlot> DestPlot;

    private Boolean isAvailable;

    private static int numberOfIrrigation=0;

    public IrrigationCanal() {
        canalId=++numberOfIrrigation;
        this.DestPlot=Optional.empty();
        this.SourcePlot=Optional.empty();
        this.isAvailable=false;
    }

    public IrrigationCanal(HexPlot src, HexPlot dst) {
        canalId=-8888888;
        this.DestPlot=Optional.of(dst);
        this.SourcePlot=Optional.of(src);
        this.isAvailable=false;
    }

    public Optional<HexPlot> getSourcePlot() {
        return SourcePlot;
    }

    public void setSourcePlot(Optional<HexPlot> sourcePlot) {
        SourcePlot = sourcePlot;
    }

    public Optional<HexPlot> getDestPlot() {
        return DestPlot;
    }

    public void setDestPlot(Optional<HexPlot> destPlot) {
        if(destPlot.isPresent()){
            HexPlot dest = destPlot.get();
            dest.setIrrigatedToTrue();
            DestPlot = destPlot;
        }
        return;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailableToTrue() {
        isAvailable = true;
    }
    public void setAvailableToFalse() {
        isAvailable = false;
    }

    @Override
    public String toString() {
        return "IrrigationCanal{" +
                "canalId=" + canalId +
                ", SourcePlot=" + SourcePlot +
                ", DestPlot=" + DestPlot +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IrrigationCanal canal)) return false;
        return canalId == canal.canalId ||( Objects.equals(getSourcePlot(), canal.getSourcePlot()) && Objects.equals(getDestPlot(), canal.getDestPlot()) && Objects.equals(isAvailable, canal.isAvailable));
    }

    public int getCanalId() {
        return canalId;
    }

    public void setCanalId(int canalId) {
        this.canalId = canalId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(canalId);
    }

    public Boolean canbeIntheSameNetwork(IrrigationCanal dest){
        HexPlot    thisdst= getDestPlot().orElse(null);
        HexPlot    thissrc= getSourcePlot().orElse(null);
        HexPlot   thatdst= dest.getDestPlot().orElse(null);
        HexPlot   thatsrc= dest.getSourcePlot().orElse(null);

        Boolean result = false;;
        if (thatdst != null && thisdst != null && thatsrc != null && thissrc != null) {
            if (thissrc.equals(thatsrc) && thisdst.isAneighbor(thatdst)) {
                result = true;
            } else if (thisdst.equals(thatsrc) && thissrc.isAneighbor(thatdst)) {
                result = true;
            } else {
                result = false;
            }
        }
         if(result){
                Display.printMessage(" on peut relier le canal"+thissrc+" <-------> "+thisdst +" a : "
                        +thatsrc+" <-------> "+thatdst);
         }
            return result;
    }
}
