package counterwords.using;

import counterwords.io.Input;
import counterwords.io.Output;
import counterwords.model.Page;
import counterwords.model.Word;
import counterwords.parser.ParserPage;
import counterwords.parser.TextPageParser;
import counterwords.store.Store;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class ParseNewPage implements UserAction {

    @Override
    public String name() {
        return "=== Распарсить новую страницу ===";
    }

    @Override
    public boolean execute(Input input, Output output, Store<Page> storePage, Store<Word> storeWord) throws SQLException, IOException {
        String url = input.askStr("Введите URL: ");
        Page page = new Page(url);
        storePage.save(page);
        ParserPage tpp = new TextPageParser();
        Map<String, Integer> mapWords = tpp.parse(url);
        page.setWords(mapWords);
        for (Map.Entry<String, Integer> entry : mapWords.entrySet()) {
            Word word = new Word(entry.getKey(), entry.getValue());
            word.setPageId(page.getId());
            storeWord.save(word);
        }
        output.write(page);
        return true;
    }
}
