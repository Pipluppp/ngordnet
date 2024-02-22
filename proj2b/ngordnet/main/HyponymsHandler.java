package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    Graph dag;
    NGramMap map;

    public HyponymsHandler(Graph g, NGramMap m) {
        this.dag = g;
        this.map = m;
    }

    @Override
    public String handle(NgordnetQuery q) {
        String response = "";
        List<String> words = q.words();
        HashSet<String> setOfHyponyms;

        // Find set of hyponyms
        if (words.size() == 1) {
            setOfHyponyms = dag.hyponyms(words.get(0));
        } else {
            String[] arrayWords = words.toArray(new String[0]);
            setOfHyponyms = dag.hyponyms(arrayWords);
        }

        // If k != 0
        Map<String, Double> hyponymFrequency = new TreeMap<>();
        HashSet<String> temp = new HashSet<>();
        if (q.k() != 0){
            // Load words into map with its NGramMap sum of frequency
            for (String word: setOfHyponyms) {
                TimeSeries wordTS = map.countHistory(word, q.startYear(), q.endYear());
                if (wordTS.isEmpty()) {
                    continue;
                }

                Double sum = 0.0;
                for (Map.Entry<Integer, Double> entry: wordTS.entrySet()) {
                    sum += entry.getValue();
                }
                if (sum != 0.0) {
                    hyponymFrequency.put(word, sum);
                }
            }

            // Store Top k frequencies and sort (but in ascending order, just index in reverse to get descending)
            ArrayList<Double> sortedSum = new ArrayList<>();
            for (Map.Entry<String, Double> entry: hyponymFrequency.entrySet()) {
                sortedSum.add(entry.getValue());
            }
            Collections.sort(sortedSum);

            // For the given top frequency, find the mapping in our TimeSeries (to get the word)
            for (int i = 0; i < q.k(); i++) {
                if (i == hyponymFrequency.size()) {
                    break;
                }

                // Search for the key (word) which has the value
                for (Map.Entry<String, Double> entry: hyponymFrequency.entrySet()) {
                    Long a = Math.round(entry.getValue());
                    Long b = Math.round(sortedSum.get(sortedSum.size() - i - 1));
                    if (a.equals(b)) {
                        temp.add(entry.getKey());
                    }
                }
            }
            // Update set of hyponyms
            setOfHyponyms = temp;
        }

        // Sort hyponyms
        List<String> sorted = dag.sortHyponyms(setOfHyponyms);
        response += sorted;
        return response;
    }
}
