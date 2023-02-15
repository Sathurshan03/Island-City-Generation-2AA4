package ca.mcmaster.cas.se2aa4.a2.generator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.locationtech.jts.geom.Coordinate;

public class IrregularMesh extends MeshADT {
    List<Coordinate> centroidCoordinates;

    public IrregularMesh(int width, int height, int precision, int numPolygons){
        super(width,height, precision, numPolygons);
        centroids=new ArrayList<>();
        vertices=new ArrayList<>();
        segments=new ArrayList<>();
        centroidCoordinates = new ArrayList<>();

        createCentroidVertices();

        for (int i=0; i<centroids.size(); i++){
            CustomPolygon polygon=new CustomPolygon(i, precision);
            addPolygon(polygon.gePolygon());
        }
    }

    public List<Coordinate> getCoordinates(){
        return centroidCoordinates;
    }

    public void createCentroidVertices(){
        Random rand=new Random();

        for (int i=0; i<super.numPolygons; i++){
            double random_x= rand.nextDouble(0,width);
            double random_y=rand.nextDouble(0,height);
            Coordinate coordinate = new Coordinate(random_x, random_y);

            CustomVertex new_centroid=new CustomVertex(random_x,random_y,new Color(254,0,0,254), "2.0", precision);

            centroids.add(new_centroid);
            centroidCoordinates.add(coordinate);
        }

    }

}
