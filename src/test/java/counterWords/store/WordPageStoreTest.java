package counterWords.store;

import counterWords.model.Word;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WordPageStoreTest implements AutoCloseable {
    public Connection init() {
        try (InputStream in = WordPageStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    @Test
    public void save() {
    }

    @Test
    public void findAll() {
        try (WordPageStore store = new WordPageStore(ConnectionRollback.create(this.init()))) {
            List<Word> words = new ArrayList<>();
            words.add(new Word("value"));
            words.add(new Word("value1"));
           for (Word w: words) {
                System.out.println(w);
            }
            //List<Word> rsl = store.findAll();
            //rsl.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        try (WordPageStore store = new WordPageStore(ConnectionRollback.create(this.init()))) {
            Word byId = store.findById(2);
            System.out.println(byId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void close() throws Exception {

    }
}