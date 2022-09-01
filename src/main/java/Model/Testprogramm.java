package Model;

public class Testprogramm {
    public static void main(String[] args) {
        Player playerA = new Player(true,true);
        Player playerB = new Player(true, false);

        Referee referee = new Referee(playerA, playerB);

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
        Field[] outerFields = board.getOuterRing().getFields();
        Field[] secondFields = board.getSecondRing().getFields();
        Field[] innerFields = board.getInnerRing().getFields();

        
    }
}
