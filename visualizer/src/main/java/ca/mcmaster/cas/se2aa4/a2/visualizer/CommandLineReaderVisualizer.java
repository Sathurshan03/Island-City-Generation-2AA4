package ca.mcmaster.cas.se2aa4.a2.visualizer;
import ca.mcmaster.cas.se2aa4.a2.generator.CommandLineReader;
import java.io.IOException;
import org.apache.commons.cli.*;

public class CommandLineReaderVisualizer implements CommandLineReader{
    private Boolean debug = false;
    private Options options;
    public CommandLineReaderVisualizer(String[] args)throws IOException, ParseException{
        super();
        options = new Options();
        createOptions();
        checkOptions(args);
    }

    public  void createOptions(){
        options.addOption(new Option("X", "debug", false, "Debug mode"));
    }

    public  void checkOptions(String[] args)throws ParseException, IOException{
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        if(cmd.hasOption("debug")) {
            debug = true;
        }
    }

    public boolean isDebug(){
       return debug; 
    }

    
}
