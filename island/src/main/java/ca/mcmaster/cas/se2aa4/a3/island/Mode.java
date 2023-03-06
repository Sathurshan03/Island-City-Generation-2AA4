package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public abstract class Mode {
    String inputMesh;
    String outputMesh;
    public Mode(String inputMesh, String outputMesh){
        this.inputMesh = inputMesh;
        this.outputMesh = outputMesh;
    }
    public abstract Mesh getMesh();
}
