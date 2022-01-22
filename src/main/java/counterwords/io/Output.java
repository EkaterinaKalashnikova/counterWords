package counterwords.io;

import java.io.IOException;

public interface Output {
    void write(Object s) throws IOException;
}
