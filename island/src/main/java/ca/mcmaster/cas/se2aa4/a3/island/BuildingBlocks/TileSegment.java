package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.ExtractSegmentInfo;
import java.awt.Color;
import java.util.List;

public class TileSegment extends ExtractSegmentInfo{
    List<Color> colorList;
    Color averageColor = Color.black;
    String segmentType;
    Double thicknessDouble;
    public TileSegment(Segment segment, List<Vertex> vertices, int offset){
        super(segment, vertices, offset);
        this.thicknessDouble = thickness;
        segmentType = extractSegmentType(segment.getPropertiesList());
    }

    public Segment getSegment(){
        String colourCode = averageColor.getRed() + "," + averageColor.getBlue() + "," + averageColor.getGreen() + "," + averageColor.getAlpha();
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
    
}
