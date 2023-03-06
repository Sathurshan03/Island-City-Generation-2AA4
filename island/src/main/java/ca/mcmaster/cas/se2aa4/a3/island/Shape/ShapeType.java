package ca.mcmaster.cas.se2aa4.a3.island.Shape;

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
}
