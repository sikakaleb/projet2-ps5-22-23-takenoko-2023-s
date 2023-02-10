package fr.cotedazur.univ.polytech.startingpoint.supplies;

import fr.cotedazur.univ.polytech.startingpoint.display.Display;

public class Panda {
    private HexPlot position;

    public Panda(HexPlot position){
        this.position=position;
    }

    public HexPlot getPosition() {
        return position;
    }

    public void setPosition(HexPlot position) {
        this.position = position;
    }

    /*
        reste test à faire
     */
    public void pandaMove(HexPlot nPosition){
        if(nPosition.getQ()==this.position.getQ()){
            this.position=nPosition;
        }
        else if (nPosition.getR()==this.position.getR()) {
            this.position=nPosition;
        }
        else if(nPosition.getS()==this.position.getS()){
            this.position=nPosition;
        }
        else {
            Display.printMessage("Le panda ne se deplace qu'en ligne droite");
        }
    }


}
