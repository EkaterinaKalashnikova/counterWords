package counterWords.using;

import counterWords.io.*;
import counterWords.model.Page;
import counterWords.model.Word;
import counterWords.parser.ParserPage;
import counterWords.parser.TextPageParser;
import counterWords.store.PageTextStore;
import counterWords.store.Store;
import counterWords.store.WordPageStore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class App {
    private Input input;

    private Output output;

    private TextPageParser wordCounter;

    private PageTextStore pageStore;

    public App(Input input, Output output, TextPageParser wordCounter, PageTextStore pageStore) {
        this.input = input;
        this.output = output;
        this.wordCounter = wordCounter;
        this.pageStore = pageStore;
    }

    public void run() throws IOException {
        /**
         *  Реализовать логику работы с пользователем
         */
       // String url = "https://www.simbirsoft.com";

        // Спросить URL, спрасить страницу, сохранить в БД и выведем статистику
        // Действия пользователя:
        // 1. Спарсить новую страницу
        // 2. Загрузить все страницы
        // 3. Показать статистику по отдельной страницы. Найти по id
        // 4. Показать статистику по отдельной страницы. Найти по url
        // 5. Показать статистику по БД
    }

    public static void main(String[] args) throws IOException, SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите url: ");
       // String url = scan.nextLine();
        String url = "https://www.simbirsoft.com";
        Store<Page> pageTextStore = new PageTextStore();
        Page page = new Page(url);
        pageTextStore.save(page);
        ParserPage tpp = new TextPageParser();
        Map<String, Integer> map = tpp.parse(url);
       Store<Word> wordTextStore = new WordPageStore();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Word word = new Word(entry.getKey(), entry.getValue());
            word.setPageId(page.getId());
            wordTextStore.save(word);
        }
        // Сохранили результат 5
        List<Word> fileList = wordTextStore.findAll();
        String fileLogName = "C:\\projects\\counterWords\\src\\main\\java\\counterWords\\store\\log.txt";
        Output fileOut = new FileOutput(fileLogName, fileList);
        fileOut.write();
    }
}
