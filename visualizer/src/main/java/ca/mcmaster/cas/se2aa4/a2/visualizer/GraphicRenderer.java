package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

public class GraphicRenderer {
    public void render(Mesh aMesh, Graphics2D canvas, Boolean debug) {
        //Set up the canvas
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);

        //Polygon information
        List<Polygon> polygons = aMesh.getPolygonsList();

        //Vertex information
        List<Vertex> vertex_list = aMesh.getVerticesList();
        List<VertexVisualizer> vertexVisualsList = new ArrayList<>();
        for (int i = 0; i < vertex_list.size(); i++) {
            //centroid points are stored in first |polygons| of vertexVisualsList
            if (i < polygons.size()) {
                vertexVisualsList.add(new VertexVisualizer(vertex_list.get(i), debug, true));
            } else {
                vertexVisualsList.add(new VertexVisualizer(vertex_list.get(i), debug, false));
            }
        }

        //Segment information
        List<Segment> segments = aMesh.getSegmentsList();
        List<SegmentVisualizer> segmentVisualsList = new ArrayList<>();
        for (Segment segment : segments) {
            segmentVisualsList.add(new SegmentVisualizer(segment, debug));
        }

        //Print Polygon one by one
        for (Polygon polygon : polygons) {
            //print each segment of that polygon one by one 
            List<Integer> pointsId = new ArrayList<>();

            for (Integer i : polygon.getSegmentIdxsList()) {
                SegmentVisualizer segmentVisual = segmentVisualsList.get(i);

                //Get the two vertices of the segment 
                Integer v1 = segmentVisual.getVertedIDX1()+polygons.size();
                Integer v2 = segmentVisual.getVertedIDX2()+polygons.size();

                drawVertex(vertexVisualsList, canvas, v1);
                drawVertex(vertexVisualsList, canvas, v2);

                double x1 = vertexVisualsList.get(v1).getX();
                double y1 = vertexVisualsList.get(v1).getY();
                double x2 = vertexVisualsList.get(v2).getX();
                double y2 = vertexVisualsList.get(v2).getY();




                //Only draw the segment if it had not been drawn
                if (!segmentVisual.isDrawn()){
                    if(!pointsId.contains(v1)){
                        pointsId.add(v1);
                    }
                    else{
                        pointsId.add(v2);
                    }
                    
                    Color old = canvas.getColor();

                    canvas.setColor(segmentVisual.getColor());
                    Line2D line = segmentVisual.getLine(x1, y1, x2, y2);

                    canvas.draw(line);
                    canvas.setColor(old);
                    segmentVisual.draw();
                }
                else{
                    if(!pointsId.contains(v2)){
                        pointsId.add(v2);
                    }
                    else{
                        pointsId.add(v1);
                    }
                }
            }

            //print neighbouring relations in debug mode of that polygon
            if (debug){
                for (Integer i : polygon.getNeighborIdxsList()) {
                    SegmentVisualizer segmentVisual = segmentVisualsList.get(i);

                    Integer v1 = segmentVisual.getVertedIDX1();
                    Integer v2 = segmentVisual.getVertedIDX2();

                    //Get the two vertices of the segment 
                     drawVertex(vertexVisualsList, canvas, v1);
                     drawVertex(vertexVisualsList, canvas, v2);

                    //Only draw the segment if it had not been drawn
                    if (!segmentVisual.isDrawn()){
                        Color old = canvas.getColor();

                        canvas.setColor(Color.gray);

                        double x1 = vertexVisualsList.get(v1).getX();
                        double y1 = vertexVisualsList.get(v1).getY();
                        double x2 = vertexVisualsList.get(v2).getX();
                        double y2 = vertexVisualsList.get(v2).getY();
                        Line2D line = segmentVisual.getLine(x1, y1, x2, y2);

                        canvas.draw(line);
                        canvas.setColor(old);
                        segmentVisual.draw();
                    }
                }
            }

            //Print the background of polygon
            List<Property> properties = polygon.getPropertiesList();
            
            //Get the colour for the polygon 
            String val = null;
            Color polygonColor;

            //Get the colours properties
            for(Property p: properties) {
                if (p.getKey().equals("background_color")) {
                    val = p.getValue();
                }
            }

            if (val == null){
                polygonColor = Color.white;
            }
            else{
                String[] raw = val.split(",");
                int red = Integer.parseInt(raw[0]);
                int green = Integer.parseInt(raw[1]);
                int blue = Integer.parseInt(raw[2]);
                int transparency = Integer.parseInt(raw[3]);

                polygonColor = new Color(red, green, blue, transparency);
            }

            //convert struct Vertex to Coordinates
            Coordinate coordinates []= new Coordinate[pointsId.size()];
            for (int i = 0; i < pointsId.size(); i++){
                VertexVisualizer v = vertexVisualsList.get(pointsId.get(i));
                coordinates[i] = new Coordinate(v.getX(), v.getY());
            }

            System.out.println("YEEEHAAA");
            for (Coordinate kl: coordinates){
                System.out.println(kl.getX() + " " + kl.getY());
            }

            // //Apply the convex Hull 
            // GeometryFactory geometryFactory = new GeometryFactory();
            // ConvexHull convexHull = new ConvexHull(coordinates, geometryFactory);
            // Geometry shape = convexHull.getConvexHull();
            // coordinates = shape.getCoordinates();

            System.out.println("BREAK");

            java.awt.geom.Path2D.Double poly = new java.awt.geom.Path2D.Double();
            Coordinate coordinate = coordinates[0];
            System.out.println(coordinates[0].getX() + " " + coordinates[0].getY());
            System.out.println("Yo");
            System.out.println(coordinates.length -1);
            poly.moveTo(coordinate.getX(), coordinate.getY());
            for (int i = 1; i < coordinates.length; i++){
                coordinate = coordinates[i];
                System.out.println(i + " " + coordinate.getX() + " " + coordinate.getY());
                poly.lineTo(coordinate.getX(), coordinate.getY());
            }
            poly.closePath();

            Color old = canvas.getColor();
            canvas.setColor(polygonColor);

            canvas.fill(poly);
            canvas.setColor(old);
            
        }

           //draw centroid in red
           if (debug) {
               //centroid points are stored in first |polygons| of vertexVisualsList
               for (int i = 0; i < polygons.size(); i++) {
                   drawVertex(vertexVisualsList, canvas, i);
               }
           }
        }
        protected void drawVertex (List < VertexVisualizer > vertexVisualsList, Graphics2D canvas,int pos){
            //Print vertex with no overlaps
            if (!vertexVisualsList.get(pos).isDrawn()) {
                VertexVisualizer vertexVisual = vertexVisualsList.get(pos);
                Color old = canvas.getColor();

                canvas.setColor(vertexVisual.getColor());
                Ellipse2D point = vertexVisual.getPoint();

                canvas.fill(point);
                canvas.setColor(old);
                vertexVisual.drawn();
            }
        }
    }