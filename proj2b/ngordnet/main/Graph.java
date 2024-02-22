package ngordnet.main;

import edu.princeton.cs.algs4.In;

import java.lang.reflect.Array;
import java.util.*;

public class Graph {
    // Node class
    public static class Synset {
        public Integer id;
        public ArrayList<String> words = new ArrayList<>();
        public ArrayList<Synset> children = new ArrayList<>();

        public Synset(Integer id, String synsetWords) {
            this.id = id;
            this.words.addAll(Arrays.asList(synsetWords.split(" ")));
        }
    }

    ArrayList<Synset> synsets = new ArrayList<>();

    public Graph(String synsetFile, String hyponymsFile) {
        // Get Synsets
        In inSynset = new In(synsetFile);
        while(inSynset.hasNextLine()){
            String[] line = inSynset.readLine().split(",");

            Integer synsetID = Integer.parseInt(line[0]);
            String synsetWords = line[1];
            synsets.add(new Synset(synsetID, synsetWords));
        }

        // Form relationship between Synsets
        In inHyponyms = new In(hyponymsFile);
        while (inHyponyms.hasNextLine()) {
            String[] line = inHyponyms.readLine().split(",");
            Integer parentID = Integer.parseInt(line[0]);

            Synset parent = synsets.get(parentID);
            for (int i = 1, len = line.length; i < len; i++) {
                Integer childSynsetID = Integer.parseInt(line[i]);
                Synset child = synsets.get(childSynsetID);
                parent.children.add(child);
            }
        }
    }

    public void printSynset() {
        for (Synset x: synsets) {
            System.out.println(x.id + ", " + x.words);
        }
    }

    public void printHyponym() {
        for (Synset x: synsets) {
            System.out.print(x.id + ": " + x.words + " has children: ");
            for (Synset child: x.children) {
                System.out.print("" + child.words);
            }
            System.out.println();
        }
    }

    // Given a word, find its hyponyms (sorted)
    public HashSet<String> getHyponyms(String word) {
        HashSet<Synset> synsetsToTraverse = new HashSet<>();
        HashSet<String> setOfHyponyms = new HashSet<>();

        // Identify synsets containing the word
        for (Synset x: synsets) {
            if (x.words.contains(word)) {
                synsetsToTraverse.add(x);
            }
        }

        // Traverse synsets
        for (Synset x: synsetsToTraverse) {
            traverse(x, setOfHyponyms);
        }
        return setOfHyponyms;
    }

    public HashSet<String> getHyponyms(List<String> words) {
        HashSet<String> hyponymsIntersection = getHyponyms(words.get(0));
        for (String word: words) {
            hyponymsIntersection.retainAll(getHyponyms(word));
        }

        return hyponymsIntersection;
    }

    public List<String> sortHyponyms(Set<String> hyponyms) {
        List<String> sorted = new ArrayList<>(hyponyms);
        Collections.sort(sorted);
        return sorted;
    }

    public void traverse(Synset node, HashSet<String> allHyponyms) {
        allHyponyms.addAll(node.words);

        for (Synset x: node.children) {
            traverse(x, allHyponyms);
        }
    }
}
