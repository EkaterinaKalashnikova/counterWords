package counterWords.parser;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TextPageParserTest {

    @Test
    public void whenParseUrl() throws IOException {
        String str = "https://www.danceshopper.com/ballroom-latin-competition-dancewear";
        TextPageParser tpp = new TextPageParser();
        Map<String, Integer> rsl = tpp.parse(str);
        List<String> fileList = new ArrayList<>();
        for (Map.Entry<String, Integer> m : rsl.entrySet()) {
            System.out.println(m.getKey() + "  -  " + m.getValue());
            fileList.add(m.getKey() + ";" + m.getValue()  + ";");
        }
        System.out.println(rsl.size());
        fileList.add(String.valueOf(rsl.size()));
    }
}