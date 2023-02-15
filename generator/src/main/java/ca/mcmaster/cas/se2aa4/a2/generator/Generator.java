package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.io.IOException;
import java.util.List;
import org.locationtech.jts.geom.Coordinate;

public class Generator {

    private final int squareSize = 20;

    public Mesh generate(int numberPolygons, int width, int height, MeshType meshType) throws IOException{

        if (meshType.equals(MeshType.GRID)){
            RegularMesh mesh = new RegularMesh(width, height, 2, squareSize); 
            return Mesh.newBuilder().addAllPolygons(mesh.getMesh()).addAllSegments(mesh.getSegments()).addAllVertices(mesh.getCentroids()).addAllVertices(mesh.getVertices()).build();
        }
        else if (meshType.equals(MeshType.IRREGULAR)){
            IrregularMesh mesh = new IrregularMesh(width, height, 2, numberPolygons);
            List<Coordinate> ya= mesh.getCoordinates();
            return Mesh.newBuilder().addAllPolygons(mesh.getMesh()).addAllSegments(mesh.getSegments()).addAllVertices(mesh.getCentroids()).addAllVertices(mesh.getVertices()).build();
        }
        else{
            throw new IOException("No type of mesh determine. Can not generate a mesh");
        }

    }

}
