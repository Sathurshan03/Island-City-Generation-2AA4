package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GeoStruct extends MeshADT {
    //Will store the vertices of polygon in customvertex form.
    protected List<CustomVertex> new_poly_vertex;

    //Stores the Polygon obtained from voronoi algorithm.
    protected Polygon geoPolygon;

    //Stores the CustomPolygon equivalent
    protected CustomPolygon cusPolygon;

    private boolean isValid;

    protected CustomVertex centroid;





    public GeoStruct(Polygon init_poly, int centroidIndex, int newIndex){

        this.geoPolygon=init_poly;

        this.new_poly_vertex=getCustomVertices();

        this.centroid=new CustomVertex(init_poly.getCentroid().getX(), init_poly.getCentroid().getY(), Color.RED, "2.0", 2);


        if (new_poly_vertex.size() >= 3) //polygon is valid if there are atleast 3 vertex
        {
            isValid = true;
            this.cusPolygon=new CustomPolygon(new_poly_vertex,this.centroid,centroidIndex,newIndex);
        }
        else{
            isValid = false;
        }

    }

    public boolean isPolygon(){
        return isValid;
    }

    //returns the custom Polygon that has been fully converted from the geo.Polygon.
    public CustomPolygon getCusPolygon(){
        return this.cusPolygon;
    }



    public List<CustomVertex> getCustomVertices(){
        //List that will store the vertices of this polygon.
        List<CustomVertex> curr_vertices=new ArrayList<>();
        List<CustomVertex> polygonVertices=new ArrayList<>();
        int numVertex = 0;

        //Iterates through all vertices for that polygon.
        for (Coordinate i: geoPolygon.getCoordinates()){
            //Temporary fix until I can figure out why width won't work
            if (i.getX()>= 0 && i.getX()<=500 && i.getY()>= 0 && i.getY()<=500){
                CustomVertex new_vertex=new CustomVertex(i.getX(),i.getY(),2);
               
                curr_vertices.add(new_vertex);
                numVertex++;
                
            }
        }
        
        for (CustomVertex vertex: curr_vertices)
        {
            CustomVertex point = checkVertex(vertex);
            if (!polygonVertices.contains(point)) {
                polygonVertices.add(point);
            }
        }



        return polygonVertices;

    }

    //Used to check whether vertex has been added before.
    //Won't work if we try to eliminate duplicates in CustomPolygon for some reason. Index out of bound error
    //Need to look into.
    public CustomVertex checkVertex(CustomVertex v){
        for (CustomVertex vertex:vertices){
            if (vertex.x==v.x & vertex.y==v.y){
                return vertex;
            }
        }
        vertices.add(v);
        return v;
    }
}
