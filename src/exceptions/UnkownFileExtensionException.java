package exceptions;

public class UnkownFileExtensionException extends Exception {
    private String filename;
    public UnkownFileExtensionException(String filename) {
        super(filename + " is of unknown extension! Supported extension: *.cbg");
        this.filename = filename;
    }
}
