package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.List;

public class MeshADT {

    List<Object> mesh;
    private int precision;
    protected int height;
    protected int width;

    public MeshADT(int width, int height, int precision){
        mesh = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.precision = precision;
    }

    public int addPolygon(Object polygon){
        mesh.add(polygon);
        return mesh.indexOf(polygon);
    }

    public int getPrecision(){
        return precision;
    }

    public int getWidth()
    {
        return width;
    }
    
    public int getHeight(){
        return height;
    }
}
