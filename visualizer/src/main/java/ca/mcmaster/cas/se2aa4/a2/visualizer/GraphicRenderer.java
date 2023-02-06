package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

public class GraphicRenderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);

        List<Vertex> vertex_list=aMesh.getVerticesList();

        List<Segment> segmentx=aMesh.getSegmentsList().subList(0,600);
        List<Segment> segmenty=aMesh.getSegmentsList().subList(600,1200);


        for (int i=0, j=0, k=0; i< vertex_list.size(); i++){
            Vertex v=vertex_list.get(i);
            double centre_x = v.getX() - (THICKNESS/2.0d);
            double centre_y = v.getY() - (THICKNESS/2.0d);
            Color old = canvas.getColor();
            canvas.setColor(extractColor(v.getPropertiesList()));
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
            canvas.fill(point);
            canvas.setColor(old);

        }

        int x=0;
        int y=0;
        for (Segment s: segmentx){
            Color old=canvas.getColor();
            canvas.setColor(extractColor(s.getPropertiesList()));
            Line2D line=new Line2D.Double(s.getV1Idx(), y, s.getV2Idx(), y);
            canvas.draw(line);
            canvas.setColor(old);

            if (y==480){
                x+=20;
                y=0;
            }else{
                y+=20;
            }

        }

        x=0;
        y=0;
        for (Segment s: segmenty){
            Color old=canvas.getColor();
            canvas.setColor(extractColor(s.getPropertiesList()));
            Line2D line=new Line2D.Double(x, s.getV1Idx(), x, s.getV2Idx());
            canvas.draw(line);
            canvas.setColor(old);

            if (x==480){
                y+=20;
                x=0;
            }else{
                x+=20;
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


