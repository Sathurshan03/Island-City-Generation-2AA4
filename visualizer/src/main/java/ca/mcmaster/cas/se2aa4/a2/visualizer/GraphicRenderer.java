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


        List<Segment> segment=aMesh.getSegmentsList(); // size: (height / spaceSize) * ((height / spaceSize) + 1)

        List<Polygon> polygons=aMesh.getPolygonsList();
        Ellipse2D point;

        System.out.println(polygons.size());

        for (Polygon p:polygons){
            
            
            System.out.println(5);
            
            for (Integer i:p.getSegmentIdxsList()){
                Segment s=segment.get(i);
                Integer v1=s.getV1Idx();
                Integer v2=s.getV2Idx();
                

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
                    canvas.setColor(extractColor(s.getPropertiesList()));
                    Line2D line = new Line2D.Double(vertex_list.get(v1).getX(), vertex_list.get(v1).getY(), vertex_list.get(v2).getX(), vertex_list.get(v2).getY());
                    canvas.draw(line);
                    canvas.setColor(old);
                    drawn_segment.add(i);
                }
            }
        }
    }

    private Color extractColor(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
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
        return new Color(red, green, blue);
    }






}


