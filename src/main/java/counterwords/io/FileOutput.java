package counterwords.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileOutput implements Output {
    private String fileName;

    public FileOutput(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(Object s) throws IOException {
        Files.write(Path.of(fileName), s.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
