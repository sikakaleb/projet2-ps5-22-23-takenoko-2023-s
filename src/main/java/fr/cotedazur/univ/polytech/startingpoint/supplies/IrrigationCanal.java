package fr.cotedazur.univ.polytech.startingpoint.supplies;

import java.util.Objects;
import java.util.Optional;

public class IrrigationCanal {
    private int canalId;
    private Optional<HexPlot> SourcePlot;
    private Optional<HexPlot> DestPlot;

    private Boolean isAvailable;

    public IrrigationCanal() {
        this.DestPlot=Optional.empty();
        this.SourcePlot=Optional.empty();
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
                "SourcePlot=" + SourcePlot +
                ", DestPlot=" + DestPlot +
                ", isAvailable=" + isAvailable +
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
}
