package counterWords.using;

import counterWords.io.*;
import counterWords.model.Page;
import counterWords.model.Word;
import counterWords.parser.ParserPage;
import counterWords.parser.TextPageParser;
import counterWords.store.PageTextStore;
import counterWords.store.Store;
import counterWords.store.WordTextStore;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());

    private static Input input;

    private static Store store;

    private Output output;

    private TextPageParser wordCounter;

    private PageTextStore pageStore;

    private WordTextStore wordStore;


    public App(Input input, Output output, TextPageParser wordCounter, PageTextStore pageStore, WordTextStore wordStore) {
        this.input = input;
        this.output = output;
        this.wordCounter = wordCounter;
        this.pageStore = pageStore;
        this.wordStore = wordStore;
    }

    // public void run() throws IOException, SQLException {

    /**
     * Реализовать логику работы с пользователем
     */
    // String url = "https://www.simbirsoft.com";
    // Спросить URL, спрасить страницу, сохранить в БД и выведем статистику

    //System.out.println();
    // System.out.println("Выберите действие: ");

    // String url = "https://www.simbirsoft.com";

    // Действия пользователя:
    // 1. Спарсить новую страницу
    // 2. Загрузить все страницы
    // 3. Показать статистику по отдельной страницы. Найти по id
    // 4. Показать статистику по БД
    public void init(Input input, Store store, List<UserAction> actions) throws IOException, SQLException {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Выберите действие: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, store);
        }
    }

    private void showMenu(List<UserAction> actions) {
        System.out.println("Menu:");
        for (int index = 0; index < actions.size(); index++) {
            System.out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        LOGGER.debug("All work!");
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите url: ");
        String url = scan.nextLine();
        url = url.replace('"', ' ').trim();
        System.out.println(url);
       // System.out.println("Выберите действие: ");
        Store<Page> pageTextStore = new PageTextStore();
        Page page = new Page(url);
        pageTextStore.save(page);
        ParserPage tpp = new TextPageParser();
        Map<String, Integer> map = tpp.parse(url);
        Store<Word> wordTextStore = new WordTextStore();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Word word = new Word(entry.getKey(), entry.getValue());
            word.setPageId(page.getId());
            wordTextStore.save(word);
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "  -  " + value);
        }
            System.out.println(map.size());


        /*List<UserAction> actions = new ArrayList<>();
        actions.add(new ParseNewPage());x
        actions.add(new LoadAllPages());
        actions.add(new StatisticsPageById());
        actions.add(new ShowDatabaseStatistics());
        new App(
                new ConsoleInput(),
                new ConsoleOutput(),
                new TextPageParser(),
                new PageTextStore(),
                new WordTextStore()
        ).init(input, store, actions);*/
    }
}







