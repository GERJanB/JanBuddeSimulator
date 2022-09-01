package Model;

public class Testprogramm {
    public static void main(String[] args) {
        Player playerA = new Player(true,true);
        Player playerB = new Player(true, false);

        Referee referee = new Referee(playerA, playerB);

        Board board = new Board();
        printBoard(board);
    }

    public static void printBoard(Board board) {
        String dash11 = "-----------";
        String dash7 = "-------";
        String dash3 = "---";
        String space11 = "           ";
        String space7 = "       ";
        String space3 = "   ";
        String startSpace = "|   ";
        String endSpace = "   |";

        Field[] outerFields = board.getOuterRing().getFields();
        Field[] secondFields = board.getSecondRing().getFields();
        Field[] innerFields = board.getInnerRing().getFields();

        //print outer top
        for (int i = 0; i < 3; i++) {
            IO.print(printIcon(outerFields[i].getPiece()));
            if (i < 2) {
                IO.print(dash11);
            }
        }
        IO.println();

        //print second Line
        IO.println("|" + space11 + "|" + space11 + "|");

        //print second top
        IO.print(startSpace);
        for (int i = 0; i < 3; i++) {
            IO.print(printIcon(secondFields[i].getPiece()));
            if (i < 2) {
                IO.print(dash7);
            }
        }
        IO.println(endSpace);

        //print fourth Line
        IO.println("|" + space3 + "|" + space7 + "|" + space7 + "|" + space3 + "|");

        //print inner top
        IO.print(startSpace + startSpace);
        for (int i = 0; i < 3; i++) {
            IO.print(printIcon(innerFields[i].getPiece()));
            if (i < 2) {
                IO.print(dash3);
            }
        }
        IO.print(endSpace);
        IO.println(endSpace);

        //print sixth Line
        IO.println(startSpace + startSpace + "|" + space7 + "|" + endSpace + endSpace);

        //print center Line
        IO.print(printIcon(outerFields[7].getPiece()));
        IO.print(dash3);
        IO.print(printIcon(secondFields[7].getPiece()));
        IO.print(dash3);
        IO.print(printIcon(innerFields[7].getPiece()));
        IO.print(space7);
        IO.print(printIcon(innerFields[3].getPiece()));
        IO.print(dash3);
        IO.print(printIcon(secondFields[3].getPiece()));
        IO.print(dash3);
        IO.println(printIcon(outerFields[3].getPiece()));

        //print eighth Line
        IO.println(startSpace + startSpace + "|" + space7 + "|" + endSpace + endSpace);

        //print inner bottom
        IO.print(startSpace + startSpace);
        for (int i = 6; i > 3; i--) {
            IO.print(printIcon(innerFields[i].getPiece()));
            if (i > 4) {
                IO.print(dash3);
            }
        }
        IO.print(endSpace);
        IO.println(endSpace);

        //print second Spacer
        IO.println("|" + space3 + "|" + space7 + "|" + space7 + "|" + space3 + "|");

        //print second bottom
        IO.print(startSpace);
        for (int i = 6; i > 3; i--) {
            IO.print(printIcon(secondFields[i].getPiece()));
            if (i > 4) {
                IO.print(dash7);
            }
        }
        IO.println(endSpace);

        //print bottom spacer
        IO.println("|" + space11 + "|" + space11 + "|");

        //print outer bottom
        for (int i = 6; i > 3; i--) {
            IO.print(printIcon(outerFields[i].getPiece()));
            if (i > 4) {
                IO.print(dash11);
            }
        }
        IO.println();
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
