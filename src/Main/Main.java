package Main;

public class Main {

    GameWorld gameWorld;

    public void start() {
        gameWorld = new GameWorld(2);
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
