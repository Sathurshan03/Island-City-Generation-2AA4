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
    GeometryFactory geometryFactory = new GeometryFactory();
    VoronoiDiagramBuilder voronoiDiagramBuilder = new VoronoiDiagramBuilder();



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
        List<Polygon> geo_polygons = VoronoiGen(collection_centroid);

        //Keeps all polygons within the width and height.
        keepInsideMesh(geo_polygons);

        //Applying Lyold Relaxation
        geo_polygons=Relaxation(geo_polygons, relationLevel);


        //goes through each geo.Polygon and converts it to a CustomPolygon.
        int newIndex = 0;
        for (int i = 0; i< geo_polygons.size(); i++){
            GeoStruct conversion=new GeoStruct(geo_polygons.get(i), newIndex);

            if (conversion.isPolygon()){
                CustomPolygon poly=conversion.getCusPolygon();
                addPolygon(poly.getPolygon());
                newIndex++;
                centroids.add(poly.centroid);
            }
        }

    }


    public List<Polygon> VoronoiGen(List<Coordinate> collection_centroid){
        voronoiDiagramBuilder.setSites(collection_centroid);
        return voronoiDiagramBuilder.getSubdivision().getVoronoiCellPolygons(geometryFactory);
    }



    public List<Polygon> Relaxation(List<Polygon> polygons, int level){
        for (int i = 0; i < level; i++)
        {
            //Set the site to the vertex of all polygons
            collection_centroid = new ArrayList<>();
            for (Polygon polygon: polygons){
                collection_centroid.add(polygon.getCentroid().getCoordinate());
            }

            //Recompute with voronoi
            voronoiDiagramBuilder = new VoronoiDiagramBuilder();

            polygons=VoronoiGen(collection_centroid);

            keepInsideMesh(polygons);
        }
        return polygons;


    }



    public void keepInsideMesh(List<Polygon> polygons)
    {
        for (Polygon p: polygons){
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



    public void createCentroids(){
        Random rand=new Random();

        for (int i=0; i<super.numPolygons; i++){
            double random_x= rand.nextDouble(0,width);
            double random_y=rand.nextDouble(0,height);
            Coordinate coordinate = new Coordinate(random_x, random_y);

            collection_centroid.add(coordinate);
        }

    }

}
