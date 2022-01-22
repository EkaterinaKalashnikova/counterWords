package counterwords.store;

import counterwords.model.Page;
import counterwords.model.Word;
import counterwords.parser.TextPageParser;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Logger;

public class WordTextStore implements StoreWord, AutoCloseable {
    private static final Logger LOG = Logger.getLogger(TextPageParser.class.getName());

    private Map<String, Integer> words = new HashMap<>();

    private Connection cnn;

    public WordTextStore() {
        Properties cfg = new Properties();
        this.cnn = Base.getConnection(cfg);
    }

    @Override
    public void save(Word word) {
        try (PreparedStatement statement = cnn.prepareStatement(
                "insert into words(word, count, page_id) values(?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, word.getValue());
            statement.setInt(2, word.getCount());
            statement.setInt(3, word.getPageId());
            statement.execute();
            try (ResultSet generatedKeys1 = statement.getGeneratedKeys()) {
                if (generatedKeys1.next()) {
                    word.setId(generatedKeys1.getInt(1));
                }
                words.put(word.getValue(), word.getCount());
            } catch (SQLException e) {
                LOG.info(e.getMessage());
            }
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        }
    }

    @Override
    public List<Word> findAll() {
        List<Word> words1 = new ArrayList<>();
        try (PreparedStatement statement = cnn.prepareStatement("select * from words")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    words1.add(getWord(resultSet));
                }
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
        return words1;
    }

    @Override
    public Word findById(int id) throws SQLException {
        Word word = null;
        try (PreparedStatement statement = cnn.prepareStatement("select * from words where id = ?")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    word = getWord(resultSet);
                }
            } catch (SQLException e) {
                LOG.info(e.getMessage());
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

    @Override
    public List<Word> getWordByIdPage(Page page) {
        List<Word> listWord = new ArrayList<>();
        try (PreparedStatement statement = cnn.prepareStatement("select * from words where words.page_id = ?")) {
            statement.setInt(1, page.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    listWord.add(getWord(resultSet));
                }
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
        return listWord;
    }

    private Word getWord(ResultSet resultSet) throws SQLException {
        return new Word(
                resultSet.getInt("id"),
                resultSet.getString("word"),
                resultSet.getInt("count"),
                resultSet.getInt("page_id")
        );
    }
}
