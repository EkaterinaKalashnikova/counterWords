package counterWords.io;

public interface Input {
    String readLine();
    public String askStr(String question);
    public int askInt(String question);
    public int askInt(String question, int max);
}
