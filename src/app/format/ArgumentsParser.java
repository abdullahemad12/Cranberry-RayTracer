package app.format;

import app.Environment;
import org.apache.commons.cli.*;

/**
 * This class is responsible for parsing the command line arguments
 * Disclaimer: part of the code used in this file is inspired by:
 * https://stackoverflow.com/a/367714
 * @author Abdullah Emad
 */
public class ArgumentsParser {

    private String inputFile = null;

    private Options options;
    private Option input;
    private CommandLineParser parser;
    private HelpFormatter helpFormatter;


    public ArgumentsParser() {
        options = new Options();
        Option input = new Option("i", "input", true, "Path/to/file.cbg");
        input.setRequired(true);
        options.addOption(input);

        parser = new DefaultParser();
        helpFormatter = new HelpFormatter();
        CommandLine cmd;

    }

    public void printHelpMessage(){
        helpFormatter.printHelp(Environment.APP_SHORT_NAME.getValue(), options);
    }

    public void parse(String[] args) throws ParseException{
        CommandLine cmd = parser.parse(options, args);

        this.inputFile = cmd.getOptionValue("input");
    }

    public String getInputFile(){
        return this.inputFile;
    }

}
