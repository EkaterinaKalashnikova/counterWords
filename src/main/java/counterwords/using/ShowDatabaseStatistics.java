package counterwords.using;

import counterwords.io.FileOutput;
import counterwords.io.Input;
import counterwords.io.Output;
import counterwords.model.Word;
import counterwords.store.Store;
import counterwords.store.WordTextStore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowDatabaseStatistics implements UserAction {
    @Override
    public String name() {
        return "=== Показать статистику по БД ====";
    }

    @Override
    public boolean execute(Input input, Store store) throws SQLException, IOException {
        String url = "https://www.simbirsoft.com";
        WordTextStore storeWord = new WordTextStore();
        List<Word> fileList = storeWord.findAll();
        String fileLogName = "C:\\projects\\counterWords\\src\\main\\java\\counterWords\\store\\log.txt";
        Output fileOut = new FileOutput(fileLogName, fileList);
        fileOut.write();
        return true;
    }
}
