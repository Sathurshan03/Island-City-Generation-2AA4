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





    public GeoStruct(Polygon init_poly, int index){

        this.geoPolygon=init_poly;

        this.new_poly_vertex=getCustomVertices();

        this.cusPolygon=new CustomPolygon(new_poly_vertex,index);


    }

    //returns the custom Polygon that has been fully converted from the geo.Polygon.
    public CustomPolygon getCusPolygon(){
        return this.cusPolygon;
    }



    public List<CustomVertex> getCustomVertices(){
        //List that will store the vertices of this polygon.
        List<CustomVertex> curr_vertices=new ArrayList<>();

        //Iterates through all vertices for that polygon.
        for (Coordinate i: geoPolygon.getCoordinates()){
            //Temporary fix until I can figure out why width won't work
            if (i.getX()<=500 & i.getY()<=500){
                CustomVertex new_vertex=new CustomVertex(i.getX(),i.getY(),2);
                //Adds new vertices only if they haven't been added before
                curr_vertices.add(checkVertex(new_vertex));
            }
        }

        return curr_vertices;

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
