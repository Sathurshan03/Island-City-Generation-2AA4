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


        Vertex[][] vertice=new Vertex[width][height];
        List<Vertex> vertices=new ArrayList<>();

        List <Segment> segmentsy=new ArrayList<>();
        List<Segment> segments=new ArrayList<>();
        // Create all the vertices

        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                Vertex new_v=Vertex.newBuilder().setX((double) x).setY((double) y).addProperties(randColor()).build();
                vertice[x/20][y/20]=new_v;
                vertices.add(new_v);

            }
        }

        for(int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                if (x<width-20){
                    Property c1 = segColor(vertice[x/20][y/20], vertice[(x + 20)/20][y/20]);
                    CustomSegments new_segment = new CustomSegments(x,x+square_size,c1,"3");
                    segments.add(new_segment.getSegment());
//                    segments.add(Segment.newBuilder().setV1Idx(x).setV2Idx(x + square_size).addProperties(c1).build());
                }

            }
        }
        for(int y = 0; y < height; y += square_size) {
            for (int x = 0; x < width; x += square_size) {
                if (y<height-20){
                    Property c2 = segColor(vertice[x/20][y/20], vertice[x/20][(y + 20)/20]);
                    CustomSegments new_segment = new CustomSegments(y,y+square_size,c2,"3");
                    segmentsy.add(new_segment.getSegment());
//                    segmentsy.add(Segment.newBuilder().setV1Idx(y).setV2Idx(y + square_size).addProperties(c2).build());
                }
            }

        }

        segments.addAll(segmentsy);




        // Distribute colors randomly. Vertices are immutable, need to enrich them

        return Mesh.newBuilder().addAllSegments(segments).addAllVertices(vertices).build();
    }

    public Property segColor(Vertex v1, Vertex v2){
        Color c1=extractColor(v1.getPropertiesList());
        Color c2=extractColor(v2.getPropertiesList());
        int red=(c1.getRed()+c2.getRed())/2;
        int blue=(c1.getBlue()+c2.getBlue())/2;
        int green=(c1.getGreen()+c2.getGreen())/2;
        String colorCode = red + "," + green + "," + blue;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

        return color;

    }
    public Property randColor(){
        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        String colorCode = red + "," + green + "," + blue;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

        return color;
    }

    public Color extractColor(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        if (val == null)
            return Color.BLACK;
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new Color(red, green, blue);
    }


}
