package counterwords.using;

import counterwords.io.Input;
import counterwords.store.Store;

import java.io.IOException;
import java.sql.SQLException;

public interface UserAction {
    String name();

    boolean execute(Input input, Store store) throws SQLException, IOException;
}
