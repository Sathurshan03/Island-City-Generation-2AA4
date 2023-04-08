package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.Elements.VertexElement;
import ca.mcmaster.cas.se2aa4.a3.tools.ExtractVertexInfo;
import graphadt.GraphComponents.Node;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class TileVertex extends ExtractVertexInfo implements TileProperties{
    List<Color> colorList;
    Color averageColor = new Color(255, 255, 255, 0);
    String vertexType;
    Double temperature;
    VertexElement vertexElement;
    Double elevation;
    Double thicknessDouble;
    IslandNode islandNode;


    public TileVertex(Vertex vertex)
    {
        super(vertex);
        this.elevation=0.0;
        this.temperature=1.0;
        this.thicknessDouble = thickness;
        this.colorList = new ArrayList<>();
        this.vertexElement = VertexElement.LAND;
        this.vertexType = extractVertexType(vertex.getPropertiesList());
        this.islandNode = null;
    }

    public void setColor(Color color){
        this.averageColor = color;
    }

    public void setElevation(Double elevation){
        this.elevation=elevation;
    }

    public void setTemperature(Double temperature){
        this.temperature=temperature;
    }

    public Double getTemperature(){
        return this.temperature;
    }

    public Double getElevation(){
        return this.elevation;
    }

    public void setThickness(Double new_thickness){
        this.thicknessDouble=new_thickness;
    }

    public void setRiver(){
        vertexElement = VertexElement.RIVER;
    }

    public void setThickness(double thickness){
        this.thicknessDouble = thickness;
    }

    public void setVertexWater(){
        vertexElement = VertexElement.WATER;
    }

    public void setVertexCity(){
        vertexElement = VertexElement.CITY;
        thicknessDouble = thicknessDouble * IslandCommandLineReader.randomGenerator.getNextDouble(1,3.5);
    }

    public void setCentralVertexCity(){
        vertexElement = VertexElement.CENTRALCITY;
    }

    public void associateIslandNode(IslandNode node){
        islandNode = node;
    }

    public IslandNode getIslandNode(){
        return islandNode;
    }

    public Node getNodeRepresentation(){
        return islandNode.getNode();
    }

    public void setVertexRoad(){
        if (!vertexElement.equals(VertexElement.CITY) && !vertexElement.equals(VertexElement.CENTRALCITY)){
            vertexElement = VertexElement.ROAD;
        }
    }

    public void setVertexDirtRoad(){
        if (!vertexElement.equals(VertexElement.CITY) && !vertexElement.equals(VertexElement.CENTRALCITY) && !vertexElement.equals(VertexElement.ROAD)){
            vertexElement = VertexElement.DIRTROAD;
        }
    }

    public Boolean isVertexWater(){
        if (vertexElement.equals(VertexElement.WATER)){
            return true;
        }
        return false;
    }

    public Boolean isVertexLand(){
        if (vertexElement.equals(VertexElement.LAND)){
            return true;
        }
        return false;
    }

    public Vertex getVertex(){
        if (!vertexElement.equals(VertexElement.LAND)){
            averageColor = vertexElement.getColor();
        }
        else{
            setAverageColor();
        }
        
        String colorCode = averageColor.getRed() + "," + averageColor.getGreen() + "," + averageColor.getBlue() + "," + averageColor.getAlpha();
        Property colorProperty = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        Property thicknessProp = Property.newBuilder().setKey("thickness").setValue(thicknessDouble.toString()).build();
        Property vertexTypeProp = Property.newBuilder().setKey("vertexType").setValue(vertexType).build();
        Property elevationTypeProp = Property.newBuilder().setKey("vertexType").setValue(elevation.toString()).build();
        return Vertex.newBuilder().setX(X).setY(Y).addProperties(0,colorProperty).addProperties(1,thicknessProp).addProperties(2,vertexTypeProp).addProperties(3,elevationTypeProp).build();
    }


    public String extractVertexType(List<Property> properties){
        //Get the "thickness" of the vertex (size)
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("vertexType")) {
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
