package input;

import java.io.File;

/**
 * The InputValidator class is responsible for validating the input arguments provided to the program.
 */
public class InputValidator {
    private String[] args;

    /**
     * Constructs an InputValidator object with the specified arguments.
     *
     * @param args the input arguments to validate
     */
    public InputValidator(String[] args) {
        this.args = args;
    }

    /**
     * Checks whether the provided input arguments are valid.
     *
     * @return true if the input arguments are valid, false otherwise
     */
    public boolean isValid() {
        if (args.length < 2) {
            return false;
        }
        File directory = new File(args[0]);
        if (!directory.exists() || !directory.isDirectory()) {
            return false;
        }
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            return false;
        }
        return true;
    }
}
