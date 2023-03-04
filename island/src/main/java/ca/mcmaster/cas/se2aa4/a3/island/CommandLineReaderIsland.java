package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.generator.CommandLineReader;
import org.apache.commons.cli.*;

import java.io.IOException;

public class CommandLineReaderIsland implements CommandLineReader {
    String inputMesh;
    String outputMesh;
    String mode;
    private Options options;

    public CommandLineReaderIsland(String[] args) throws IOException, ParseException {
        super();
        options = new Options();
        createOptions();
        checkOptions(args);
    }
    public void createOptions(){
        //Creates all the options for the command line
        options.addOption(new Option("i", "inputMesh", true, "Input Mesh"));
        options.addOption(new Option("o", "outputMesh", true, "Output Mesh"));
        options.addOption(new Option("m", "mode", true, "Map Mode"));
    }

    public void checkOptions(String[] args) throws ParseException, IOException{
        //check all the options from the command line to figure what information to extra or display
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        //extract the values from each option
        inputMesh = cmd.getOptionValue("inputMesh");
        outputMesh = cmd.getOptionValue("outputMesh");
        mode = cmd.getOptionValue("mode");


        //Help option
        if(cmd.hasOption("help")) {
            System.out.println("Create Island Mesh: java -jar island.jar -inputMesh -outputMesh -mode");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("help", options);
            System.exit(0);
        }

    }

//    public Structs.Mesh createMesh() throws IOException
//    {
//        //Generate the mesh based on the inputs from the command line
//        Generator generator = new Generator();
//        return generator.generate(numberPolygons, canvasWidth, canvasHeight, meshType, relationLevel, gridSpacing);
//    }

    public String getInputMesh(){
        return inputMesh;
    }
    public String getOutputMesh(){
        return outputMesh;
    }
    public String getMode(){
        return mode;
    }
}
