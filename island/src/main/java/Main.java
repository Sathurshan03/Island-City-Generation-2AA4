import ca.mcmaster.cas.se2aa4.a3.island.CommandLineReaderIsland;
import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        CommandLineReaderIsland commandReader = new CommandLineReaderIsland(args);
    }
}
