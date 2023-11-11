package interfaces;

import java.util.Map;

/**
 * An interface for defining Hearst patterns to find hypernyms in text data.
 */
public interface HearstPatterns {
    /**
     * Finds hypernyms in the data.
     */
    void find();

    /**
     * Retrieves the map of hypernyms and their counts.
     *
     * @return the map of hypernyms and their counts
     */
    Map<String, Integer> getHypernyms();

    /**
     * Sets the data to be processed by the pattern.
     *
     * @param data the data to be processed
     */
    void setData(String data);
}
