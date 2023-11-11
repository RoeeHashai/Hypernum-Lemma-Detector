package patterns;

import interfaces.HearstPatterns;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An abstract class representing a pattern for finding hypernyms in text data.
 */
public abstract class AbstractPattern implements HearstPatterns {
    private String data;
    private String lemma;
    private Map<String, Integer> hypernyms;

    /**
     * Constructs an AbstractPattern object with the specified lemma.
     *
     * @param lemma the lemma representing the hyponym for which to find hypernyms
     */
    AbstractPattern(String lemma) {
        this.lemma = lemma;
        this.data = "";
        hypernyms = new HashMap<>();
    }

    protected abstract String getRegex();

    @Override
    public void find() {
        String hypernym = "";
        String regex = getRegex();
        Pattern regexPattern = Pattern.compile(regex);
        Matcher matcher = regexPattern.matcher(data);
        while (matcher.find()) {
            String matchText = data.substring(matcher.start(), matcher.end());
            Pattern patternToSplit = Pattern.compile("<np>([^<]*)</np>");
            Matcher splitMatcher = patternToSplit.matcher(matchText);
            if (splitMatcher.find()) {
                hypernym = splitMatcher.group(1);
            }
            while (splitMatcher.find()) {
                String tempHyponym = splitMatcher.group(1);
                if (tempHyponym.equals(lemma)) {
                    if (hypernyms.containsKey(hypernym)) {
                        int count = hypernyms.get(hypernym);
                        count++;
                        hypernyms.put(hypernym, count);
                    } else {
                        hypernyms.put(hypernym, 1);
                    }
                }
            }
        }
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public Map<String, Integer> getHypernyms() {
        return hypernyms;
    }

    protected String getLemma() {
        return lemma;
    }

    protected String getData() {
        return data;
    }
}
