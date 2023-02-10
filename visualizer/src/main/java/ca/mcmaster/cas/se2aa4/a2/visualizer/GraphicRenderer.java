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

        List<Segment> segment=aMesh.getSegmentsList(); // size: (height / spaceSize) * ((height / spaceSize) + 1)

        List<Polygon> polygons=aMesh.getPolygonsList();

        for (Polygon p:polygons){
            for (Integer i:p.getSegmentIdxsList()){
                Segment s=segment.get(i);
                Integer v1=s.getV1Idx();
                Integer v2=s.getV2Idx();

                if (!drawn_vertex.contains(v1)) {
                    Vertex v=vertex_list.get(v1);
                    Color old = canvas.getColor();
                    canvas.setColor(extractVertexColor(v.getPropertiesList()));
                    double thickness = extractThickness(v.getPropertiesList());

                    double centre_x = v.getX() - (thickness/2.0d);
                    double centre_y = v.getY() - (thickness/2.0d);

                    Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, thickness, thickness);
                    canvas.fill(point);
                    canvas.setColor(old);
                    drawn_vertex.add(v1);
                }

                if (!drawn_vertex.contains(v2)){
                    Vertex v=vertex_list.get(v2);
                    Color old = canvas.getColor();
                    canvas.setColor(extractVertexColor(v.getPropertiesList()));
                    double thickness = extractThickness(v.getPropertiesList());

                    double centre_x = v.getX() - (thickness/2.0d);
                    double centre_y = v.getY() - (thickness/2.0d);

                    Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, thickness, thickness);
                    canvas.fill(point);
                    canvas.setColor(old);
                    drawn_vertex.add(v2);
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


        for (int i=0; i< vertex_list.size(); i++){
            Vertex v=vertex_list.get(i);
            Color old = canvas.getColor();
            canvas.setColor(extractVertexColor(v.getPropertiesList()));
            double thickness = extractThickness(v.getPropertiesList());

            double centre_x = v.getX() - (thickness/2.0d);
            double centre_y = v.getY() - (thickness/2.0d);

            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, thickness, thickness);
            canvas.fill(point);
            canvas.setColor(old);

        }

        for (Segment s: segment) {
            Color old = canvas.getColor();
            canvas.setColor(extractColor(s.getPropertiesList()));
            Line2D line = new Line2D.Double(vertex_list.get(s.getV1Idx()).getX(), vertex_list.get(s.getV1Idx()).getY(), vertex_list.get(s.getV2Idx()).getX(), vertex_list.get(s.getV2Idx()).getY());
            canvas.draw(line);
            canvas.setColor(old);
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

    private double extractThickness(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("thickness")) {
                val = p.getValue();
            }
        }
        if (val == null)
            return 0;
        return Double.parseDouble(val);
    }



    private Color extractVertexColor(List<Property> properties) {
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
        int transparency = Integer.parseInt(raw[3]);
        return new Color(red, green, blue, transparency);
    }

}


