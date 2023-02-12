package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class Generator {

    private final int width = 500;
    private final int height = 500;
    private final int squareSize = 20;

    public Mesh generate() {

        RegularMesh mesh = new RegularMesh(width, height, 2, squareSize);

        return Mesh.newBuilder().addAllPolygons(mesh.getMesh()).addAllSegments(mesh.getSegments()).addAllVertices(mesh.getVertices()).addAllVertices(mesh.centroids).build();
    }

}
