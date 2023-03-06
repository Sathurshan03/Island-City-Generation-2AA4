import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.Sandbox;

import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        IslandCommandLineReader commandReader = new IslandCommandLineReader(args);

        Mesh mesh = null;
        if (commandReader.isSandBoxMode()){
            Sandbox sandbox = new Sandbox(commandReader.getInputMesh(), commandReader.getOutputMesh());
            sandbox.generate();
            mesh = sandbox.getMesh();
        }
        else{
            throw new IOException("Invalid mode was entered");
        }

        MeshFactory factory = new MeshFactory();
        factory.write(mesh, commandReader.getOutputMesh());
    }
}
