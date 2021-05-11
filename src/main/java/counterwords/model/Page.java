package counterwords.model;

import java.util.Map;
import java.util.Objects;

public class Page {
    private int id;

    private String url;

    private Map<String, Integer> words;

    public Page(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public Page(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, Integer> getWords() {
        return words;
    }

    public void setWords(Map<String, Integer> words) {
        this.words = words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Page page = (Page) o;
        return id == page.id && Objects.equals(url, page.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url);
    }
}
