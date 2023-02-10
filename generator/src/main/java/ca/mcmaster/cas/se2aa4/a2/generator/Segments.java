package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.List;

public class Segments {

    private static final int THICKNESS = 3;
    int v1;
    int v2;
    Property colour;
    Property thickness;
    Segment segment;

    public Segments(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
        this.thickness = Property.newBuilder().setKey("thickness").setValue(Integer.toString(THICKNESS)).build();
        this.segment = Segment.newBuilder().setV1Idx(v1).setV2Idx(v2).addProperties(0,colour)
                .addProperties(1,thickness).build();
    }

    public Segment getSegment(){
        return segment;
    }

    public void setColour(Property colour){ // change back to accepting Color colour later, find out how nirmal wants to assign colour
                                         // to colour attribute
//        String colourCode = colour.getRed() + "," + colour.getGreen() + "," + colour.getBlue() + "," +
//                colour.getAlpha();
//        this.colour = Property.newBuilder().setKey("rgb_color").setValue(colourCode).build();
        this.colour = colour;
    }


    public Property segColor(Vertex v1, Vertex v2) { // Do I need this method here if segment only accepts list index
                                                     // values for a vertex, need to send the actual vertex to generate a colour once object is instantiated?
        Color c1 = v1.getColour();
        Color c2 = v2.getColour();
//        Color c1 = extractColor(v1.getPropertiesList());
//        Color c2 = extractColor(v2.getPropertiesList());
        int red = (c1.getRed() + c2.getRed()) / 2;
        int blue = (c1.getBlue() + c2.getBlue()) / 2;
        int green = (c1.getGreen() + c2.getGreen()) / 2;
        int transparency = (c1.getAlpha() + c2.getAlpha()) / 2;
        String colorCode = red + "," + green + "," + blue + "," + transparency;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

        return color; // if we keep this method, can switch this to this.colour = color

    }
//    private Color extractColor(List<Property> properties) {
//        String val = null;
//        for (Property p : properties) {
//            if (p.getKey().equals("rgb_color")) {
//                System.out.println(p.getValue());
//                val = p.getValue();
//            }
//        }
//        if (val == null)
//            return Color.BLACK;
//        String[] raw = val.split(",");
//        int red = Integer.parseInt(raw[0]);
//        int green = Integer.parseInt(raw[1]);
//        int blue = Integer.parseInt(raw[2]);
//        return new Color(red, green, blue);
//    }

}
