import ca.mcmaster.cas.se2aa4.a2.generator.Generator;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Options options = new Options();
        options.addOption("numPoly", true, "Number of Polygons");
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("help", options);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String numPoly = cmd.getOptionValue("numPoly");
        int numberPolygons;
        if(numPoly == null){
            //default is 100
            numberPolygons = 100;
        }
        else{
            numberPolygons = Integer.parseInt(numPoly);
        }
        


        Generator generator = new Generator();
        Mesh myMesh = generator.generate();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
    }

}
