package counterwords.using;

import counterwords.io.Input;
import counterwords.model.Page;
import counterwords.store.PageTextStore;
import counterwords.store.Store;

import java.io.IOException;
import java.sql.SQLException;

public class StatisticsPageById implements UserAction {

    @Override
    public String name() {
        return "=== Показать статистику по отдельной страницы. Найти по id ===";
    }

    @Override
    public boolean execute(Input input, Store store) throws SQLException, IOException {
        //String url = "";
        String url = "https://www.simbirsoft.com";
        Page page = new Page(url);
        Store<Page> pages = new PageTextStore();
        pages.findById(page.getId());
        return true;
    }
}