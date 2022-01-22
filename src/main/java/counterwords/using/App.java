package counterwords.using;

import counterwords.io.*;
import counterwords.store.PageTextStore;
import counterwords.store.StorePage;
import counterwords.store.StoreWord;
import counterwords.store.WordTextStore;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());

    private Input input;

    private StorePage storePage;

    private StoreWord storeWord;

    private Output out;

    private Output consoleOutput;

    private App(Input input, Output out, StorePage storePage, StoreWord storeWord) {
        this.input = input;
        this.out = out;
        this.storePage = storePage;
        this.storeWord = storeWord;
        this.consoleOutput = new ConsoleOutput();
    }

    private void init(List<UserAction> actions) throws IOException, SQLException {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Выберите действие: ");
            if (select == 6) {
                consoleOutput.write("Выход из программы)");
                break;
            }
            if (select == 4) {
                String fileName = input.askStr("Введите название файла: ");
                out = new FileOutput(fileName);
                consoleOutput.write("Следующие действия будут записаны в файл " + fileName);
                continue;
            }
            if (select == 5) {
                out = new ConsoleOutput();
                consoleOutput.write("Следующие действия будут записаны в консоль ");
                continue;
            }
            if (select < 0 || select >= actions.size()) {
                consoleOutput.write("Неправильный ввод, вы можете выбрать: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, out, storePage, storeWord);
        }
    }

    private void showMenu(List<UserAction> actions) throws IOException {
        consoleOutput.write("Menu:");
        for (int index = 0; index < actions.size(); index++) {
            consoleOutput.write(index + ". " + actions.get(index).name());
        }
        consoleOutput.write("4. === Записать следующее действие в файл === ");
        consoleOutput.write("5. === Прекратить запись в файл === ");
        consoleOutput.write("6. === Выход === ");
    }

    public static void main(String[] args) throws IOException, SQLException {
        LOGGER.debug("All work!");
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ParseNewPage());
        actions.add(new LoadAllPages());
        actions.add(new StatisticsPageById());
        actions.add(new ShowDatabaseStatistics());
        new App(
                new ConsoleInput(),
                new ConsoleOutput(),
                new PageTextStore(),
                new WordTextStore()
        ).init(actions);
    }
}







