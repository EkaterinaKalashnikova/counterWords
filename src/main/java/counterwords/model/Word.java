package counterwords.model;

import java.util.Objects;

public class Word {
    private int id;

    private String value;

    private int count;

    private int pageId;

    public Word(int id, String value, int count, int pageId) {
        this.id = id;
        this.value = value;
        this.count = count;
        this.pageId = pageId;
    }

    public Word(String value) {
        this.value = value;
    }

    public Word(String value, int count) {
        this.value = value;
        this.count = count;
    }

    public Word(String value, String count) {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Word)) {
            return false;
        }
        Word word = (Word) o;
        return count == word.count && pageId == word.pageId && value.equals(word.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, count, pageId);
    }

    @Override
    public String toString() {
        return "Word{" + "id=" + id + ", value='" + value + '\'' + ", count=" + count + ", pageId=" + pageId + '}';
    }
}
