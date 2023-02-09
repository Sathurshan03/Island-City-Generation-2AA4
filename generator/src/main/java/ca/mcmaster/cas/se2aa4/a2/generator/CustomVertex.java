package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import java.util.*;
import java.awt.*;

public class CustomVertex {
    double x;
    double y; 
    private static final String THICKNESSVALUE = "4.0";
    Property colour;
    Vertex vertex;
    Property thickness;

    public CustomVertex(double x, double y){
        this.x = x;
        this.y = y;
        this.colour = randColor();
        this.thickness = Property.newBuilder().setKey("thickness").setValue(THICKNESSVALUE).build();
        this.vertex = Vertex.newBuilder().setX((double) x).setY((double) y).addProperties(0,colour).addProperties(1,thickness).build();
    }

    public Vertex getVertex(){
        return vertex;
    }

    public Color getColour(){
        String colourVal = colour.getValue();

        if (colourVal == null)
        {
            return Color.BLACK;
        }

        String[] raw = colourVal.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        int transparency = Integer.parseInt(raw[3]);

        return new Color(red, green, blue, transparency);
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
