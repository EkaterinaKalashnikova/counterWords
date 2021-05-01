package counterWords.parser;

import java.io.IOException;
import java.util.Map;

public interface  ParserPage {
    Map<String, Integer> parse(String url) throws IOException;
}
