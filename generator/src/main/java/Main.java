import ca.mcmaster.cas.se2aa4.a2.generator.Generator;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;
import ca.mcmaster.cas.se2aa4.a2.generator.MeshType;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Options options = new Options();
        options.addOption(new Option("g", "grid", false, "Generate Grid Mesh"));
        options.addOption(new Option("i", "irregular", false, "Generate Irregular Mesh"));
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
        MeshType meshType;

        if(cmd.hasOption("-help")) {
            //ADD MORE INSTRUCTIONS HERE TO EXPLAIN HOW TO USE COMMAND LINE ARGUMENTS
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("help", options);
            return;
        }
        
        //checks if valid input for generating grid or irregular mesh
        if(cmd.hasOption("grid") && cmd.hasOption("irregular")) {
            throw new IOException("Can not generate both grid and irregular mesh");
        }
        else if (cmd.hasOption("grid")) {
            meshType = MeshType.GRID;
        }
        else if (cmd.hasOption("irregular")) {
            meshType = MeshType.IRREGULAR;
        }
        else{
            throw new IOException("Need to generate either grid or irregular mesh");
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
        

        //NEED TO STILL USE relationLevel

        Generator generator = new Generator();
        Mesh myMesh = generator.generate(numberPolygons, canvasWidth, canvasHeight, meshType, relationLevel);
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
    }

}
