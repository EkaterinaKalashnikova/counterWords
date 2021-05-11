package counterwords.store;

import counterwords.model.Page;

import java.sql.*;
import java.util.*;

public class PageTextStore implements Store<Page>, AutoCloseable {
    private List<Page> pages = new ArrayList<>();

    private Connection cnn;

    public PageTextStore() throws SQLException {
        // this.cnn = ConnectionRollback.create(this.init())
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
            e.printStackTrace();
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
                e.printStackTrace();
        }
        return pages1;
    }

    @Override
    public Page findById(int id) throws SQLException {
        Page page = null;
        try (PreparedStatement statement = cnn.prepareStatement("select * from pages where id = ?")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id1 = resultSet.getInt("id");
                    String url = resultSet.getString("url");
                    page = new Page(id1, url);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return page;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }
}



