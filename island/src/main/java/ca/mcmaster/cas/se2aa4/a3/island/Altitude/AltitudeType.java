package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;

import java.util.List;

public enum AltitudeType {
    VOLCANIC, OCEAN;


    public String toString(){
        switch(this){
            case VOLCANIC :
                return "volcanic";
            case OCEAN:
                return "water";
        }
        return null;
    }
    public Altitude getAltitude(List<Tile> tiles){
        switch(this){
            case VOLCANIC :
                return new Volcanic(tiles);
            case OCEAN:
                return new Ocean(tiles);
        }
        return null;
    }
}
