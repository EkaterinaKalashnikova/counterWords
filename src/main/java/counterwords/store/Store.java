package counterwords.store;

import java.sql.SQLException;
import java.util.List;

public interface Store<T> {

    void save(T t) throws SQLException;

    List<T> findAll();

    T findById(int id) throws SQLException;

    void close() throws Exception;
}
