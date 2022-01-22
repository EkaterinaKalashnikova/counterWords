package counterwords.store;

import counterwords.model.Page;
import counterwords.model.Word;
import java.util.List;

public interface StoreWord extends Store<Word> {

    List<Word> getWordByIdPage(Page page);
}
