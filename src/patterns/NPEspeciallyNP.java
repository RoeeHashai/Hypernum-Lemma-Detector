package patterns;

/**
 * A pattern for finding hypernyms using the "NPEspeciallyNP" pattern.
 */
public class NPEspeciallyNP extends AbstractPattern {
    /**
     * Constructs an NPEspeciallyNP pattern with the specified lemma.
     *
     * @param lemma the lemma representing the hyponym for which to find hypernyms
     */
    public NPEspeciallyNP(String lemma) {
        super(lemma);
    }

    @Override
    protected String getRegex() {
        return "<np>([^<]*)<\\/np> (, )?especially <np>([^<]*)<\\/np>"
                + "( (, <np>([^<]*)<\\/np> )*)?((, )?(and |or )?<np>([^<]*)<\\/np>)?";

    }
}
