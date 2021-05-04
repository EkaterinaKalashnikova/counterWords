package counterWords.store;

import counterWords.model.Word;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static org.hamcrest.core.Is.is;

public class WordTextStoreTest  {

    private Connection init() {
        try (InputStream in = WordTextStore.class.getClassLoader().getResourceAsStream(".app.properties")) {
            Properties config = new Properties();
            if (in != null) {
                config.load(in);
            }
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
    public void testSave() throws SQLException {
        try (WordTextStore store = new WordTextStore(ConnectionRollback.create(this.init()))) {
            Word word = new Word("value");
            store.save(word);
            Assert.assertThat(store.findAll(),is(word));
        }
    }

    @Test
    public void testFindAll() {
        List<Word> words = new ArrayList<>();
        try (WordTextStore store = new WordTextStore(ConnectionRollback.create(this.init()))) {
            words.add(new Word("value"));
            words.add(new Word("value1"));
            Assert.assertThat(store.findAll(), is(words));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindById() {
        Word[] words = new Word[3];
        try (WordTextStore store = new WordTextStore(ConnectionRollback.create(this.init()))) {
            words[0] = new Word("value");
            words[1] = new Word("value1");
            words[2] = new Word("value2");
            for (Word word: words) {
                store.save(word);
            }
            String expected = String.valueOf(store.findAll().get(3));
            int result = store.findById(store.findAll().get(3).getId()).getId();
            Assert.assertThat(result, is(expected));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


   /* @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }*/
}




