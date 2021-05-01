package counterWords.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitterImpl implements Splitter {
    private static final Logger LOG =  Logger.getLogger(SplitterImpl.class.getName());
    private static Map<String, Integer> maps;

    public SplitterImpl() {
        maps = new HashMap<>();
    }

    @Override
    public Map<String, Integer> splitString(String s) {
        LOG.info("info");
        Pattern pattern1 = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS | Pattern.UNICODE_CASE);
        Matcher matcher = pattern1.matcher(s);
        while (matcher.find()) {
            //System.out.println(matcher.group());
            int count = maps.getOrDefault(matcher.group().toUpperCase(), 0);
            maps.put(matcher.group().toUpperCase(), count + 1);
            // System.out.println("Position: " + matcher.group() + "-" + matcher.start());
        }
        return maps;
    }
}
