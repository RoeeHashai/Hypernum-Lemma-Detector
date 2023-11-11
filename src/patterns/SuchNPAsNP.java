package patterns;

/**
 * A pattern for finding hypernyms using the "SuchNPAsNP" pattern.
 */
public class SuchNPAsNP extends AbstractPattern {
    /**
     * Constructs a SuchNPAsNP pattern with the specified lemma.
     *
     * @param lemma the lemma representing the hyponym for which to find hypernyms
     */
    public SuchNPAsNP(String lemma) {
        super(lemma);
    }

    @Override
    protected String getRegex() {
        return "such <np>([^<]*)<\\/np> (, )? as <np>([^<]*)<\\/np>"
                + "( (, <np>([^<]*)<\\/np> )*)?((, )?(and |or )?<np>([^<]*)<\\/np>)?";
    }
}
