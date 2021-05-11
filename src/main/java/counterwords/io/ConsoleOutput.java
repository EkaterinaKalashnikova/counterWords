package counterwords.io;

import java.io.IOException;
import java.util.*;

public class ConsoleOutput implements Output {


    @Override
    public void write() throws IOException {
        List<String> fileList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (Map.Entry<String, Integer> m : map.entrySet()) {
            String key = m.getKey();
            Integer value = m.getValue();
            System.out.println(key + "  -  " + value);
            fileList.add(String.format("%s-%s", m.getKey(), m.getValue()));
        }
        System.out.println(map.size());
        fileList.add(String.valueOf(map.size()));
    }
}

