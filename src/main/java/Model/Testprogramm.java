package Model;

public class Testprogramm {
    public static void main(String[] args) {
        Player playerA = new Player(true,true);
        Player playerB = new Player(true, false);

        Referee referee = new Referee(playerA, playerB);
        IO.println(playerA.PlacePiece(0, 1));
        IO.println(playerB.PlacePiece(2, 2));
        IO.println(playerA.PlacePiece(2,2));

        Board board = new Board();
        printBoard(referee.getBoard());
    }

    public static void printBoard(Board board) {
        String dash14 = "--------------";
        String dash9 = "---------";
        String dash4 = "----";
        String space14 = "              ";
        String space9 = "         ";
        String space4 = "    ";
        String startSpace = "|    ";
        String endSpace = "    |";

        Field[] outerFields = board.getOuterRing().getFields();
        Field[] secondFields = board.getSecondRing().getFields();
        Field[] innerFields = board.getInnerRing().getFields();

        //print outer top
        for (int i = 0; i < 3; i++) {
            IO.print(printIcon(outerFields[i].getPiece()));
            if (i < 2) {
                IO.print(dash14);
            }
        }
        IO.println();

        //print second Line
        IO.println("|" + space14 + "|" + space14 + "|");

        //print second top
        IO.print(startSpace);
        for (int i = 0; i < 3; i++) {
            IO.print(printIcon(secondFields[i].getPiece()));
            if (i < 2) {
                IO.print(dash9);
            }
        }
        IO.println(endSpace);

        //print fourth Line
        IO.println("|" + space4 + "|" + space9 + "|" + space9 + "|" + space4 + "|");

        //print inner top
        IO.print(startSpace + startSpace);
        for (int i = 0; i < 3; i++) {
            IO.print(printIcon(innerFields[i].getPiece()));
            if (i < 2) {
                IO.print(dash4);
            }
        }
        IO.print(endSpace);
        IO.println(endSpace);

        //print sixth Line
        IO.println(startSpace + startSpace + "|" + space9 + "|" + endSpace + endSpace);

        //print center Line
        IO.print(printIcon(outerFields[7].getPiece()));
        IO.print(dash4);
        IO.print(printIcon(secondFields[7].getPiece()));
        IO.print(dash4);
        IO.print(printIcon(innerFields[7].getPiece()));
        IO.print(space9);
        IO.print(printIcon(innerFields[3].getPiece()));
        IO.print(dash4);
        IO.print(printIcon(secondFields[3].getPiece()));
        IO.print(dash4);
        IO.println(printIcon(outerFields[3].getPiece()));

        //print eighth Line
        IO.println(startSpace + startSpace + "|" + space9 + "|" + endSpace + endSpace);

        //print inner bottom
        IO.print(startSpace + startSpace);
        for (int i = 6; i > 3; i--) {
            IO.print(printIcon(innerFields[i].getPiece()));
            if (i > 4) {
                IO.print(dash4);
            }
        }
        IO.print(endSpace);
        IO.println(endSpace);

        //print second Spacer
        IO.println("|" + space4 + "|" + space9 + "|" + space9 + "|" + space4 + "|");

        //print second bottom
        IO.print(startSpace);
        for (int i = 6; i > 3; i--) {
            IO.print(printIcon(secondFields[i].getPiece()));
            if (i > 4) {
                IO.print(dash9);
            }
        }
        IO.println(endSpace);

        //print bottom spacer
        IO.println("|" + space14 + "|" + space14 + "|");

        //print outer bottom
        for (int i = 6; i > 3; i--) {
            IO.print(printIcon(outerFields[i].getPiece()));
            if (i > 4) {
                IO.print(dash14);
            }
        }
        IO.println();

        IO.println();
        IO.println("Spieler A = +");
        IO.println("Spieler B = #");
    }

    private static String printIcon(Piece piece) {
        if (piece == null) {
            return "O";
        }
        if (piece.getBelongsPlayerA()) {
            return "+";
        } else {
            return "#";
        }
    }
}
