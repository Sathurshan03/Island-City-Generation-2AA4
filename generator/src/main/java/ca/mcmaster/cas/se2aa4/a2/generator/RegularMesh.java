package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

public class RegularMesh extends MeshADT{
    int squareSpacing;
    List<Vertex> vertices;

    public RegularMesh(int width, int height, int precision, int squareSpacing)
    {
        super(width, height, precision);
        this.squareSpacing = squareSpacing;
        vertices = new ArrayList<>();
    }

    public List<Vertex> getVertices(){
        return vertices;
    }

    public void addVertex(Vertex vertex)
    {
        vertices.add(vertex);
    }
    
}
