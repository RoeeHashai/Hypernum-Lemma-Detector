// 209853282 Roee Hashai
package process;

import collections.HypernymCollection;
import input.Reader;
import interfaces.HearstPatterns;
import patterns.NPSuchAsNP;
import patterns.NPIncludingNP;
import patterns.NPWhichIsNP;
import patterns.NPEspeciallyNP;
import patterns.SuchNPAsNP;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents the process flow for finding hypernyms in text data.
 * It takes a path to a folder containing data files and a lemma representing the hyponym.
 * It applies various Hearst patterns to the data and collects the hypernyms using a HypernymCollection.
 */
public class ProcessFlow {
    private String path;
    private String lemma;
    private HypernymCollection hypernymCollection;

    /**
     * Constructs a ProcessFlow with the specified path and lemma.
     *
     * @param path  the path to the folder containing the data files
     * @param lemma the lemma representing the hyponym for which to find hypernyms
     */
    public ProcessFlow(String path, String lemma) {
        this.path = path;
        this.lemma = lemma;
        hypernymCollection = new HypernymCollection();
    }

    /**
     * Processes the data by applying the Hearst patterns to find hypernyms.
     */
    public void processData() {
        List<HearstPatterns> hearstPatterns = buildPatterns();
        Reader reader = new Reader(path);
        while (!reader.shouldStop()) {
            String data = reader.getData();
            if (data != null) {
                if (data.contains("<np>" + lemma + "</np>")) {
                    for (HearstPatterns pattern : hearstPatterns) {
                        pattern.setData(data);
                        pattern.find();
                    }
                }
            }
        }
        for (HearstPatterns pattern : hearstPatterns) {
            hypernymCollection.add(pattern.getHypernyms());
        }
        hypernymCollection.print();

    }

    private List<HearstPatterns> buildPatterns() {
        List<HearstPatterns> hearstPatterns = new ArrayList<>();
        hearstPatterns.add(new NPEspeciallyNP(lemma));
        hearstPatterns.add(new NPIncludingNP(lemma));
        hearstPatterns.add(new NPSuchAsNP(lemma));
        hearstPatterns.add(new NPWhichIsNP(lemma));
        hearstPatterns.add(new SuchNPAsNP(lemma));
        return hearstPatterns;

    }
}
