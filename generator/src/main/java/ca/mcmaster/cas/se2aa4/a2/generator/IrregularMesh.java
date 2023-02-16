package ca.mcmaster.cas.se2aa4.a2.generator;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IrregularMesh extends MeshADT {

    public IrregularMesh(int width, int height, int precision, int numPolygons){
        super(width,height, precision, numPolygons);

        //will be used to store the coordinates of centroid for geo.polygon.
        collection_centroid =new ArrayList<>();

        //will be used to store the CustomVertex version of centroids.
        centroids=new ArrayList<>();

        vertices=new ArrayList<>();
        segments=new ArrayList<>();

        //generates the centroids in random order.
        createCentroids();

        //Generates the polygons using Voronoi
        GeometryFactory geometryFactory = new GeometryFactory();
        VoronoiDiagramBuilder voronoiDiagramBuilder = new VoronoiDiagramBuilder();
        voronoiDiagramBuilder.setSites(collection_centroid);
        List<Polygon> polygons = voronoiDiagramBuilder.getSubdivision().getVoronoiCellPolygons(geometryFactory);

        //goes through each geo.Polygon and converts it to a CustomPolygon.
        for (int i=0; i<polygons.size(); i++){
            GeoStruct conversion=new GeoStruct(polygons.get(i), i);
            addPolygon(conversion.getCusPolygon().gePolygon());

        }

    }

    public void createCentroids(){
        Random rand=new Random();

        for (int i=0; i<super.numPolygons; i++){
            double random_x= rand.nextDouble(0,width);
            double random_y=rand.nextDouble(0,height);

            CustomVertex new_centroid=new CustomVertex(random_x,random_y,new Color(254,0,0,254), "2.0", precision);

            centroids.add(new_centroid);
            collection_centroid.add(new Coordinate(random_x,random_y));

        }

    }

}
