package counterwords.store;

import counterwords.model.Page;
import java.sql.SQLException;

public interface StorePage extends Store<Page> {

    Page findByURL(String url) throws SQLException;
}
