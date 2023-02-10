package ca.mcmaster.cas.se2aa4.a2.generator;

import java.awt.*;
import java.util.*;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int squareSize = 20;

    public Mesh generate() {

        RegularMesh mesh = new RegularMesh(width, height, 2, squareSize);

        System.out.println(mesh.mesh.size());


        return Mesh.newBuilder().addAllPolygons(mesh.getMesh()).addAllSegments(mesh.segments).addAllVertices(mesh.getVertices()).addAllVertices(mesh.centroids).build();
    }

}
