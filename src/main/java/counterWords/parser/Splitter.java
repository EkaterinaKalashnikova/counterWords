package counterWords.parser;

import java.util.Map;

public interface Splitter {
    Map<String, Integer> splitString(String s);
}
