package counterwords.using;

import counterwords.io.Input;
import counterwords.io.Output;
import counterwords.model.Page;
import counterwords.model.Word;
import counterwords.store.PageTextStore;
import counterwords.store.Store;
import counterwords.store.StorePage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LoadAllPages implements UserAction {
    @Override
    public String name() {
        return "=== Загрузить все страницы ===";
    }

    @Override
    public boolean execute(Input input, Output output, Store<Page> storePage, Store<Word> storeWord) throws IOException {
        StorePage pageAll = new PageTextStore();
        List<Page> all = pageAll.findAll();
        output.write(all.stream().map(Page::getUrl).collect(Collectors.toList()));
        return true;
    }
}
