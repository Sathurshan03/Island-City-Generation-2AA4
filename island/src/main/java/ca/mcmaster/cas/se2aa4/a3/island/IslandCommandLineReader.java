package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.tools.CommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.tools.RandomGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.ModeType;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Regular;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Sandbox;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;

import org.apache.commons.cli.*;

import java.io.IOException;

public class IslandCommandLineReader implements CommandLineReader {
    String inputMeshFile;
    String outputMeshFile;
    String mode;
    String shape;
    String seed;
    String maxLakes;

    String elevation;
    ModeType mapMode;

    AltitudeType altitude;
    ShapeType shapeToUse;
    private Options options;

    public static RandomGenerator randomGenerator;

    public IslandCommandLineReader(String[] args) throws IOException, ParseException {
        super();
        options = new Options();
        createOptions();
        checkOptions(args);

        //Set the random generator class 
        if (seed == null){
            //Create new seed
            randomGenerator = new RandomGenerator();
        }
        else{
            //use the inputted seed
            randomGenerator = new RandomGenerator(Long.parseLong(seed));
        }
    }
    public void createOptions(){
        //Creates all the options for the command line
        options.addOption(new Option("i", "inputMesh", true, "Input Mesh"));
        options.addOption(new Option("o", "outputMesh", true, "Output Mesh"));
        options.addOption(new Option("m", "mode", true, "Map Mode"));
        options.addOption(new Option("sh", "shape", true, "Island Shape"));
        options.addOption(new Option("a", "altitude", true, "Island Elevation"));
        options.addOption(new Option("se", "seed", true, "Map seed"));
        options.addOption(new Option("l", "lakes", true, "Maximum number of lakes"));

    }

    public void checkOptions(String[] args) throws ParseException, IOException{
        //check all the options from the command line to figure what information to extra or display
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        //extract the values from each option
        inputMeshFile = cmd.getOptionValue("inputMesh");
        outputMeshFile = cmd.getOptionValue("outputMesh");
        mode = cmd.getOptionValue("mode");
        shape = cmd.getOptionValue("shape");
        elevation = cmd.getOptionValue("altitude");
        seed = cmd.getOptionValue("seed");
        maxLakes = cmd.getOptionValue("lakes");

        //Help option
        if(cmd.hasOption("help")) {
            System.out.println("Create Island Mesh: java -jar island.jar -inputMesh -outputMesh -mode");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("help", options);
            System.exit(0);
        }

        //Set the mode to run
        for(ModeType m: ModeType.values()){
            String modeString = m.toString();
            if (modeString.equals(mode))
            {
                mapMode = m;
                break;
            }
        }

        //Get the shape type
        for(ShapeType s: ShapeType.values()){
            String shapeString = s.toString();
            if (shapeString.equals(shape))
            {
                shapeToUse = s;
                break;
            }
        }

        //Get the altitude type
        for(AltitudeType a: AltitudeType.values()){
            String altitudeString = a.toString();
            if (altitudeString.equals(elevation))
            {
                altitude = a;
                break;
            }
        }
    }
    
    public String getOutputMeshFile(){
        return outputMeshFile;
    }

    protected boolean isSandBoxMode(){
        if (mapMode.equals(ModeType.SANDBOX)){
            return true;
        }
        return false;
    }
    protected boolean isRegularMode(){
        if (mapMode.equals(ModeType.REGULAR)){
            return true;
        }
        return false;
    }
    public Mesh generateFromInputs() throws IOException{
        RunMode runMode = new RunMode();
        return runMode.getMesh();
    }

    private class RunMode{
        Mesh mesh = null;
        public RunMode() throws IOException{   
            if (isSandBoxMode()){
                Sandbox sandbox = new Sandbox(inputMeshFile, outputMeshFile);
                sandbox.generate();
                mesh = sandbox.getMesh();
            }
            else if (isRegularMode()){
                Regular regular = new Regular(inputMeshFile, outputMeshFile, shapeToUse, altitude);
                regular.generate();
                mesh = regular.getMesh();
            }
            else{
                throw new IOException("Invalid mode was entered");
            }
        }
        public Mesh getMesh(){
            return mesh;
        }
    }
}
