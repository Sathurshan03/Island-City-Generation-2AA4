package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            //Get the polygon's vertex and segment visualization information
            for (Integer i : polygon.getSegmentIdxsList()) {
                SegmentVisualizer segmentVisual = segmentVisualsList.get(i);
                Integer v1 = segmentVisual.getVertedIDX1() + polygons.size();
                Integer v2 = segmentVisual.getVertedIDX2() + polygons.size();

                drawVertex(vertexVisualsList, canvas, v1);
                drawVertex(vertexVisualsList, canvas, v2);

                //Print the segment that hasn't been drawn yet
                if (!segmentVisual.isDrawn()) {
                    Color old = canvas.getColor();
                    canvas.setColor(segmentVisual.getColor());

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

            //draw centroid in red
            if (debug) {
                //centroid points are stored in last |polygons| of vertexVisualsList
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
                vertexVisual.draw();
            }
        }
    }

