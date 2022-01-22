package counterwords.parser;

import org.apache.log4j.LogManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class TextPageParser implements ParserPage {
    private Map<String, Integer> result = new HashMap<>();
    private static final Logger LOG = Logger.getLogger(TextPageParser.class.getName());
    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(TextPageParser.class.getName());

    @Override
    public Map<String, Integer> parse(String url) throws IOException {
        LOG.info("Parse url");
        LOGGER.debug("All work!");
        Document document = Jsoup.connect(url).get();
        Element body = document.getElementsByTag("body").first();
        SplitterImpl splitter = new SplitterImpl();
        String s = String.valueOf(body);
        String str = s.replaceAll("(?s)<!--.+?-->|<script.+?</script>|<.+?>", "")
                .replaceAll("&nbsp;| +", " ")
                .replaceAll("\r\n ", "\r\n")
                .replaceAll("[\r\n\t]+", "\r\n")
                .replaceAll("\\n", "");
        if (!str.equals("")) {
            result.putAll(splitter.splitString(str));
        }
        return result;
    }
}
