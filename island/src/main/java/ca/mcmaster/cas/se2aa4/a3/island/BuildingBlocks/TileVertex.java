package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.ExtractVertexInfo;
import java.awt.Color;
import java.util.List;

public class TileVertex extends ExtractVertexInfo{
    List<Color> colorList;
    public TileVertex(Vertex vertex)
    {
        super(vertex);
    }
    
}
