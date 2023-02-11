package ca.mcmaster.cas.se2aa4.a2.visualizer;

import java.awt.Color;
import java.util.List;
import java.awt.geom.Ellipse2D;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public class VertexVisualizer {
    private double X;
    private double Y;
    private Boolean drawn;
    private Vertex vertex;
    private boolean debug;
    private boolean isCentroid;

    public VertexVisualizer (Vertex vertex, Boolean debug, Boolean isCentroid)
    {
        this.X = vertex.getX();
        this.Y = vertex.getY();
        this.drawn = false;
        this.vertex = vertex;
        this.debug = debug;
        this.isCentroid = isCentroid;
    }
    public double getX()
    {
        return X;
    }

    public double getY()
    {
        return Y;
    }

    public boolean isDrawn()
    {
        return drawn;
    }

    public Color getColor(){
        if (debug && !isCentroid)
        {
            return Color.BLACK;
        }
        return extractVertexColor(vertex.getPropertiesList());
    }

    private double getThickness(){
        return extractThickness(vertex.getPropertiesList());
    }

    public void draw(){
        drawn = true; 
    }

    public Ellipse2D getPoint(){
        double thickness = getThickness();

        double centre_x = X - (thickness/2.0d);
        double centre_y = Y - (thickness/2.0d);

        Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, thickness, thickness);

        return point;
    }


    private Color extractVertexColor(List<Property> properties) {
        String val = null;

        //Get the colours properties
        for(Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
                System.out.println(p.getValue());
                val = p.getValue();
            }
        }

        if (val == null)
            return Color.BLACK;

        //Extract the colour and transparency values
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        int transparency = Integer.parseInt(raw[3]);

        return new Color(red, green, blue, transparency);
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


}
