package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomPolygon extends MeshADT{

    private List<CustomVertex> poly_vertices;
    private List<CustomSegments> poly_segment;
    private List<Integer> segment_index;
    private List<Integer> neighbours;
    private CustomVertex centroid;
    private Integer centroid_idx;
    private Boolean isRegular;
    private Polygon polygon;


    public CustomPolygon(int centroid, int squareSize){
        this.isRegular=true;

        this.neighbours=new ArrayList<>();
        this.centroid=centroids.get(centroid);
        this.centroid_idx=centroid;

        this.poly_vertices=makeVertices(squareSize);
        this.poly_segment=makeSegments(poly_vertices);

        this.segment_index=getSegmentIndex(this.poly_segment);
        this.polygon=makePolygon();
    }

    public CustomPolygon(List<CustomVertex> cusVertices, int newCentroidIndex, List<Integer> indexNeighbourCentroids){
        //initializes all of the required lists.
        this.isRegular=false;

        this.neighbours=new ArrayList<>();

        //finds associated centroid, and centroid index.
        this.centroid=centroids.get(newCentroidIndex);
        this.centroid_idx=newCentroidIndex;


        this.poly_vertices=cusVertices;

        //creates necessary segments. Directly checks for overlap.
        this.poly_segment=makeSegments(poly_vertices);

        this.segment_index=getSegmentIndex(this.poly_segment);

        this.neighbours=makeNeighbourSegments(indexNeighbourCentroids);

        //struct.polygon creation without neighbouring polygons.
        this.polygon=makePolygon();
    }

    public CustomVertex getCentroid(){
        return this.centroid;
    }

    public List<CustomSegments> getPolySegments(){
        return this.poly_segment;
    }

    public List<CustomVertex> getPolyVertices(){
        return this.poly_vertices;
    }

    public Polygon getPolygon(){
        return polygon;
    }


    protected List<CustomVertex> makeVertices(int squareSize){
        double offset = squareSize / 2;

        CustomVertex v1=makeVertex(centroid.x-offset, centroid.y-offset);
        CustomVertex v2=makeVertex(centroid.x+offset, centroid.y-offset);
        CustomVertex v3=makeVertex(centroid.x+offset, centroid.y+offset);
        CustomVertex v4=makeVertex(centroid.x-offset, centroid.y+offset);


        return Arrays.asList(v1,v2,v3,v4);
    }


    protected List<Integer> getSegmentIndex(List<CustomSegments> partSegments){
        List<Integer> indexes=new ArrayList<>();
        for (CustomSegments s: partSegments){
            indexes.add(segments.indexOf(s));
        }
        return indexes;
    }


    protected List<CustomSegments> makeSegments(List<CustomVertex> all_vertices){
        List<CustomSegments> curr_segments=new ArrayList<>();
        for (int i=1; i<all_vertices.size(); i++){
            CustomSegments s=makeSegment(vertices.indexOf(all_vertices.get(i-1)),vertices.indexOf(all_vertices.get(i)));
            curr_segments.add(s);
        }
        CustomSegments s=makeSegment(vertices.indexOf(all_vertices.get(0)),vertices.indexOf(all_vertices.get(all_vertices.size()-1)));
        curr_segments.add(s);
        return curr_segments;
    }


    protected List<Integer> makeNeighbourSegments(List<Integer> indexNeighbourCentroids){
        List<Integer> all_neighbours=new ArrayList<>();
        for (Integer i: indexNeighbourCentroids){
            CustomSegments new_s=new CustomSegments(this.centroid_idx,i,this.centroid, centroids.get(i), "0.5f");
            segments.add(new_s);
            all_neighbours.add(segments.indexOf(new_s));
        }
        return all_neighbours;
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
        CustomSegments s=new CustomSegments(v1,v2,vertices.get(v1),vertices.get(v2), "0.5f", this.centroid_idx);
        for (CustomSegments c: segments){
            if ((c.v1_idx ==s.v1_idx & c.v2_idx ==s.v2_idx | c.v2_idx ==s.v1_idx & c.v1_idx ==s.v2_idx)){
                if (isRegular){
                    CustomSegments new_s=new CustomSegments(this.centroid_idx,c.centroid,this.centroid, centroids.get(c.centroid), "0.5f");
                    segments.add(new_s);
                    this.neighbours.add(segments.indexOf(new_s));
                }
                return c;
            }
        }
        segments.add(s);
        return s;
    }

}

