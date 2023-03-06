package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public abstract class Mode {
    String inputMesh;
    String outputMesh;
    ShapeType shape;
    public Mode(String inputMesh, String outputMesh, ShapeType shape){
        this.inputMesh = inputMesh;
        this.outputMesh = outputMesh;
        this.shape = shape;
    }
    public abstract Mesh getMesh();
}
