package ngordnet.main;

import org.junit.jupiter.api.Test;

public class TestGraph {
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String MEDIUM_SYNSET_FILE = "data/wordnet/synsets1000-subgraph.txt";
    public static final String MEDIUM_HYPONYM_FILE = "data/wordnet/hyponyms1000-subgraph.txt";

    @Test
    public void testSynset() {
        Graph dag = new Graph(MEDIUM_SYNSET_FILE, MEDIUM_HYPONYM_FILE);
        dag.printSynset();
        dag.printHyponym();
    }
}
