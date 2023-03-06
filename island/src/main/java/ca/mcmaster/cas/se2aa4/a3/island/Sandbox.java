package ca.mcmaster.cas.se2aa4.a3.island;
import java.io.IOException;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;

public class Sandbox extends Mode{
    
    public Sandbox(String inputMesh, String outputMesh) throws IOException{
        super(inputMesh, outputMesh, ShapeType.CIRCLE);

         //extract all the info from the input mesh
         extractInformation();
    }

    public void generate(){
        //generate a map following the sandbox requirements
        Circle cricle = new Circle(width, height, tiles);

    }

    public Mesh getMesh(){
        return null;
    }
    
}
