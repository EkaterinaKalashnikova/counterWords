package counterWords.store;

import counterWords.model.Page;
import counterWords.model.Word;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class WordTextStore implements Store<Word>, AutoCloseable {
    private Map<String, Integer> words = new HashMap<>();

    private Connection cnn;

    public WordTextStore() throws SQLException {
            // this.cnn = ConnectionRollback.create(this.init())
            Properties cfg = new Properties();
            this.cnn = Base.getConnection(cfg);
        }

    public WordTextStore(Connection connection) {
        this.cnn = connection;
    }

    @Override
    public void save(Word word) {
        try (PreparedStatement statement1 = cnn.prepareStatement(
                "insert into words(word, count, page_id) values(?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement1.setString(1, word.getValue());
            statement1.setInt(2, word.getCount());
            statement1.setInt(3, word.getPageId());
            statement1.execute();
            try (ResultSet generatedKeys1 = statement1.getGeneratedKeys()) {
                if (generatedKeys1.next()) {
                    word.setId(generatedKeys1.getInt(1));
                }
                words.put(word.getValue(), word.getCount());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Word> findAll() {
        List<Word> words1 = new ArrayList<>();
        try (PreparedStatement statement = cnn.prepareStatement("select * from words")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    words1.add(new Word(
                            resultSet.getInt("id"),
                            resultSet.getString("word"),
                            resultSet.getInt("count"),
                            resultSet.getInt("page_id")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words1;
    }

    @Override
    public Word findById(int id) throws SQLException {
        Word word = null;
        try (PreparedStatement statement = cnn.prepareStatement("select * from words where id = ?")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    word = new Word(
                            resultSet.getInt("id"),
                            resultSet.getString("word"),
                            resultSet.getInt("count"),
                            resultSet.getInt("page_id")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return word;
    }

    @Override
    public void close() throws SQLException {
        if (cnn != null) {
            cnn.close();
        }
    }
}
