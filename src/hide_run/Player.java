package hide_run;

public class Player extends Person {
    private int runRate = 15;
    public int numberOfChaser = 0;
    public boolean isCaught = false;

    public Player(int x, int y, int t) {
        super(x, y, t);
    }

    public void run() {
        walk(runRate);
    }
}
