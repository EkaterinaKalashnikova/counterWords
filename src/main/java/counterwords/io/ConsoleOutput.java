package counterwords.io;

public class ConsoleOutput implements Output {
    @Override
    public void write(Object s) {
        System.out.println(s.toString());
    }
}

