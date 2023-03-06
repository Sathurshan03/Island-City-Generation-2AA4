package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import java.util.List;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;

public enum ShapeType {
    CIRCLE, RECTANGLE;

    public String toString(){
        switch(this){
        case CIRCLE :
            return "circle";
        case RECTANGLE :
            return "rectangle";
        }
        return null;
    }

    public Shape getShape(double width, double height,  List<Tile> tiles){
        switch(this){
            case CIRCLE :
                return new Circle(width, height, tiles);
            case RECTANGLE :
                return new Rectangle(width, height, tiles);
            }
            return null;
    }
}
