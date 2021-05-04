package counterWords.using;

import counterWords.io.Input;
import counterWords.model.Page;
import counterWords.store.PageTextStore;
import counterWords.store.Store;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoadAllPages implements UserAction{
    @Override
    public String name() {
        return "=== Загрузить все страницы ===";
    }

    @Override
    public boolean execute(Input input, Store store) throws SQLException, IOException {
        String url = "https://www.simbirsoft.com";
        Store<Page> pageAll = new PageTextStore();
        List<Page> all = pageAll.findAll();
        return true;
    }
}
