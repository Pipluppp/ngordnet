package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    WordNet net;
    NGramMap map;

    public HyponymsHandler(WordNet net, NGramMap m) {
        this.net = net;
        this.map = m;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        Integer k = q.k();
        Integer startYear = q.startYear();
        Integer endYear = q.startYear();

        List<String> hyponyms;

        if (k == 0) {
            hyponyms = net.getHyponyms(words);
        }
        else {
            hyponyms = net.getHyponymsNGrams(words, startYear, endYear, k, map);
        }

        return hyponyms.toString();
    }
}
