import ca.mcmaster.cas.se2aa4.a2.generator.Generator;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Options options = new Options();
        options.addOption(new Option("np", "numPoly", true, "Number of Polygons"));
        options.addOption(new Option("wth", "width", true, "Width of Canvas"));
        options.addOption(new Option("hth", "height", true, "Height of Canvas"));
        options.addOption(new Option("rl", "relation", true, "Relaxation level for Llyod relaxation"));
        options.addOption(new Option("h", "help", false, "Available Options and Command Line Arguments"));
        
        
        //default values
        int numberPolygons = 0;
        int canvasWidth = 500;
        int canvasHeight = 500;
        int relationLevel = 0;
        
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String numPoly = cmd.getOptionValue("numPoly");
        String width = cmd.getOptionValue("width");
        String height = cmd.getOptionValue("height");
        String relation = cmd.getOptionValue("relation");

        if(cmd.hasOption("-help")) {
            //ADD MORE INSTRUCTIONS HERE TO EXPLAIN HOW TO USE COMMAND LINE ARGUMENTS
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("help", options);
            return;
        }

        if(numPoly != null){
            numberPolygons = Integer.parseInt(numPoly);
        }
        if(width != null){
            canvasWidth = Integer.parseInt(width);
        }
        if(height != null){
            canvasHeight = Integer.parseInt(height);
        }
        if(relation != null){
            relationLevel = Integer.parseInt(relation);
        }
        
        System.out.println(numberPolygons);
        System.out.println(canvasWidth);
        System.out.println(canvasHeight);
        System.out.println(relationLevel);


        Generator generator = new Generator();
        Mesh myMesh = generator.generate(numberPolygons, canvasWidth, canvasHeight);
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
    }

}
