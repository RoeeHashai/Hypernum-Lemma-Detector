package patterns;

/**
 * A pattern for finding hypernyms using the "NPSuchAsNP" pattern.
 */
public class NPSuchAsNP extends AbstractPattern {

    /**
     * Constructs an NPSuchAsNP pattern with the specified lemma.
     *
     * @param lemma the lemma representing the hyponym for which to find hypernyms
     */
    public NPSuchAsNP(String lemma) {
        super(lemma);
    }

    @Override
    protected String getRegex() {
        return "<np>([^<]*)<\\/np> (, )?such as <np>([^<]*)<\\/np>"
                + "( (, <np>([^<]*)<\\/np> )*)?((, )?(and |or )?<np>([^<]*)<\\/np>)?";

    }
}
