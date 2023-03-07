package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.tools.CommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.ModeType;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;

import org.apache.commons.cli.*;

import java.io.IOException;

public class IslandCommandLineReader implements CommandLineReader {
    String inputMesh;
    String outputMesh;
    String mode;
    String shape;

    String elevation;
    ModeType mapMode;

    AltitudeType altitude;
    ShapeType shapeToUse;
    private Options options;

    public IslandCommandLineReader(String[] args) throws IOException, ParseException {
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
        options.addOption(new Option("s", "shape", true, "Island Shape"));
        options.addOption(new Option("a", "altitude", true, "Island Elevation"));

    }

    public void checkOptions(String[] args) throws ParseException, IOException{
        //check all the options from the command line to figure what information to extra or display
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        //extract the values from each option
        inputMesh = cmd.getOptionValue("inputMesh");
        outputMesh = cmd.getOptionValue("outputMesh");
        mode = cmd.getOptionValue("mode");
        shape = cmd.getOptionValue("shape");
        elevation=cmd.getOptionValue("altitude");




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

        for(ShapeType s: ShapeType.values()){
            String shapeString = s.toString();
            if (shapeString.equals(shape))
            {
                shapeToUse = s;
                break;
            }
        }

        for(AltitudeType a: AltitudeType.values()){
            String altitudeString = a.toString();
            if (altitudeString.equals(elevation))
            {
                altitude = a;
                break;
            }
        }

    }

    public String getInputMesh(){
        return inputMesh;
    }
    public String getOutputMesh(){
        return outputMesh;
    }
    public ShapeType getShapeType(){
        return shapeToUse;
    }

    public AltitudeType getAltitudeType(){
        return altitude;
    }

    public boolean isSandBoxMode(){
        if (mapMode.equals(ModeType.SANDBOX)){
            return true;
        }
        return false;
    }
    public boolean isRegularMode(){
        if (mapMode.equals(ModeType.REGULAR)){
            return true;
        }
        return false;
    }
}
