package counterwords.store;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Base {
    private Connection cnn;
    private Properties cfg;

    public static Connection getConnection(Properties cfg) {
        Connection cnn;
        try (InputStream in = PageTextStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            assert in != null;
            cfg.load(in);
            Class.forName(cfg.getProperty("jdbc.driver"));
            cnn = DriverManager.getConnection(
                    cfg.getProperty("jdbc.url"),
                    cfg.getProperty("jdbc.username"),
                    cfg.getProperty("jdbc.password"));

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return cnn;
    }
}
