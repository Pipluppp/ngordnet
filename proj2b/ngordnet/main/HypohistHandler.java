package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;
import ngordnet.plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class HypohistHandler extends NgordnetQueryHandler {
    WordNet net;
    NGramMap map;

    HypohistHandler(WordNet net, NGramMap map) {
        this.net = net;
        this.map = map;
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

        // NGramMap portion of plotting
        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> hyponymWords = new ArrayList<>();

        for (String word: hyponyms) {
            hyponymWords.add(word);
            lts.add(map.weightHistory(word, q.startYear(), q.endYear()));
        }

        XYChart chart = Plotter.generateTimeSeriesChart(hyponyms, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);

        return encodedImage;
    }
}
