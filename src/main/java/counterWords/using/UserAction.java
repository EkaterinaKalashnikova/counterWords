package counterWords.using;

import counterWords.io.Input;
import counterWords.store.Store;

import java.io.IOException;
import java.sql.SQLException;

public interface UserAction {
    String name();

    boolean execute(Input input, Store store) throws SQLException, IOException;
}
