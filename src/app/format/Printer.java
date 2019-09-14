package app.format;

public class Printer {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void printError(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    public static void printWarning(String message){
        System.out.println(ANSI_YELLOW + message + ANSI_RESET);
    }

    public static void printSuccess(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_RESET);
    }
    public static void println(String message){
        System.out.println(message);
    }

}
