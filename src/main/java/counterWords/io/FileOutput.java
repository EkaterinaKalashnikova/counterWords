package counterWords.io;

import counterWords.model.Word;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileOutput implements Output {
    private String fileName;
    private List<Word> filelist;

    public FileOutput(String fileName, List<Word> filelist) {
        this.fileName = fileName;
        this.filelist = filelist;
    }


    private void write(String fileName, List<Word> filelist) throws IOException {
        Files.write(Path.of(fileName), filelist.stream()
                .map(Word::toString).collect(Collectors.toList()));
    }

    @Override
    public void write() {
        try {
            write(fileName, filelist);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
