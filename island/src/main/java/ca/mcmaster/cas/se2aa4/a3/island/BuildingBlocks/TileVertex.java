package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.tools.ExtractVertexInfo;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class TileVertex extends ExtractVertexInfo implements TileProperties{
    List<Color> colorList;
    Color averageColor = new Color(0, 0, 0, 0);
    String vertexType;

    Double elevation;
    Double thicknessDouble;
    public TileVertex(Vertex vertex)
    {
        super(vertex);
        this.thicknessDouble = thickness;
        this.colorList = new ArrayList<>();
        this.vertexType = extractVertexType(vertex.getPropertiesList());
    }

    public void setColor(Color color){
        this.averageColor = color;
    }

    public void setElevation(Double elevation){
        this.elevation=elevation;
    }

    public Double getElevation(){
        return this.elevation;
    }

    public void setThickness(Double new_thickness){
        this.thicknessDouble=new_thickness;
    }



    public Vertex getVertex(){
        setAverageColor();
        String colorCode = averageColor.getRed() + "," + averageColor.getGreen() + "," + averageColor.getBlue() + "," + averageColor.getAlpha();
        Property colorProperty = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        Property thicknessProp = Property.newBuilder().setKey("thickness").setValue(thicknessDouble.toString()).build();
        Property vertexTypeProp = Property.newBuilder().setKey("vertexType").setValue(vertexType).build();
        return Vertex.newBuilder().setX(X).setY(Y).addProperties(0,colorProperty).addProperties(1,thicknessProp).addProperties(2,vertexTypeProp).build();
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
