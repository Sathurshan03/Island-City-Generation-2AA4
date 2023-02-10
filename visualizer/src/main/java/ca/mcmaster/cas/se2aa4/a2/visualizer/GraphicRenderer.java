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

public class GraphicRenderer {

    public void render(Mesh aMesh, Graphics2D canvas) {


        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);

        List<Integer> drawn_vertex=new ArrayList<>();
        List<Integer> drawn_segment=new ArrayList<>();


        List<Vertex> vertex_list=aMesh.getVerticesList();
        List<VertexVisualizer> vertexVisualsList = new ArrayList<>();
        for (Vertex vertex : vertex_list){
            vertexVisualsList.add(new VertexVisualizer(vertex));
        }

        List<Segment> segments=aMesh.getSegmentsList(); // size: (height / spaceSize) * ((height / spaceSize) + 1)
        List<SegmentVisualizer> segmentVisualsList = new ArrayList<>();
        for (Segment segment : segments){
            segmentVisualsList.add(new SegmentVisualizer(segment));
        }

        List<Polygon> polygons=aMesh.getPolygonsList();
        Ellipse2D point;


        for (Polygon p:polygons){                        
            for (Integer i:p.getSegmentIdxsList()){
                SegmentVisualizer segmentVisual = segmentVisualsList.get(i);
                Integer v1= segmentVisual.getVertedIDX1();
                Integer v2= segmentVisual.getVertedIDX2();
                

                if (!drawn_vertex.contains(v1)) {
                    VertexVisualizer vertexVisual = vertexVisualsList.get(v1);
                    Color old = canvas.getColor();

                    canvas.setColor(vertexVisual.getColor());

                    point = vertexVisual.getPoint();
                    canvas.fill(point);
                    
                    canvas.setColor(old);
                    drawn_vertex.add(v1);
                    vertexVisual.drawn();
                }

                if (!drawn_vertex.contains(v2)){
                    VertexVisualizer vertexVisual = vertexVisualsList.get(v2);
                    Color old = canvas.getColor();

                    canvas.setColor(vertexVisual.getColor());
                    point = vertexVisual.getPoint();

                    canvas.fill(point);
                    canvas.setColor(old);
                    drawn_vertex.add(v2);
                    vertexVisual.drawn();
                }


                if (!drawn_segment.contains(i)){
                    Color old = canvas.getColor();
                    canvas.setColor(segmentVisual.getColor());
                    
                    double x1 = vertexVisualsList.get(v1).getX();
                    double y1 = vertexVisualsList.get(v1).getY();
                    double x2 = vertexVisualsList.get(v2).getX();
                    double y2 = vertexVisualsList.get(v2).getY();
                    Line2D line = segmentVisual.getLine(x1, y1, x2, y2);

                    canvas.draw(line);
                    canvas.setColor(old);
                    drawn_segment.add(i);
                }
            }
        }
    }








}


