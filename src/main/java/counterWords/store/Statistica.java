package counterWords.store;

import counterWords.model.Page;
import counterWords.model.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistica<T> implements Store<T> {
    private Connection cnn;

    @Override
    public void save(T o) {
    }

    @Override
    public List<T> findAll() {
        List<Page> pages = new ArrayList<>();
        List<Word> words = new ArrayList<>();
        Map<List<Page>, List<Word>> pageWordMap = new HashMap<>();
        try (PreparedStatement statement = cnn.prepareStatement(
                "select * from pages inner join words w on pages.id = w.page_id ")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    pages.add(new Page(
                            resultSet.getInt("id"),
                            resultSet.getString("url")));
                    words.add(new Word(
                            resultSet.getInt("id"),
                            resultSet.getString("word"),
                            resultSet.getInt("count"),
                            resultSet.getInt("page_id")));
                    pageWordMap.put(pages, words);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (List<T>) pageWordMap;
    }

    @Override
    public T findById(int id) throws SQLException {
        Page page = null;
        try (PreparedStatement statement = cnn.prepareStatement(
                "select p.id, p.url, w.word, w.count from pages p join words w on p.id = w.page_id\n" +
                        "where p.id = ?\n" +
                        "order by p.url, w.word")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id1 = resultSet.getInt("id");
                    String url = resultSet.getString("url");
                    page = new Page(id1,url);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (T) page;
    }

    @Override
    public void close() throws SQLException, Exception {
        if (cnn != null) {
            cnn.close();
        }
    }
}
