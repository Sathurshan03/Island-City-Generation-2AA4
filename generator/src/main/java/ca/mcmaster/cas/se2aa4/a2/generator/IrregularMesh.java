package ca.mcmaster.cas.se2aa4.a2.generator;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.locationtech.jts.geom.Coordinate;

public class IrregularMesh extends MeshADT {
    List<Coordinate> centroidCoordinates;
    List<Polygon> polygons;

    public IrregularMesh(int width, int height, int precision, int numPolygons, int relationLevel){
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
        polygons = voronoiDiagramBuilder.getSubdivision().getVoronoiCellPolygons(geometryFactory);
        keepInsideMesh();

        List<Coordinate> newVertices= new ArrayList<>();
        for (int i = 0; i < relationLevel; i++)
        {
            //Set the site to the vertex of all polygons
            newVertices = new ArrayList<>();
            for (Polygon polygon:polygons){
                newVertices.add(polygon.getCentroid().getCoordinate());
            }

            //Recompute with voronoi
            geometryFactory = new GeometryFactory();
            voronoiDiagramBuilder = new VoronoiDiagramBuilder();
            voronoiDiagramBuilder.setSites(newVertices);
            polygons = voronoiDiagramBuilder.getSubdivision().getVoronoiCellPolygons(geometryFactory);
            keepInsideMesh();
        }

        convertCoordinateToVertex(newVertices);

        //goes through each geo.Polygon and converts it to a CustomPolygon.
        List<CustomVertex> confirmedVertex = new ArrayList<>();
        int newIndex = 0;
        for (int i=0; i<polygons.size(); i++){
            GeoStruct conversion=new GeoStruct(polygons.get(i), i, newIndex);

            if (conversion.isPolygon()){
                CustomPolygon poly=conversion.getCusPolygon();
                addPolygon(poly.gePolygon());
                newIndex++;
                confirmedVertex.add(poly.centroid);
            }
        }
        centroids=confirmedVertex;


    }
    public void keepInsideMesh()
    {
        for (Polygon p:polygons){
            //go through all connecting vertex and resize if goes outside width or height.
            for (Coordinate pi:p.getCoordinates()){
                if (pi.getX()>width){
                    pi.setX(width);
                }else if (pi.getX()<0){
                    pi.setX(0);
                }
                if (pi.getY()>height){
                    pi.setY(height);
                }else if (pi.getY()<0){
                    pi.setY(0);
                }
            }
        }
    }

    public List<Coordinate> getCoordinates(){
        return centroidCoordinates;
    }

    public void convertCoordinateToVertex(List<Coordinate> newVertices){
        centroids.clear();
        for (Coordinate vertex: newVertices){
            double x = vertex.getX();
            double y = vertex.getY();
            centroids.add(new CustomVertex(x,y,new Color(254,0,0,254), "2.0", precision));
        }
    }

    public void createCentroids(){
        Random rand=new Random();

        for (int i=0; i<super.numPolygons; i++){
            double random_x= rand.nextDouble(0,width);
            double random_y=rand.nextDouble(0,height);
            Coordinate coordinate = new Coordinate(random_x, random_y);

            CustomVertex new_centroid=new CustomVertex(random_x,random_y,new Color(254,0,0,254), "2.0", precision);

            centroids.add(new_centroid);

            collection_centroid.add(coordinate);
        }

    }

}
