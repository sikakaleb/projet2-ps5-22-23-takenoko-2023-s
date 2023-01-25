package fr.cotedazur.univ.polytech.startingpoint.supplies;

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
}
