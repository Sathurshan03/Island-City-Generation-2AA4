package ca.mcmaster.cas.se2aa4.a2.generator;

import java.awt.*;
import java.util.*;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int squareSize = 20;

    public Mesh generate() {

        RegularMesh mesh = new RegularMesh(width, height, 2, squareSize);
        
        List <Segment> segmentsy=new ArrayList<>();
        List<Segment> segments=new ArrayList<>();
        

        //Horizontal segments
        for(int x = 0; x*squareSize < width ; x++) {
            for (int y = 0; y*squareSize <= height; y++) {
                
                Property c1 = segColor(mesh.getConnectingVertices(x,y), mesh.getConnectingVertices(x+1,y));
                segments.add(Segment.newBuilder().setV1Idx(x*squareSize).setV2Idx(x*squareSize + squareSize).addProperties(c1).build());
            }

        }
        for(int y = 0; y*squareSize < height; y++) {
            for (int x = 0; x*squareSize <= width; x++) {
                Property c2 = segColor(mesh.getConnectingVertices(x,y), mesh.getConnectingVertices(x,y+1));
                segmentsy.add(Segment.newBuilder().setV1Idx(y*squareSize).setV2Idx(y*squareSize + squareSize).addProperties(c2).build());
            }

        }

        segments.addAll(segmentsy);






        return Mesh.newBuilder().addAllSegments(segments).addAllVertices(mesh.getVertices()).build();
    }

    public Property segColor(CustomVertex v1, CustomVertex v2){
        Color c1= v1.getColour();
        Color c2= v2.getColour();
        int red=(c1.getRed()+c2.getRed())/2;
        int blue=(c1.getBlue()+c2.getBlue())/2;
        int green=(c1.getGreen()+c2.getGreen())/2;

        String colorCode = red + "," + green + "," + blue;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

        return color;

    }
}
