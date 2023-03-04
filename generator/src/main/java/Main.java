import ca.mcmaster.cas.se2aa4.a2.generator.CommandLineReaderGenerator;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        CommandLineReaderGenerator commandReader = new CommandLineReaderGenerator(args);
        Mesh myMesh = commandReader.createMesh();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, commandReader.getFileName());
    }
}
