package counterWords.using;

import counterWords.io.Input;
import counterWords.model.Page;
import counterWords.model.Word;
import counterWords.parser.ParserPage;
import counterWords.parser.TextPageParser;
import counterWords.store.PageTextStore;
import counterWords.store.Store;
import counterWords.store.WordTextStore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class ParseNewPage implements UserAction {

    @Override
    public String name() {
       // return "=== Get a parser of page ====";
        return "=== Спарсить новую страницу ===";
    }

    @Override
    public boolean execute(Input input, Store store) throws SQLException, IOException {
        Store<Page> pageTextStore = new PageTextStore();
       // String url = "";
        String url = "https://www.simbirsoft.com";
        Page page = new Page(url);
        pageTextStore.save(page);
        ParserPage tpp = new TextPageParser();
        Map<String, Integer> map = tpp.parse(url);
        Store<Word> wordTextStore = new WordTextStore();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Word word = new Word(entry.getKey(), entry.getValue());
            word.setPageId(page.getId());
            wordTextStore.save(word);
        }
        return true;
    }
}
