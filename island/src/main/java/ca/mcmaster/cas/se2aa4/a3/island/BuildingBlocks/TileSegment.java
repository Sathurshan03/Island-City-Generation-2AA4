package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.ExtractSegmentInfo;
import java.awt.Color;
import java.util.List;

public class TileSegment extends ExtractSegmentInfo{
    List<Color> colorList;
    public TileSegment(Segment segment, List<Vertex> vertices, int offset){
        super(segment, vertices, offset);
    }
    
}
