package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GeoStruct extends MeshADT {

    protected List<CustomVertex> new_poly_vertex;
    protected List<CustomSegments> new_poly_segment;

    protected Polygon geoPolygon;

    protected CustomVertex centroid;

    protected CustomPolygon cusPolygon;





    public GeoStruct(Polygon init_poly){
        this.geoPolygon=init_poly;
        this.new_poly_vertex=getCustomVertices();
        this.centroid=new CustomVertex(init_poly.getCentroid().getX(), init_poly.getCentroid().getY(), Color.RED, "0.5f", 2);
        this.cusPolygon=new CustomPolygon(new_poly_vertex);


    }

    public CustomPolygon getCusPolygon(){
        return this.cusPolygon;
    }



    public List<CustomVertex> getCustomVertices(){
        //List that will store the vertices of this polygon.
        List<CustomVertex> curr_vertices=new ArrayList<>();

        //Iterates through all vertices for that polygon.
        for (Coordinate i: geoPolygon.getCoordinates()){
            CustomVertex new_vertex=new CustomVertex(i.getX(),i.getY(),2);
            curr_vertices.add(new_vertex);
        }

        return curr_vertices;

    }
}
