package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

public abstract class MeshADT {

    List<Object> mesh;
    private int precision;
    protected int height;
    protected int width;
    protected List<Vertex> vertices;

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
    
    public void addVertex(Vertex vertex)
    {
        vertices.add(vertex);
    }
    
    public List<Vertex> getVertices(){
        return vertices;
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

    abstract public void createCentroidVertices();
}
