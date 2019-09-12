package app;

import app.format.ArgumentsParser;
import app.format.Printer;
import controllers.scene.Scene;
import org.apache.commons.cli.ParseException;

public class Main {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args){

        ArgumentsParser argParser = new ArgumentsParser();

        try {
            argParser.parse(args);
        } catch (ParseException e) {
            Printer.printError(e.getMessage());
            argParser.printHelpMessage();
            System.exit(1);
        }

        String filepath = argParser.getInputFile();

        try{
            Scene scene = new Scene(filepath);
            scene.render();
        } catch (Exception e) {
            Printer.printError(e.getMessage());
            System.exit(2);
        }

        Printer.printSuccess("Photo produced Successfully!");
        System.exit(0);

    }
}
