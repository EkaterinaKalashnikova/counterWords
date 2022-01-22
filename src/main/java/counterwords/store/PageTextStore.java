package counterwords.store;

import counterwords.model.Page;
import counterwords.parser.TextPageParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class PageTextStore implements StorePage, AutoCloseable {
    private static final Logger LOG = Logger.getLogger(PageTextStore.class.getName());

    private List<Page> pages = new ArrayList<>();

    private Connection cnn;

    public PageTextStore() {
        Properties cfg = new Properties();
        this.cnn = Base.getConnection(cfg);
    }

    @Override
    public void save(Page page) {
        try (PreparedStatement statement =
                     cnn.prepareStatement(
                             "insert into pages(url) values (?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, page.getUrl());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    page.setId(generatedKeys.getInt(1));
                }
                pages.add(page);
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
   }

    @Override
    public List<Page> findAll() {
        List<Page> pages1 = new ArrayList<>();
            try (PreparedStatement statement = cnn.prepareStatement("select * from pages")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        pages1.add(new Page(
                                resultSet.getInt("id"),
                                resultSet.getString("url")));
                    }
                }
            } catch (Exception e) {
                LOG.info(e.getMessage());
        }
        return pages1;
    }

    @Override
    public Page findById(int id) throws SQLException {
        Page page = null;
        try (PreparedStatement statement = cnn.prepareStatement("select * from pages where id = ?")) {
            statement.setInt(1, id);
            page = getPage(page, statement);
        }
        return page;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    @Override
    public Page findByURL(String url) throws SQLException {
        Page page = null;
        try (PreparedStatement statement = cnn.prepareStatement("select * from pages where url = ?")) {
            statement.setString(1, url);
            page = getPage(page, statement);
        }
        return page;
    }

    private Page getPage(Page page, PreparedStatement statement) {
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                String url1 = resultSet.getString("url");
                page = new Page(id1, url1);
            }

        } catch (SQLException e) {
            LOG.info(e.getMessage());
        }
        return page;
    }
}



