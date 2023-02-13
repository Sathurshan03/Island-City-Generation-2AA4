package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import java.util.*;
import java.awt.*;
import org.decimal4j.util.DoubleRounder;

public class CustomVertex{
    double x;
    double y; 
    private static final String THICKNESSVALUE = "4.0";
    Property colourProperty;
    Vertex vertex;
    Property thickness;

    public CustomVertex(double x, double y, int precision){
        this.x = DoubleRounder.round(x, precision);
        this.y = DoubleRounder.round(y, precision);
        this.colourProperty = randColor();
        this.thickness = Property.newBuilder().setKey("thickness").setValue(THICKNESSVALUE).build();
        this.vertex = Vertex.newBuilder().setX((double) x).setY((double) y).addProperties(0,colourProperty).addProperties(1,thickness).build();
    }

    public CustomVertex(double x, double y, Color colour, String thicknessValue, int precision){
        this.x = DoubleRounder.round(x, precision);
        this.y = DoubleRounder.round(y, precision);
        this.colourProperty = setColour(colour);
        this.thickness = Property.newBuilder().setKey("thickness").setValue(thicknessValue).build();
        this.vertex = Vertex.newBuilder().setX((double) x).setY((double) y).addProperties(0,this.colourProperty).addProperties(1,thickness).build();
    }

    public Vertex getVertex(){
        return vertex;
    }

    public Color getColour(){
        String colourVal = colourProperty.getValue();

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

    protected Property randColor(){
        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        int transparency = bag.nextInt(155) + 100;
        String colorCode = red + "," + green + "," + blue + "," + transparency;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        return color;    }


    private Property setColour(Color colour){
        String colorCode = colour.getRed() + "," + colour.getGreen() + "," + colour.getBlue() + "," + colour.getAlpha();
        Property colorProp = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

        return colorProp;
    }







}
