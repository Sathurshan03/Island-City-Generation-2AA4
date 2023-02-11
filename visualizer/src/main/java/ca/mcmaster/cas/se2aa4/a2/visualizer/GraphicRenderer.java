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
<<<<<<< HEAD
    private static final int THICKNESS = 3;

    public void render(Mesh aMesh, Graphics2D canvas) {
=======

    public void render(Mesh aMesh, Graphics2D canvas, Boolean debug) {

        //Set up the canvas
>>>>>>> cdc00dc34f6eac8913049bc05f68e232aaa626d6
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);

<<<<<<< HEAD
        List<Vertex> vertex_list = aMesh.getVerticesList();

        List<Segment> segmentx = aMesh.getSegmentsList().subList(0, 600);
        List<Segment> segmenty = aMesh.getSegmentsList().subList(600, 1200);


        for (int i = 0, j = 0, k = 0; i < vertex_list.size(); i++) {
            Vertex v = vertex_list.get(i);
            double centre_x = v.getX() - (THICKNESS / 2.0d);
            double centre_y = v.getY() - (THICKNESS / 2.0d);
            Color old = canvas.getColor();
            canvas.setColor(extractColor(v.getPropertiesList()));
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
            canvas.fill(point);
            canvas.setColor(old);
=======
        //Polygon information
        List<Polygon> polygons=aMesh.getPolygonsList();
>>>>>>> cdc00dc34f6eac8913049bc05f68e232aaa626d6

        //Vertex information
        List<Vertex> vertex_list=aMesh.getVerticesList();
        List<VertexVisualizer> vertexVisualsList = new ArrayList<>();
        for (int i = 0; i < vertex_list.size(); i++){
            //centroid points are stored in last |polygons| of vertexVisualsList
            if (i >= (vertex_list.size() - polygons.size()))
            {
                vertexVisualsList.add(new VertexVisualizer(vertex_list.get(i), debug, true));
            }
            else
            {
                vertexVisualsList.add(new VertexVisualizer(vertex_list.get(i), debug, false));
            }
        }

<<<<<<< HEAD
        int x = 0;
        int y = 0;
        for (Segment s : segmentx) {
            Color old = canvas.getColor();
            canvas.setColor(extractSegmentColor(s.getPropertiesList()));
            float segThickness = extractThickness(s.getPropertiesList());
            Stroke newStroke = new BasicStroke(segThickness);
            canvas.setStroke(newStroke);
            Line2D line = new Line2D.Double(s.getV1Idx(), y, s.getV2Idx(), y);
            canvas.draw(line);
            canvas.setColor(old);

            if (y == 480) {
                x += 20;
                y = 0;
            } else {
                y += 20;
=======
        //Segment information
        List<Segment> segments=aMesh.getSegmentsList(); 
        List<SegmentVisualizer> segmentVisualsList = new ArrayList<>();
        for (Segment segment : segments){
            segmentVisualsList.add(new SegmentVisualizer(segment, debug));
        }

        


        //Print Polygon one by one
        for (Polygon polygon:polygons){   
            //Get the polygon's vertex and segment visualization information
            for (Integer i:polygon.getSegmentIdxsList()){
                SegmentVisualizer segmentVisual = segmentVisualsList.get(i);
                Integer v1= segmentVisual.getVertedIDX1();
                Integer v2= segmentVisual.getVertedIDX2();

                drawVertex(vertexVisualsList, canvas, v1);
                drawVertex(vertexVisualsList, canvas, v2);
    
                //Print the segment is it hasn't been drawn yet
                if (!segmentVisual.isDrawn()){
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
>>>>>>> cdc00dc34f6eac8913049bc05f68e232aaa626d6
            }
        }

<<<<<<< HEAD
        x = 0;
        y = 0;
        for (Segment s : segmenty) {
            Color old = canvas.getColor();
            canvas.setColor(extractSegmentColor(s.getPropertiesList()));
            float segThickness = extractThickness(s.getPropertiesList());
            Stroke newStroke = new BasicStroke(segThickness);
            canvas.setStroke(newStroke);
            Line2D line = new Line2D.Double(x, s.getV1Idx(), x, s.getV2Idx());
            canvas.draw(line);
            canvas.setColor(old);

            if (x == 480) {
                y += 20;
                x = 0;
            } else {
                x += 20;
            }
        }


=======
        //draw centroid in red
        if (debug)
        {
            //centroid points are stored in last |polygons| of vertexVisualsList
            for (int i = 1; i <= polygons.size(); i ++)
            {
                drawVertex(vertexVisualsList, canvas, vertexVisualsList.size() - i );
            }
        }
>>>>>>> cdc00dc34f6eac8913049bc05f68e232aaa626d6
    }
    protected void drawVertex(List<VertexVisualizer> vertexVisualsList, Graphics2D canvas, int pos){
        //Print vertex with no overlaps
        if (!vertexVisualsList.get(pos).isDrawn()){
            VertexVisualizer vertexVisual = vertexVisualsList.get(pos);
            Color old = canvas.getColor();

<<<<<<< HEAD
    private Color extractColor(List<Property> properties) {
        String val = null;
        for (Property p : properties) {
            if (p.getKey().equals("rgb_color")) {
                System.out.println(p.getValue());
                val = p.getValue();
            }
=======
            canvas.setColor(vertexVisual.getColor());
            Ellipse2D point = vertexVisual.getPoint();

            canvas.fill(point);
            canvas.setColor(old);
            vertexVisual.draw();
>>>>>>> cdc00dc34f6eac8913049bc05f68e232aaa626d6
        }
    }
<<<<<<< HEAD

    public float extractThickness(List<Property> properties){
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("thickness")) {
                val = p.getValue();
            }
        }
        if (val == null)
            return 0;
        return Float.parseFloat(val);
    }

        private Color extractSegmentColor(List<Property> properties) {
            String val = null;
            for (Property p : properties) {
                if (p.getKey().equals("rgb_color")) {
                    System.out.println(p.getValue());
                    val = p.getValue();
                }
            }
            if (val == null)
                return Color.BLACK;
            String[] raw = val.split(",");
            int red = Integer.parseInt(raw[0]);
            int green = Integer.parseInt(raw[1]);
            int blue = Integer.parseInt(raw[2]);
            int transparency = Integer.parseInt(raw[3]);
            return new Color(red, green, blue, transparency);
        }
    }
=======
}
>>>>>>> cdc00dc34f6eac8913049bc05f68e232aaa626d6


