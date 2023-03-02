package ca.mcmaster.cas.se2aa4.a2.visualizer;

import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

public class ExtractSegmentInfo {
    private int vertex1ID;
    private int vertex2ID;
    private double thickness;

    public ExtractSegmentInfo (Segment segment){
        this.vertex1ID = segment.getV1Idx();
        this.vertex2ID = segment.getV2Idx();
        this.thickness = extractThickness(segment.getPropertiesList());
    }

    public int getVertedIDX1(){
        return vertex1ID;
    }

    public int getVertedIDX2(){
        return vertex2ID;
    }

    public double getThickness(){
        return thickness;
    }

    private double extractThickness(List<Property> properties) {
        //Get the "thickness" of the vertex (size)
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("thickness")) {
                val = p.getValue();
            }
        }
        if (val == null)
            return 0;
        return Double.parseDouble(val);
    }
}
