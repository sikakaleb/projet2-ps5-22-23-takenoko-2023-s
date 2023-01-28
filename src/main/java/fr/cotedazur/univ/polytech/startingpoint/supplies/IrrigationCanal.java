package fr.cotedazur.univ.polytech.startingpoint.supplies;

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
        canalId=8888888;
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
        HexPlot dest = destPlot.get();
        dest.setIrrigatedToTrue();
        DestPlot = destPlot;
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
            HexPlot thisdst= getDestPlot().get();
            HexPlot thissrc= getSourcePlot().get();
            HexPlot thatdst= dest.getDestPlot().get();
            HexPlot thatsrc= dest.getSourcePlot().get();
        System.out.println(  thisdst.equals(thatsrc) +" "+ thissrc.isAneighbor(thatdst));
        System.out.println(thisdst);
        System.out.println(thatdst);

            return thissrc.equals(thatsrc) && thisdst.isAneighbor(thatdst)
                    ||
                    thisdst.equals(thatsrc) && thissrc.isAneighbor(thatdst);
    }
}
