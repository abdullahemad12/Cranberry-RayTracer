package exceptions;
/**
 * The UnknownFileExtensionException is thrown when the input file to the program
 * has an extension different than .cbg
 * @author Abdullah Emad
 * @version 1.0
 */
public class UnknownFileExtensionException extends CranberryException {
    /**
     * the filename of the input file
     */
    private String filename;

    /**
     *
     * @param filename the filename of the input file
     */
    public UnknownFileExtensionException(String filename) {
        super(filename + " is of unknown extension! Supported extension: *.cbg", 6);
        this.filename = filename;
    }
}
