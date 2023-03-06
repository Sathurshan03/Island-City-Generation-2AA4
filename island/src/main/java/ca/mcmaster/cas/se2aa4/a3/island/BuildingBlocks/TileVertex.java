package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.ExtractVertexInfo;
import java.awt.Color;
import java.util.List;

public class TileVertex extends ExtractVertexInfo{
    List<Color> colorList;
    Color averageColor = Color.black;
    String vertexType;
    Double thicknessDouble;
    public TileVertex(Vertex vertex)
    {
        super(vertex);
        this.thicknessDouble = thickness;
        this.vertexType = extractVertexType(vertex.getPropertiesList());
    }

    public Vertex getVertex(){
        String colorCode = averageColor.getRed() + "," + averageColor.getBlue() + "," + averageColor.getGreen() + "," + averageColor.getAlpha();
        Property colorProperty = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        Property thicknessProp = Property.newBuilder().setKey("thickness").setValue(thicknessDouble.toString()).build();
        Property vertexTypeProp = Property.newBuilder().setKey("vertexType").setValue(vertexType).build();
        return Vertex.newBuilder().setX(X).setY(Y).addProperties(0,colorProperty).addProperties(1,thicknessProp).addProperties(2,vertexTypeProp).build();
    }

    private String extractVertexType(List<Property> properties){
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
    
}
