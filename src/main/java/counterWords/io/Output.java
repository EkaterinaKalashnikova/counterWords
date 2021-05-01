package counterWords.io;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface Output {
    void write() throws IOException;
}
