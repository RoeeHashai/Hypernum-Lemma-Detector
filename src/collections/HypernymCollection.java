package collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HypernymCollection stores hypernyms and their counts.
 */
public class HypernymCollection {
    private Map<String, Integer> map;

    /**
     * Constructs an empty HypernymCollection.
     */
    public HypernymCollection() {
        map = new HashMap<>();
    }

    /**
     * Adds hypernyms and their counts from another map to this collection.
     *
     * @param otherMap the map containing hypernyms and counts to be added
     */
    public void add(Map<String, Integer> otherMap) {
        if (map.isEmpty()) {
            map.putAll(otherMap);
            return;
        }
        for (String hypernym : otherMap.keySet()) {
            if (map.containsKey(hypernym)) {
                int count = map.get(hypernym);
                count += otherMap.get(hypernym);
                map.put(hypernym, count);
            } else {
                map.put(hypernym, otherMap.get(hypernym));
            }
        }
    }

    /**
     * Prints the hypernyms and their counts in a sorted order.
     */
    public void print() {
        if (!map.isEmpty()) {
            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(map.entrySet());
            sortedEntries.sort(Map.Entry.<String, Integer>comparingByValue().reversed()
                    .thenComparing(Map.Entry.comparingByKey()));
            for (Map.Entry<String, Integer> entry : sortedEntries) {
                System.out.println(entry.getKey() + ": (" + entry.getValue() + ")");
            }
        } else {
            System.out.println("The lemma doesn't appear in the corpus.");
        }
    }
}
