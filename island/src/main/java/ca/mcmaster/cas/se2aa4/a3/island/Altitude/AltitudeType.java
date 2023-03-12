package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;

import java.util.List;

public enum AltitudeType {
    VOLCANIC, OCEAN, CLIFF;


    public String toString(){
        switch(this){
            case VOLCANIC :
                return "volcanic";
            case OCEAN:
                return "water";
            case CLIFF:
                return "cliff";
        }
        return null;
    }
    public AltitudeFunction getAltitude(List<Tile> tiles){
        switch(this){
            case VOLCANIC :
                return new Volcanic().getFunction();
            case OCEAN:
                return new Ocean().getFunction();
            case CLIFF:
                return new Cliff().getFunction();
        }
        return null;
    }
}
