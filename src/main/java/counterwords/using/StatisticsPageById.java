package counterwords.using;

import counterwords.io.Input;
import counterwords.io.Output;
import counterwords.model.Page;
import counterwords.model.Word;
import counterwords.store.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StatisticsPageById implements UserAction {

    @Override
    public String name() {
        return "=== Показать статистику по отдельной страницы. Найти по id ===";
    }

    @Override
    public boolean execute(Input input, Output output, Store<Page> storePage, Store<Word> storeWord) throws SQLException, IOException {
        StorePage pages = new PageTextStore();
        Page page = pages.findByURL(input.askStr("Введите URL: "));
        StoreWord wordStore = new WordTextStore();
        List<Word> wordByIdPage = wordStore.getWordByIdPage(page);
        output.write(wordByIdPage);
        return true;
    }
}
