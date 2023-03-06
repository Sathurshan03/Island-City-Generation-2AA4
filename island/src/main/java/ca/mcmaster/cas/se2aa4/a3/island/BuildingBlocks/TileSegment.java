package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.ExtractSegmentInfo;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class TileSegment extends ExtractSegmentInfo implements TileProperties{
    List<Color> colorList;
    Color averageColor = new Color(0, 0, 0, 0);
    String segmentType;
    Double thicknessDouble;
    public TileSegment(Segment segment, List<Vertex> vertices, int offset){
        super(segment, vertices, offset);
        this.thicknessDouble = thickness;
        this.colorList = new ArrayList<>();
        segmentType = extractSegmentType(segment.getPropertiesList());
    }

    public void setColor(Color color){
        this.averageColor = color;
    }

    public Segment getSegment(){
        setAverageColor();
        String colourCode = averageColor.getRed() + "," + averageColor.getGreen() + "," + averageColor.getBlue() + "," + averageColor.getAlpha();
        Property colorProp = Property.newBuilder().setKey("rgb_color").setValue(colourCode).build();
        Property thicknessProp = Property.newBuilder().setKey("thickness").setValue(thicknessDouble.toString()).build();
        Property segmentTypeProp = Property.newBuilder().setKey("segmentType").setValue(segmentType).build();
        return Segment.newBuilder().setV1Idx(vertex1ID).setV2Idx(vertex2ID).addProperties(0, colorProp).addProperties(1, thicknessProp).addProperties(2, segmentTypeProp).build();
    }

    private String extractSegmentType(List<Property> properties){
        //Get the "thickness" of the vertex (size)
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("segmentType")) {
                val =  p.getValue();
                break;
            }
        }
        return val;
    }

    public void setAverageColor(){
        //set the average color for the object
        if (colorList.size()!=0 && !averageColor.equals(new Color(0, 0, 0, 0))){
            int red = 0;
            int green = 0;
            int blue = 0;
            int alpha = 0;

            for (Color color: colorList){
                red += color.getRed();
                green += color.getGreen();
                blue += color.getBlue();
                alpha += color.getAlpha();
            }
            
            red /= colorList.size();
            green /= colorList.size();
            blue /= colorList.size();
            alpha /= colorList.size();
            averageColor = new Color(red, green, blue, alpha);
        }
    }   
}
