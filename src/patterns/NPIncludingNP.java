package patterns;

/**
 * A pattern for finding hypernyms using the "NPIncludingNP" pattern.
 */
public class NPIncludingNP extends AbstractPattern {
    /**
     * Constructs an NPIncludingNP pattern with the specified lemma.
     *
     * @param lemma the lemma representing the hyponym for which to find hypernyms
     */
    public NPIncludingNP(String lemma) {
        super(lemma);
    }

    @Override
    protected String getRegex() {
        return "<np>([^<]*)<\\/np> (, )?including <np>([^<]*)<\\/np>"
                + "( (, <np>([^<]*)<\\/np> )*)?((, )?(and |or )?<np>([^<]*)<\\/np>)?";

    }
}
