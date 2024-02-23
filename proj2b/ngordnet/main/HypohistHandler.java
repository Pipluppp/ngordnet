package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;

import java.util.List;

public class HypohistHandler extends NgordnetQueryHandler {
    WordNet net;
    NGramMap map;

    @Override
    public String handle(NgordnetQuery q) {
        return "hello i am hypohist";
    }
}
