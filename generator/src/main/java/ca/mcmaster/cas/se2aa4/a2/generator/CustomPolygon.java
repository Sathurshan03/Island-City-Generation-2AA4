package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;


import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CustomPolygon extends MeshADT{

    protected List<CustomVertex> poly_vertices;
    protected List<Segment> poly_segment;


    protected List<Integer> segment_index;

    protected Vertex centroid;


    protected Polygon polygon;

    public CustomPolygon(Vertex centroid){
        this.centroid=centroid;
        this.poly_vertices=makeVertices();
        this.poly_segment=makeSegments(poly_vertices.get(0), poly_vertices.get(1), poly_vertices.get(2), poly_vertices.get(3));
        this.polygon=makePolygon();
    }

    protected List<CustomVertex> makeVertices(){


        CustomVertex v1=makeVertex(centroid.getX()-10, centroid.getY()-10);
        CustomVertex v2=makeVertex(centroid.getX()+10, centroid.getY()-10);
        CustomVertex v3=makeVertex(centroid.getX()+10, centroid.getY()+10);
        CustomVertex v4=makeVertex(centroid.getX()-10, centroid.getY()+10);


        return Arrays.asList(v1,v2,v3,v4);
    }

    protected List<Segment> makeSegments(CustomVertex v1, CustomVertex v2, CustomVertex v3, CustomVertex v4){
        Segment s1=makeSegment(vertices.indexOf(v1),vertices.indexOf(v2));
        Segment s2=makeSegment(vertices.indexOf(v2),vertices.indexOf(v3));
        Segment s3=makeSegment(vertices.indexOf(v3),vertices.indexOf(v4));
        Segment s4=makeSegment(vertices.indexOf(v4),vertices.indexOf(v1));

        this.segment_index=Arrays.asList(segments.indexOf(s1), segments.indexOf(s2), segments.indexOf(s3), segments.indexOf(s4));

        return Arrays.asList(s1,s2,s3,s4);

    }



    protected Polygon makePolygon(){
        return Polygon.newBuilder().addAllSegmentIdxs(this.segment_index).build();
    }


    private CustomVertex makeVertex(double x, double y){
        CustomVertex v=new CustomVertex(x,y);

        for (CustomVertex c: vertices){
            if (c.x==v.x & c.y==v.y){
                return c;
            }
        }

        vertices.add(v);
        return v;
    }

    private Segment makeSegment(int v1, int v2){
        Segment s=Segment.newBuilder().setV1Idx(v1).setV2Idx(v2).addProperties(calcColor(vertices.get(v1),vertices.get(v2) )).build();
        for (Segment c: segments){
            if ((c.getV1Idx()==s.getV1Idx() & c.getV2Idx()==s.getV2Idx()) | (c.getV2Idx()==s.getV1Idx() & c.getV1Idx()==s.getV2Idx()) ){
                return c;
            }
        }
        segments.add(s);
        return s;
    }

    protected Property calcColor(CustomVertex v1, CustomVertex v2){
        Color c1=v1.getColour();
        Color c2=v2.getColour();
        int red=(c1.getRed()+c2.getRed())/2;
        int blue=(c1.getBlue()+c2.getBlue())/2;
        int green=(c1.getGreen()+c2.getGreen())/2;
        String colorCode = red + "," + green + "," + blue;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

        return color;
    }

}

