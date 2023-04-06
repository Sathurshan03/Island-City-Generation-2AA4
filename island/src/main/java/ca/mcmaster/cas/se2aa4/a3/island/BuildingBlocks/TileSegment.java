package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.Elements.SegmentElement;
import ca.mcmaster.cas.se2aa4.a3.island.Elements.VertexElement;
import ca.mcmaster.cas.se2aa4.a3.tools.ExtractSegmentInfo;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class TileSegment extends ExtractSegmentInfo implements TileProperties{
    private List<Color> colorList;
    private String segmentType;
    private Double thicknessDouble;
    private TileVertex tileVertex1;
    private TileVertex tileVertex2;
    private int numRivers;
    private SegmentElement segmentElement;
    private Color averageColor;

    public TileSegment(Segment segment, List<Vertex> vertices, int offset){
        super(segment, vertices, offset);
        this.thicknessDouble = super.thickness;
        this.colorList = new ArrayList<>();
        this.numRivers = 0;
        this.segmentElement = SegmentElement.REGULAR;
        this.averageColor = segmentElement.getColor();
        segmentType = extractSegmentType(segment.getPropertiesList());
    }

    public TileVertex getTileVertex1(){
        return this.tileVertex1;
    }

    public TileVertex getTileVertex2(){
        return this.tileVertex2;
    }


    public void setTileVertex1(TileVertex vertex){
        this.tileVertex1 = vertex;
    }

    public void setTileVertex2(TileVertex vertex){
        this.tileVertex2 = vertex;
    }

    public void setRiver(){
        segmentElement = SegmentElement.River;
        numRivers++;
        updateThickness();
    }

    public void setSegmentRoad(){
        segmentElement = SegmentElement.ROAD;
        thicknessDouble = super.thickness * 1.5;
        setVertexThicknessSimilar();
    }

    private void updateThickness(){
        thicknessDouble = 0.75 * numRivers;
    }

    public void setSegmentVertexRiver(){
        setVertexThicknessSimilar();
        tileVertex1.setRiver();
        tileVertex2.setRiver();
    }

    public void setVertexThicknessSimilar(){
        //sets the vertex size to be same as the segment thickeness
        if (!tileVertex1.vertexElement.equals(VertexElement.CITY) && !tileVertex1.vertexElement.equals(VertexElement.CENTRALCITY)){
            tileVertex1.setThickness(thicknessDouble*Math.sqrt(2));
        }
        
        if (!tileVertex2.vertexElement.equals(VertexElement.CITY) && !tileVertex2.vertexElement.equals(VertexElement.CENTRALCITY)){
            tileVertex2.setThickness(thicknessDouble*Math.sqrt(2));
        } 
    }

    public double getThickness(){
        return thicknessDouble; 
    }

    public Boolean containsTileVertex(TileVertex vertex){
        //returns true if the segment connects to the inputted vertex
        if (tileVertex1.equals(vertex) || tileVertex2.equals(vertex)){
            return true;
        }
        return false;
    }

    public TileVertex getAdjacentVertex(TileVertex vertex){
        //returns the adjacent vertex of the segment for the inputted vertex
        if (tileVertex1.equals(vertex))
        {
            return tileVertex2;
        }
        else if (tileVertex2.equals(vertex)){
            return tileVertex1;
        }
        return null;
    }

    public Segment getSegment(){
        if (!segmentElement.equals(SegmentElement.REGULAR)){
            averageColor = segmentElement.getColor();
        }
        else{
            setAverageColor();
        }
        
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

    public void setColor(Color color) {
        averageColor = color;
    }   
}
