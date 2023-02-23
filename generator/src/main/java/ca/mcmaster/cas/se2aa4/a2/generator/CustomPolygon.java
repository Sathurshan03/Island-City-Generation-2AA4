package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomPolygon extends MeshADT{

    protected List<CustomVertex> poly_vertices;

    protected List<CustomSegments> poly_segment;
    protected List<Integer> indexNeighbourCentroids;
    protected int precision;


    protected List<Integer> segment_index;

    protected List<Integer> neighbours;

    protected CustomVertex centroid;
    protected Integer centroid_idx;


    protected Polygon polygon;

    public CustomPolygon(int centroid, int precision){
        this.segment_index=new ArrayList<>();
        this.neighbours=new ArrayList<>();
        this.poly_segment=new ArrayList<>();
        this.centroid=centroids.get(centroid);
        this.centroid_idx=centroid;
        this.precision = precision;
        this.poly_vertices=makeVertices();
        makeSegments();
        this.polygon=makePolygon();
    }

    public CustomPolygon(List<CustomVertex> cusVertices, CustomVertex centroid, int newCentroidIndex, List<Integer> indexNeighbourCentroids){
        //initializes all of the required lists.
        this.segment_index=new ArrayList<>();
        this.neighbours=new ArrayList<>();
        this.poly_segment=new ArrayList<>();

        //finds associated centroid, and centroid index.
        //Not necessary right now, but still need to check whether polygon creation in geom retained the order of centroids (most likely didn't).
        this.centroid=centroid;
        this.centroid_idx=newCentroidIndex;
        this.poly_vertices=cusVertices;
        this.indexNeighbourCentroids = indexNeighbourCentroids;

        //creates necessary segments. Directly checks for overlap.
        makeSegments();

        //struct.polygon creation without neighbouring polygons.
        this.polygon=Polygon.newBuilder().addAllSegmentIdxs(this.segment_index).addAllNeighborIdxs(neighbours).build();
    }

    public CustomVertex getCentroid(){
        return this.centroid;
    }

    protected List<CustomVertex> makeVertices(){


        CustomVertex v1=makeVertex(centroid.x-10, centroid.y-10);
        CustomVertex v2=makeVertex(centroid.x+10, centroid.y-10);
        CustomVertex v3=makeVertex(centroid.x+10, centroid.y+10);
        CustomVertex v4=makeVertex(centroid.x-10, centroid.y+10);


        return Arrays.asList(v1,v2,v3,v4);
    }

    public Polygon getPolygon(){
        return polygon;
    }



    protected void makeSegments(){
        for (int i=1; i<poly_vertices.size(); i++){
            CustomSegments s=makeSegment(vertices.indexOf(poly_vertices.get(i-1)),vertices.indexOf(poly_vertices.get(i)));
            poly_segment.add(s);
            segment_index.add(segments.indexOf(s));
        }
        CustomSegments s=makeSegment(vertices.indexOf(poly_vertices.get(0)),vertices.indexOf(poly_vertices.get(poly_vertices.size()-1)));
        poly_segment.add(s);
        segment_index.add(segments.indexOf(s));

        if(indexNeighbourCentroids != null) {
            for(int i=0; i<indexNeighbourCentroids.size(); i++){
                CustomSegments new_s=new CustomSegments(this.centroid_idx,indexNeighbourCentroids.get(i),Color.GRAY, "0.5f", this.centroid_idx);
                segments.add(new_s);
                this.neighbours.add(segments.indexOf(new_s));
            }
        }
    }



    protected Polygon makePolygon(){
        return Polygon.newBuilder().addAllSegmentIdxs(this.segment_index).addAllNeighborIdxs(neighbours).build();
    }



    private CustomVertex makeVertex(double x, double y){
        CustomVertex v=new CustomVertex(x,y,precision);

        for (CustomVertex c: vertices){
            if (c.x==v.x & c.y==v.y){
                return c;
            }
        }

        vertices.add(v);
        return v;
    }

    private CustomSegments makeSegment(int v1, int v2){
        CustomSegments s=new CustomSegments(v1,v2,calcColor(vertices.get(v1),vertices.get(v2)), "0.5f", this.centroid_idx);
        for (CustomSegments c: segments){
            if ((c.v1==s.v1 & c.v2==s.v2 | c.v2==s.v1 & c.v1==s.v2 )){
                if (indexNeighbourCentroids==null){
                    CustomSegments new_s=new CustomSegments(this.centroid_idx,c.centroid,Color.GRAY, "0.5f", this.centroid_idx);
                    segments.add(new_s);
                    this.neighbours.add(segments.indexOf(new_s));
                }
                return c;
            }
        }
        segments.add(s);
        return s;
    }

    protected Color calcColor(CustomVertex v1, CustomVertex v2){
        Color c1=v1.getColour();
        Color c2=v2.getColour();
        int red=(c1.getRed()+c2.getRed())/2;
        int blue=(c1.getBlue()+c2.getBlue())/2;
        int green=(c1.getGreen()+c2.getGreen())/2;
        return new Color(red,blue,green);
    }

}

