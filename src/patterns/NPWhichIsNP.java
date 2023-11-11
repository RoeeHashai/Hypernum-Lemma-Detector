package patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A pattern for finding hypernyms using the "NPWhichIsNP" pattern.
 */
public class NPWhichIsNP extends AbstractPattern {
    /**
     * Constructs an NPWhichIsNP pattern with the specified lemma.
     *
     * @param lemma the lemma representing the hyponym for which to find hypernyms
     */
    public NPWhichIsNP(String lemma) {
        super(lemma);
    }

    @Override
    protected String getRegex() {
        return "<np>" + getLemma() + "<\\/np> (, )?which is ((an example|a kind|a class) of )?<np>([^<]*)<\\/np>";
    }

    @Override
    public void find() {
        String hypernym = "";
        String regex = getRegex();
        Pattern regexPattern = Pattern.compile(regex);
        Matcher matcher = regexPattern.matcher(getData());
        while (matcher.find()) {
            hypernym = matcher.group(matcher.groupCount());
            if (super.getHypernyms().containsKey(hypernym)) {
                int count = super.getHypernyms().get(hypernym);
                count++;
                getHypernyms().put(hypernym, count);
            } else {
                getHypernyms().put(hypernym, 1);
            }
        }
    }
}

