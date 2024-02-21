package ngordnet.main;

import edu.princeton.cs.algs4.In;

import java.lang.reflect.Array;
import java.util.*;

public class Graph {
    // Node class
    public static class Synset {
        public Integer id;
        public HashSet<String> words = new HashSet<>();
        public HashSet<Synset> children = new HashSet<>();

        public Synset(Integer id, String synsetWords) {
            this.id = id;
            this.words.addAll(Arrays.asList(synsetWords.split(" ")));
        }
    }

    HashSet<Synset> synsets = new HashSet<>();

    public Graph(String synsetFile) {
        In in = new In(synsetFile);

        while(in.hasNextLine()){
            String[] line = in.readLine().split(",");

            Integer synsetID = Integer.parseInt(line[0]);
            String synsetWords = line[1];
            synsets.add(new Synset(synsetID, synsetWords));
        }
    }

    public void printSynset() {
        for (Synset x: synsets) {
            System.out.println(x.id + ", " + x.words);
        }
    }
}
