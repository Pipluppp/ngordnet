package ngordnet.main;

import ngordnet.browser.NgordnetServer;
import ngordnet.ngrams.NGramMap;

public class Main {
    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();
        String wordFile = "./data/ngrams/top_14377_words.csv";
        String countFile = "./data/ngrams/total_counts.csv";

        String synsetFile = "./data/wordnet/synsets.txt";
        String hyponymFile = "./data/wordnet/hyponyms.txt";

        Graph graph = new Graph(synsetFile, hyponymFile);
        NGramMap ngram = new NGramMap(wordFile, countFile);
        // NGramMap ngm = new NGramMap(wordFile, countFile);

        hns.startUp();
        hns.register("history", new HistoryHandler(ngram));
        hns.register("historytext", new HistoryTextHandler(ngram));
        hns.register("hyponyms", new HyponymsHandler(graph, ngram));
    }
}
