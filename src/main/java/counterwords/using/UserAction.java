package counterwords.using;

import counterwords.io.Input;
import counterwords.io.Output;
import counterwords.model.Page;
import counterwords.model.Word;
import counterwords.store.Store;
import java.io.IOException;
import java.sql.SQLException;

public interface UserAction {
    String name();

    boolean execute(Input input, Output output, Store<Page> storePage, Store<Word> storeWord) throws SQLException, IOException;
}
