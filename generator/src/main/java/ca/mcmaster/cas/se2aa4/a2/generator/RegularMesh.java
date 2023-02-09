package ca.mcmaster.cas.se2aa4.a2.generator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

public class RegularMesh extends MeshADT{
    int squareSize;
    CustomVertex[][] centroidVertice;
    CustomVertex[][] vertice;
    List<Vertex> vertices;

    public RegularMesh(int width, int height, int precision, int squareSize)
    {
        super(width, height, precision);
        this.squareSize = squareSize;
        vertices = new ArrayList<>();
        createCentroidVertices();
        createConnectingVertices();
    }

    public void createCentroidVertices(){
        //Centroid vertices
        centroidVertice = new CustomVertex[width/20][height/ 20];
        
        for(int x = 0 ; x*squareSize + squareSize/2 < width; x++) {
            for(int y = 0 ; y*squareSize + squareSize/2 < height; y ++) {
                double xPos = x*squareSize + squareSize/2;
                double yPos = y*squareSize + squareSize/2;
                CustomVertex new_v= new CustomVertex(xPos, yPos ,new Color(254,0,0,254), "2.0");
                centroidVertice[x][y]=new_v;
                addVertex(new_v.getVertex());
            }
        }
    }

    public void createConnectingVertices()
    {
        //Connecting vertices
        vertice = new CustomVertex[width][height];

        for(int x = 0; x <= width; x += squareSize) {
            for(int y = 0; y <= height; y += squareSize) {
                CustomVertex new_v= new CustomVertex(x,y);
                vertice[x/20][y/20]=new_v;
                addVertex(new_v.getVertex());
            }
        }
    }

    public CustomVertex[][] getCentroidVertices(){
        return centroidVertice;
    }

    public CustomVertex getConnectingVertexs(int x, int y){
        return vertice[x][y];
    }

    public List<Vertex> getVertices(){
        return vertices;
    }

    public void addVertex(Vertex vertex)
    {
        vertices.add(vertex);
    }
    
}
