package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomPolygon extends MeshADT{

    protected List<CustomVertex> poly_vertices;

    protected List<CustomSegments> poly_segment;
    protected List<Integer> indexNeighbourCentroids;

    protected int squareSize;


    protected List<Integer> segment_index;

    protected List<Integer> neighbours;

    protected CustomVertex centroid;
    protected Integer centroid_idx;


    protected Polygon polygon;

    public CustomPolygon(int centroid, int precision, int squareSize){
        this.segment_index=new ArrayList<>();
        this.neighbours=new ArrayList<>();
        this.poly_segment=new ArrayList<>();
        this.centroid=centroids.get(centroid);
        this.centroid_idx=centroid;
        this.squareSize = squareSize;
        this.poly_vertices=makeVertices();
        this.poly_segment=makeSegments();
        this.segment_index=getSegmentIndex(this.poly_segment);
        this.polygon=makePolygon();
    }

    public CustomPolygon(List<CustomVertex> cusVertices, CustomVertex centroid, int newCentroidIndex, List<Integer> indexNeighbourCentroids){
        //initializes all of the required lists.
        this.segment_index=new ArrayList<>();
        this.neighbours=new ArrayList<>();
        this.poly_segment=new ArrayList<>();

        //finds associated centroid, and centroid index.
        this.centroid=centroid;
        this.centroid_idx=newCentroidIndex;
        this.poly_vertices=cusVertices;
        this.indexNeighbourCentroids = indexNeighbourCentroids;

        //creates necessary segments. Directly checks for overlap.
        this.poly_segment=makeSegments();
        this.segment_index=getSegmentIndex(this.poly_segment);

        makeNeighbourSegments();

        //struct.polygon creation without neighbouring polygons.
        this.polygon=makePolygon();
    }

    public CustomVertex getCentroid(){
        return this.centroid;
    }

    protected List<CustomVertex> makeVertices(){
        double offset = squareSize / 2;

        CustomVertex v1=makeVertex(centroid.x-offset, centroid.y-offset);
        CustomVertex v2=makeVertex(centroid.x+offset, centroid.y-offset);
        CustomVertex v3=makeVertex(centroid.x+offset, centroid.y+offset);
        CustomVertex v4=makeVertex(centroid.x-offset, centroid.y+offset);


        return Arrays.asList(v1,v2,v3,v4);
    }

    public Polygon getPolygon(){
        return polygon;
    }

    protected List<Integer> getSegmentIndex(List<CustomSegments> partSegments){
        List<Integer> indexes=new ArrayList<>();
        for (CustomSegments s: partSegments){
            indexes.add(segments.indexOf(s));
        }
        return indexes;

    }


    protected List<CustomSegments> makeSegments(){
        List<CustomSegments> curr_segments=new ArrayList<>();
        for (int i=1; i<poly_vertices.size(); i++){
            CustomSegments s=makeSegment(vertices.indexOf(poly_vertices.get(i-1)),vertices.indexOf(poly_vertices.get(i)));
            curr_segments.add(s);
        }
        CustomSegments s=makeSegment(vertices.indexOf(poly_vertices.get(0)),vertices.indexOf(poly_vertices.get(poly_vertices.size()-1)));
        curr_segments.add(s);
        return curr_segments;
    }


    protected void makeNeighbourSegments(){
        if(indexNeighbourCentroids != null) {
            for (Integer i: indexNeighbourCentroids){
                CustomSegments new_s=new CustomSegments(this.centroid_idx,i,Color.GRAY, "0.5f", this.centroid_idx);
                segments.add(new_s);
                this.neighbours.add(segments.indexOf(new_s));
            }
        }
    }



    protected Polygon makePolygon(){
        return Polygon.newBuilder().addAllSegmentIdxs(this.segment_index).addAllNeighborIdxs(this.neighbours).build();
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

