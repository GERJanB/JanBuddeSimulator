package Model;

public class Testprogramm {
    public static void main(String[] args) {
        Player playerA = new Player(true,true);
        Player playerB = new Player(true, false);

        Referee referee = new Referee(playerA, playerB);

        Board board = new Board();
        Field[][] fields = new Field[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Field field = new Field();
            }
        }

        while (true) {
            if (playerA.getPiecesCount() < 3) {
                IO.println("Spieler B hat gewonnen");
                break;
            }
            if (playerB.getPiecesCount() < 3) {
                IO.println("Spieler A hat gewonnen");
                break;
            }

            printBoard(referee.getBoard());
        }
    }

    public static void printBoard(Board board) {

    }
}
