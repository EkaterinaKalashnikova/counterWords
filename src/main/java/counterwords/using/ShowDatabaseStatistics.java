package counterwords.using;

import counterwords.io.FileOutput;
import counterwords.io.Input;
import counterwords.io.Output;
import counterwords.model.Page;
import counterwords.model.Word;
import counterwords.store.Store;
import counterwords.store.StoreWord;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShowDatabaseStatistics implements UserAction {
    @Override
    public String name() {
        return "=== Показать статистику по БД ====";
    }

    @Override
    public boolean execute(Input input, Output output, Store<Page> storePage, Store<Word> storeWord) throws IOException {
        List<Page> fileListPages = storePage.findAll();
        for (Page pageList : fileListPages) {
            List<Word> listAllWords = ((StoreWord) storeWord).getWordByIdPage(pageList);
            Map<String, Integer> mapWords = listAllWords.stream().collect(Collectors.toMap(Word::getValue, Word::getCount));
            pageList.setWords(mapWords);
        }
       output.write(fileListPages);
        return true;
    }
}
