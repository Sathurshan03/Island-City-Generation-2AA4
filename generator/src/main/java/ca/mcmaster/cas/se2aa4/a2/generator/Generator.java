package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.io.IOException;


public class Generator {

    private final int squareSize = 20;
    
    public Mesh generate(int numberPolygons, int width, int height, MeshType meshType, int relationLevel) throws IOException{

        if (meshType.equals(MeshType.GRID)){
            //GRID based mesh
            RegularMesh mesh = new RegularMesh(width, height, 2, squareSize); 
            return Mesh.newBuilder().addAllPolygons(mesh.getMesh()).addAllSegments(mesh.getSegments()).addAllVertices(mesh.getCentroids()).addAllVertices(mesh.getVertices()).build();
        }
        else if (meshType.equals(MeshType.IRREGULAR)){
            //Irregular mesh
            IrregularMesh mesh = new IrregularMesh(width, height, 2, numberPolygons, relationLevel);
            return Mesh.newBuilder().addAllPolygons(mesh.getMesh()).addAllSegments(mesh.getSegments()).addAllVertices(mesh.getCentroids()).addAllVertices(mesh.getVertices()).build();
        }
        else{
            throw new IOException("No type of mesh determine. Can not generate a mesh");
        }
    }

}
