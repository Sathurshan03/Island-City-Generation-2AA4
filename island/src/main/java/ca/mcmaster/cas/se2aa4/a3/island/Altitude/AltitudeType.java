package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;

import java.util.List;

public enum AltitudeType {
    VOLCANIC, OCEAN, CLIFF,HILLS;


    public String toString(){
        switch(this){
            case VOLCANIC :
                return "volcanic";
            case OCEAN:
                return "water";
            case CLIFF:
                return "cliff";
            case HILLS:
                return "hills";
        }
        return null;
    }
    public AltitudeFunction getAltitude(List<Tile> tiles){
        switch(this){
            case VOLCANIC :
                return new Volcanic().getFunction();
            case OCEAN:
                return new Water().getFunction(tiles);
            case CLIFF:
                return new Cliff().getFunction();
            case HILLS:
                return new Hills().getFunction();
        }
        return null;
    }
}
