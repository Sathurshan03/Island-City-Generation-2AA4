package ca.mcmaster.cas.se2aa4.a2.generator;

import java.awt.*;
import java.util.*;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {

        RegularMesh mesh = new RegularMesh(width, height, 2, square_size);

        CustomVertex[][] vertice=new CustomVertex[width][height];
        CustomVertex[][] centroidVertice=new CustomVertex[width/20][height/ 20];

        List <Segment> segmentsy=new ArrayList<>();
        List<Segment> segments=new ArrayList<>();
        
        // Create all the vertices
        //Centroid vertices
        for(int x = 0 ; x*square_size + square_size/2 < width; x++) {
            for(int y = 0 ; y*square_size + square_size/2 < height; y ++) {
                double xPos = x*square_size + square_size/2;
                double yPos = y*square_size + square_size/2;
                CustomVertex new_v= new CustomVertex(xPos, yPos ,new Color(254,0,0,254), "2.0");
                centroidVertice[x][y]=new_v;
                mesh.addVertex(new_v.getVertex());
            }
        }

        //Connecting vertices
        for(int x = 0; x <= width; x += square_size) {
            for(int y = 0; y <= height; y += square_size) {
                CustomVertex new_v= new CustomVertex(x,y);
                vertice[x/20][y/20]=new_v;
                mesh.addVertex(new_v.getVertex());
            }
        }

        for(int x = 0; x <= width; x += square_size) {
            for (int y = 0; y <= height; y += square_size) {
                if (x<width-20){
                    Property c1 = segColor(vertice[x/20][y/20], vertice[(x + 20)/20][y/20]);
                    segments.add(Segment.newBuilder().setV1Idx(x).setV2Idx(x + square_size).addProperties(c1).build());
                }

            }
        }
        for(int y = 0; y < height; y += square_size) {
            for (int x = 0; x < width; x += square_size) {
                if (y<height-20){
                    Property c2 = segColor(vertice[x/20][y/20], vertice[x/20][(y + 20)/20]);
                    segmentsy.add(Segment.newBuilder().setV1Idx(y).setV2Idx(y + square_size).addProperties(c2).build());
                }
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
