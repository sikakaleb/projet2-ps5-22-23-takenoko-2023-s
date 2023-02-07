package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;
import fr.cotedazur.univ.polytech.startingpoint.supplies.HexPlot;

public class Gardener {
    private HexPlot position;

    Gardener(HexPlot position){
        this.position=position;
    }

    public HexPlot getPosition() {
        return position;
    }

    public void setPosition(HexPlot position) {
        this.position = position;
    }

    public void move(HexPlot plot){
        if ( plot.getQ() == this.position.getQ()
            || plot.getR()==this.position.getR()
                || plot.getS()==this.position.getS()
        ) {
            this.position = plot;
        }

        else {
            Display.printMessage("Le jardinier ne se d\u00E9place qu'en ligne droite");
        }
    }


}
