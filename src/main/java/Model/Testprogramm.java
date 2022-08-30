package Model;

public class Testprogramm {
    public static void main(String[] args) {
        Player playerA = new Player(true,true);
        Player playerB = new Player(true, false);

        new Game(playerA, playerB);

        while (true) {

        }
    }
}
