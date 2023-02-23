package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import org.locationtech.jts.geom.Coordinate;

public abstract class MeshADT {

    public List<Polygon> mesh;
    protected static int precision;
    protected int height;
    protected int width;
    protected static List<CustomVertex> vertices;
    protected int numPolygons;
    protected static List<Coordinate> collection_centroid;
    protected static List<CustomVertex> centroids;
    public static List<CustomSegments> segments;

    public MeshADT(){
        this.mesh=new ArrayList<>();
    }


    public MeshADT(int width, int height, int precision, int numPolygons){
        this.mesh = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.precision = precision;
        this.numPolygons = numPolygons;
    }

    public List<Polygon> getMesh(){
        return this.mesh;
    }


    public void addPolygon(Polygon polygon){
        this.mesh.add(polygon);
    }
    
    public void addVertex(CustomVertex vertex)
    {
        vertices.add(vertex);
    }
    
    public List<Vertex> getVertices(){
        List<Vertex> reg_vertices=new ArrayList<>();

        for (CustomVertex v: vertices){
            reg_vertices.add(v.getVertex());
        }
        return reg_vertices;
    }

    public static List<CustomVertex> getAllCustomVertices(){
        return vertices;
    }

    public static List<CustomSegments> getCustomSegments(){
        return segments;
    }

    public static List<CustomVertex> getCustomCentroids(){
        return centroids;
    }



    public List<Segment> getSegments(){
        List<Segment> reg_segments=new ArrayList<>();

        for (CustomSegments s: segments){
            reg_segments.add(s.getSegment());
        }
        return reg_segments;
    }

    public List<Vertex> getCentroids(){
        List<Vertex> reg_centroids=new ArrayList<>();

        for (CustomVertex v:centroids){
            reg_centroids.add(v.getVertex());
        }

        return reg_centroids;
    }

    public static int getPrecision(){
        return precision;
    }

    public int getWidth()
    {
        return width;
    }
    
    public int getHeight(){
        return height;
    }
}
