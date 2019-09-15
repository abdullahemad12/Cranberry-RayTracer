package app;

import app.format.ArgumentsParser;
import app.format.Printer;
import controllers.scene.Scene;
import exceptions.CranberryException;
import org.apache.commons.cli.ParseException;

public class Main {
    private static final int SUCCESS = 0;
    public static void main(String[] args){

        ArgumentsParser argParser = new ArgumentsParser();

        try {
            argParser.parse(args);
        } catch (ParseException e) {
            Printer.printError(e.getMessage());
            argParser.printHelpMessage();
            System.exit(CranberryException.DEFAULT_EXIT_STATUES);
        }

        String filepath = argParser.getInputFile();

        try{
            Scene scene = new Scene(filepath);
            scene.render();
        } catch (CranberryException e) {
            Printer.printError(e.getMessage());
            System.exit(e.EXIT_STATUES);
        } catch (Exception e) {
            Printer.printError(e.getMessage());
            System.exit(CranberryException.DEFAULT_EXIT_STATUES);
        }

        Printer.printSuccess("Photo produced Successfully!");
        System.exit(SUCCESS);

    }
}
