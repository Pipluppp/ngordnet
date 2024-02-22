package ngordnet.main;

import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.*;

public class WordNet {
    Graph graph;
    String synsetFile;
    String hyponymFile;

    WordNet(String synsetFile, String hyponymFile) {
        this.synsetFile = synsetFile;
        this.hyponymFile = hyponymFile;
        this.graph = new Graph(synsetFile, hyponymFile);
    }

    public List<String> getHyponyms(List<String> words) {
        Set<String> hyponyms = graph.getHyponyms(words);
        return graph.sortHyponyms(hyponyms);
    }

    // Hyponyms with k != 0
    public List<String> getHyponymsNGrams(List<String> words, Integer starYear, Integer endYear, Integer k, NGramMap ngm) {
        HashSet<String> setOfHyponyms = graph.getHyponyms(words);
        Map<String, Double> hyponymFrequency = new TreeMap<>();
        HashSet<String> temp = new HashSet<>();

        // Load words into map with its NGramMap sum of frequency
        for (String word: setOfHyponyms) {
            TimeSeries wordTS = ngm.countHistory(word, starYear, endYear);

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
        for (int i = 0; i < k; i++) {
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
            // Update set of hyponyms
            setOfHyponyms = temp;
        }

        return sortHyponyms(setOfHyponyms);
    }

    public List<String> sortHyponyms(Set<String> hyponyms) {
        return graph.sortHyponyms(hyponyms);
    }
}
