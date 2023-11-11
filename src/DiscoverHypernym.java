import process.ProcessFlow;
import input.InputValidator;


/**
 * A class that discovers hypernyms based on input arguments.
 */
public class DiscoverHypernym {
    /**
     * The main method that starts the hypernym discovery process.
     *
     * @param args the command-line arguments containing the folder path and the lemma
     */
    public static void main(String[] args) {
        InputValidator inputValidator = new InputValidator(args);
        if (inputValidator.isValid()) {
            ProcessFlow process = new ProcessFlow(args[0], args[1]);
            process.processData();
        }
    }
}