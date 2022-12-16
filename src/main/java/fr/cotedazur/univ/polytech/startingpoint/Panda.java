package fr.cotedazur.univ.polytech.startingpoint;

public class Panda {
    private HexPlot position;

    Panda(HexPlot position){
        this.position=position;
    }

    public HexPlot getPosition() {
        return position;
    }

    public void setPosition(HexPlot position) {
        this.position = position;
    }

    public void pandaMove(HexPlot nPosition){
        if(nPosition.getQ()==this.position.getQ()){
            this.position=nPosition;
        }
        else if (nPosition.getQ()==this.position.getQ()) {
            this.position=nPosition;
        }
        else if(nPosition.getQ()==this.position.getQ()){
            this.position=nPosition;
        }


    }
}
