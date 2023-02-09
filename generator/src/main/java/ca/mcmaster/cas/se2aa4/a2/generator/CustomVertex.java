package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import java.util.*;

public class CustomVertex {
    double x;
    double y; 
    Property colour;
    Vertex vertex;

    public CustomVertex(double x, double y){
        this.x = x;
        this.y = y;
        this.colour = randColor();
        this.vertex = Vertex.newBuilder().setX((double) x).setY((double) y).addProperties(colour).build();
    }

    public Vertex getVertex(){
        return vertex;
    }

    public Property randColor(){
        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        int transparency = bag.nextInt(155) + 100;
        String colorCode = red + "," + green + "," + blue + "," + transparency;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

        return color;
    }
    
}
