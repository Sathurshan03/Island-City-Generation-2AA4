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

    static List <Segment> all_segments=new ArrayList<Segment>();
    static List <Vertex> all_vertex=new ArrayList<Vertex>();

    public Mesh generate() {

        List<Vertex> centroids=new ArrayList<Vertex>();

        int digit=20;


        for (int x=1; x<25; x++){
            for (int y=1; y<25; y++){
                Vertex new_v=Vertex.newBuilder().setX((double) x*digit).setY((double) y*digit).build();
                centroids.add(new_v);
                all_vertex.add(new_v);
            }
        }

        for (Vertex v:centroids){
            Polygon curr_polygon=new Polygon();
        }




        // Distribute colors randomly. Vertices are immutable, need to enrich them

        return Mesh.newBuilder().addAllVertices(all_vertex).addAllSegments(all_segments).build();
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
