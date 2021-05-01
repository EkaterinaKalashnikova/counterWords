package counterWords.io;

import java.io.IOException;
import java.util.*;

public class ConsoleOutput implements Output {


    @Override
    public void write() throws IOException {
       // List<String> fileList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        map.forEach((key, value) -> {
            System.out.println(key + "  -  " + value);
            // fileList.add(m.getKey() + "-" + m.getValue());
        });
        System.out.println(map.size());
       // fileList.add(String.valueOf(map.size()))
    }
}

