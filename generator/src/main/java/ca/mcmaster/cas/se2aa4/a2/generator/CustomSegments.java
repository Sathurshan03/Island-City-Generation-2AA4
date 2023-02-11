package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.*;

public class CustomSegments {

    private static final String THICKNESS = "3";
    int v1;
    int v2;
    Property colourProp;
    Property thicknessProp;
    Segment segment;

    public CustomSegments(int v1, int v2, Color colour, String thickness) {
        this.v1 = v1;
        this.v2 = v2;
        this.colourProp = setColour(colour);
        this.thicknessProp = Property.newBuilder().setKey("thickness").setValue(thickness).build();
        this.segment = Segment.newBuilder().setV1Idx(v1).setV2Idx(v2).addProperties(0,colourProp)
                .addProperties(1,thicknessProp).build();
    }

    public Segment getSegment(){
        return segment;
    }

    public Color getColour(){
        String colourVal = colourProp.getValue();

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

    public Property setColour(Color colour){
        String colourCode = colour.getRed() + "," + colour.getBlue() + "," + colour.getGreen() + "," + colour.getAlpha();
        return Property.newBuilder().setKey("rgb_color").setValue(colourCode).build();
    }

    public void setThickness(Property thickness) {
        this.thicknessProp = thickness;
    }

    public String getThickness(){
        return thicknessProp.getValue();
    }
    }
