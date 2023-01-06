package fr.cotedazur.univ.polytech.startingpoint;
import supplies.HexPlot;
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
        //à gerer avec une execption
        else {
            System.out.println("Le panda ne se déplace qu'en ligne droite");
        }
    }

}
