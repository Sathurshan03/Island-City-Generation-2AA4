package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.GeneralBiome;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Heatmaps;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;
import ca.mcmaster.cas.se2aa4.a3.tools.CommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.tools.RandomGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.ModeType;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Regular;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Sandbox;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;

import org.apache.commons.cli.*;

import java.io.IOException;

public class IslandCommandLineReader implements CommandLineReader {
    private String inputMeshFile;
    private String outputMeshFile;
    private String mode;
    private String shape;
    private String seed;
    private String river;
    private String maxLakes;
    private String aquifer;
    private String elevation;
    private String biome;

    private String soil;
    private ModeType mapMode;
    private AltitudeType altitude;
    private ShapeType shapeToUse;
    private BiomeTypes generalBiome;

    private SoilTypes generalSoil;
    private int maxNumLakes;
    private int maxNumRivers;
    private int numAquifers;
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
        options.addOption(new Option("b", "biomes", true, "Biome type"));
        options.addOption(new Option("se", "seed", true, "Map seed (Long)"));
        options.addOption(new Option("r", "rivers", true, "Maximum number of rivers to generate (Integer)"));
        options.addOption(new Option("l", "lakes", true, "Maximum number of lakes"));
        options.addOption(new Option("s", "soil", true, "Enter the soil profile"));
        options.addOption(new Option("aq", "aquifers", true, "Number of aquifers"));
        options.addOption(new Option("h", "help", false, ""));

    }

    public void checkOptions(String[] args) throws ParseException, IOException {
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
        biome = cmd.getOptionValue("biomes");
        river = cmd.getOptionValue("rivers");
        maxLakes = cmd.getOptionValue("lakes");
        soil=cmd.getOptionValue("soil");
        aquifer = cmd.getOptionValue("aquifers");

        //Help option
        if (cmd.hasOption("help")) {
            System.out.println("Create Island Mesh: java -jar island.jar -inputMesh -outputMesh -mode");
            System.out.println("Create Sandbox Island: java -jar island.jar -inputMesh -outputMesh -sandbox [-shape] [-altitude]");
            System.out.println("Create Regular Island: java -jar island.jar -inputMesh -outputMesh -regular [-shape] [-altitude] [-biomes] [-lakes] [-rivers] [-soil] [-aquifers] [-seed]");
            System.out.println("Create Heatmap: java -jar island.jar -inputMesh -outputMesh -heatmap [-shape] [-altitude] [-biomes] [-lakes] [-rivers] [-soil] [-aquifers] [-seed]");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("help", options);
            System.exit(0);
        }

        //Set the mode to run
        for (ModeType m : ModeType.values()) {
            String modeString = m.toString();
            if (modeString.equals(mode)) {
                mapMode = m;
                break;
            }
        }

        //Get the shape type
        for (ShapeType s : ShapeType.values()) {
            String shapeString = s.toString();
            if (shapeString.equals(shape)) {
                shapeToUse = s;
                break;
            }
        }

        //Get the altitude type
        for (AltitudeType a : AltitudeType.values()) {
            String altitudeString = a.toString();
            if (altitudeString.equals(elevation)) {
                altitude = a;
                break;
            }
        }

        for (BiomeTypes b : BiomeTypes.values()) {
            String biomeString = b.toString();
            if (biomeString.equals(biome)) {
                generalBiome = b;
                break;
            }
        }

        for (SoilTypes s : SoilTypes.values()) {
            String soilString = s.toString();
            if (soilString.equals(soil)) {
                generalSoil = s;
                break;
            }
        }
        //Set the maximum number of lakes
        if (cmd.hasOption("lakes")) {
            maxNumLakes = Integer.parseInt(maxLakes);
        } else {
            maxNumLakes = 0;
        }

        //Set the maximum number of rivers
        if (cmd.hasOption("rivers")) {
            maxNumRivers = Integer.parseInt(river);
        } else {
            maxNumRivers = 0;
        }

        //Set the number of aquifers
        if (cmd.hasOption("aquifers")) {
            numAquifers = Integer.parseInt(aquifer);
        } else {
            numAquifers = 0;
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

    protected boolean isHeatmapMode(){
        if (mapMode.equals(ModeType.HEATMAP)) {
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
                Regular regular = new Regular(inputMeshFile, outputMeshFile, shapeToUse, altitude, generalBiome, maxNumLakes, maxNumRivers, generalSoil, numAquifers);
                regular.generate();
                mesh = regular.getMesh();
            }else if (isHeatmapMode()){
                Heatmaps heatmap=new Heatmaps(inputMeshFile, outputMeshFile, shapeToUse, altitude, generalBiome,maxNumLakes, maxNumRivers,generalSoil, numAquifers);
                mesh=heatmap.getMesh();
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
