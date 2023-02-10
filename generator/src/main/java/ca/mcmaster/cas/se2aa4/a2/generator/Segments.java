package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.List;

public class Segments {

    private static final int THICKNESS = 3;
    private int v1x; // should these be attributes? what are the attributes for the class?
    private int v2x;
    private int v1y;
    private int v2y;
    Property colour;
    Segment segment;

    public Segments(int v1x, int v2x, int v1y, int v2y){
        this.v1x = v1x;
        this.v2x = v2x;
        this.v1y = v1y;
        this.v2y = v2y;
//        this.colour = segColor();
//        this.segment = Segment.newBuilder().
    }

    public Line2D drawSegment(){
        return new Line2D.Double(v1x,v1y,v2x,v2y);
    }
    public int getV1x(){
        return v1x;
    }
    public int getV1y(){
        return v1y;
    }
    public int getV2x(){
        return v2x;
    }
    public int getV2y(){return v2y;}

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

    public Property segColor(Vertex v1, Vertex v2){
        Color c1=extractColor(v1.getPropertiesList());
        Color c2=extractColor(v2.getPropertiesList());
        int red=(c1.getRed()+c2.getRed())/2;
        int blue=(c1.getBlue()+c2.getBlue())/2;
        int green=(c1.getGreen()+c2.getGreen())/2;
        String colorCode = red + "," + green + "," + blue;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

        return color;

    }

//    public void render(Mesh aMesh, Graphics2D canvas) {
//        canvas.setColor(Color.BLACK);
//        Stroke stroke = new BasicStroke(0.5f);
//        canvas.setStroke(stroke);
//
//        List<Vertex> vertex_list=aMesh.getVerticesList();
//
//        List<Segment> segmentx=aMesh.getSegmentsList().subList(0,600);
//        List<Segment> segmenty=aMesh.getSegmentsList().subList(600,1200);

//        int x=0;
//        int y=0;
//        for (Segment s: segmentx){
//            Color old=canvas.getColor();
//            canvas.setColor(extractColor(s.getPropertiesList()));
//            Line2D line=new Line2D.Double(s.getV1Idx(), y, s.getV2Idx(), y);
//            canvas.draw(line);
//            canvas.setColor(old);

//            if (y==480){
//                x+=20;
//                y=0;
//            }else{
//                y+=20;
//            }

        }

//        x=0;
//        y=0;
//        for (Segment s: segmenty){
//            Color old=canvas.getColor();
//            canvas.setColor(extractColor(s.getPropertiesList()));
//            Line2D line=new Line2D.Double(x, s.getV1Idx(), x, s.getV2Idx()); // how is the segment going to be drawn with a colour, segColour returns a property?
//            canvas.draw(line);
//            canvas.setColor(old);

//            if (x==480){
//                y+=20;
//                x=0;
//            }else{
//                x+=20;
//            }
//  }



//    }
//
//}
