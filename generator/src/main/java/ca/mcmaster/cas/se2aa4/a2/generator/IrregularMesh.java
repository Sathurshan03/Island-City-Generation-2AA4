package ca.mcmaster.cas.se2aa4.a2.generator;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



    public class IrregularMesh extends MeshADT {
        VoronoiDiagramBuilder voronoiDiagramBuilder = new VoronoiDiagramBuilder();
        GeometryFactory geometryFactory = new GeometryFactory();


        Geometry triangles;

        public IrregularMesh(int width, int height, int precision, int numPolygons, int relationLevel) {
            super(width, height, precision, numPolygons);

            //will be used to store the coordinates of centroid for geo.polygon.
            collection_centroid = new ArrayList<>();

            //will be used to store the CustomVertex version of centroids.
            centroids = new ArrayList<>();

            vertices = new ArrayList<>();
            segments = new ArrayList<>();


            //generates the centroids in random order.
            createCentroids();


            //Generates the polygons using Voronoi
            List<Polygon> geo_polygon = VoronoiGen(collection_centroid);
            keepInsideMesh(geo_polygon);

            geo_polygon = Relaxation(geo_polygon, relationLevel);


            centroids.clear();
            //goes through each geo.Polygon and converts it to a CustomPolygon.
            int newIndex = 0;

            for (int i = 0; i < geo_polygon.size(); i++) {
                List<Integer> indexNeighbourCentroids = Triangulation(geo_polygon.get(i), geo_polygon);

                CustomVertex currCentroids=new CustomVertex(geo_polygon.get(i).getCentroid().getX(), geo_polygon.get(i).getCentroid().getY(), Color.RED, "2.0", 2);
                centroids.add(currCentroids);

                GeoStruct conversion = new GeoStruct(geo_polygon.get(i), newIndex, indexNeighbourCentroids);

                if (conversion.isPolygon()) {
                    CustomPolygon poly = conversion.getCusPolygon();
                    addPolygon(poly.getPolygon());
                    newIndex++;
                }
            }

        }


        public List<Polygon> VoronoiGen(List<Coordinate> collection_centroid) {
            voronoiDiagramBuilder.setSites(collection_centroid);
            return voronoiDiagramBuilder.getSubdivision().getVoronoiCellPolygons(geometryFactory);
        }


        public List<Polygon> Relaxation(List<Polygon> polygons, int level) {
            for (int i = 0; i < level; i++) {
                //Set the site to the vertex of all polygons
                collection_centroid = new ArrayList<>();
                for (Polygon polygon : polygons) {
                    collection_centroid.add(polygon.getCentroid().getCoordinate());
                }

                //Recompute with voronoi
                voronoiDiagramBuilder = new VoronoiDiagramBuilder();

                polygons = VoronoiGen(collection_centroid);

                keepInsideMesh(polygons);
            }
            return polygons;


        }

        public List<Integer> Triangulation(Polygon p1, List<Polygon> polygons) {
            DelaunayTriangulationBuilder delaunayTriangulationBuilder = new DelaunayTriangulationBuilder();
            delaunayTriangulationBuilder.setSites(collection_centroid);
            triangles = delaunayTriangulationBuilder.getTriangles(new GeometryFactory());

            List<Polygon> neighbours = new ArrayList<>();
            LinearRing p1Boundary = (LinearRing) p1.getExteriorRing();

            for (Polygon p2 : polygons) {
                if (p2.equalsExact(p1)) {
                    continue;
                }

                boolean areAdjacent = false;
                LinearRing p2Boundary = (LinearRing) p2.getExteriorRing();

                for (int i = 0; i < triangles.getNumGeometries(); i++) {
                    Geometry triangle = triangles.getGeometryN(i);
                    if (p1Boundary.intersects(triangle) && p2Boundary.intersects(triangle) && p1Boundary.intersects(p2Boundary)) {
                        areAdjacent = true;
                        break;
                    }
                }
                if (areAdjacent) {
                    neighbours.add(p2);
                }
            }
            List<Integer> indexNeighbourCentroids = new ArrayList<>();
            for (Polygon neighbour : neighbours) {
                for (CustomVertex c : centroids) {
                    double tolerance = 0.01;
                    Point centroid = geometryFactory.createPoint(new Coordinate(c.getX(), c.getY()));
                    if (neighbour.getCentroid().distance(centroid) < tolerance) {
                        indexNeighbourCentroids.add(centroids.indexOf(c));
                    }
                }
            }

            return indexNeighbourCentroids;
        }

        public void keepInsideMesh(List<Polygon> polygons) {
            for (Polygon p : polygons) {
                //go through all connecting vertex and resize if goes outside width or height.
                for (Coordinate pi : p.getCoordinates()) {
                    if (pi.getX() > width) {
                        pi.setX(width);
                    } else if (pi.getX() < 0) {
                        pi.setX(0);
                    }
                    if (pi.getY() > height) {
                        pi.setY(height);
                    } else if (pi.getY() < 0) {
                        pi.setY(0);
                    }
                }
            }
        }

        public void convertCoordinateToVertex(List<Coordinate> newVertices) {
            centroids.clear();
            for (Coordinate vertex : newVertices) {
                double x = vertex.getX();
                double y = vertex.getY();
                centroids.add(new CustomVertex(x, y, new Color(254, 0, 0, 254), "2.0", precision));
            }
        }

        public void createCentroids() {
            Random rand = new Random();

            for (int i = 0; i < super.numPolygons; i++) {
                double random_x = rand.nextDouble(0, width);
                double random_y = rand.nextDouble(0, height);
                Coordinate coordinate = new Coordinate(random_x, random_y);

                collection_centroid.add(coordinate);
            }
        }
    }
