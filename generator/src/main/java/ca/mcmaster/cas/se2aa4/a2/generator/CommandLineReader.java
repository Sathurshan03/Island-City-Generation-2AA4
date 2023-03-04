package ca.mcmaster.cas.se2aa4.a2.generator;

import org.apache.commons.cli.*;

import java.io.IOException;

public interface CommandLineReader {
    public  void createOptions();

    public  void checkOptions(String[] args)throws ParseException, IOException;
    
}
