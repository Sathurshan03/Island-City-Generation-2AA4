package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Rectangle;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Shape;

import java.util.List;

public enum AltitudeType {
    VOLCANIC,WATER;


    public String toString(){
        switch(this){
            case VOLCANIC :
                return "volcanic";
            case WATER :
                return "water";
        }
        return null;
    }
    public Altitude getAltitude(List<Tile> tiles){
        switch(this){
            case VOLCANIC :
                return new Volcanic(tiles);
            case WATER:
                return new Water(tiles);
        }
        return null;
    }
}
