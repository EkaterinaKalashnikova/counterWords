package counterWords.store;

import java.sql.SQLException;
import java.util.List;

public interface Store<T> {
    void save(T t);
    List<T> findAll();
    T findById(int id) throws SQLException;
    public void close() throws SQLException, Exception;
}
