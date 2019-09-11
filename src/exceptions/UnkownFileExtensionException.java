package exceptions;

public class UnkownFileExtensionException extends Exception {
    public UnkownFileExtensionException() {
        super("The file you are trying to parse is of unknown extension! Supported extension: *.cbg");
    }
}
