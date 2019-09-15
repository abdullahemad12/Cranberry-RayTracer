package exceptions;

public class UnkownFileExtensionException extends CranberryException {
    private String filename;
    public UnkownFileExtensionException(String filename) {
        super(filename + " is of unknown extension! Supported extension: *.cbg", 6);
        this.filename = filename;
    }
}
