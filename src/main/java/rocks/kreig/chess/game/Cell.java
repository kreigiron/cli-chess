package rocks.kreig.chess.game;

import rocks.kreig.chess.game.piece.Piece;

public class Cell {
    public static final int A = 0;
    public static final int B = 1;
    public static final int C = 2;
    public static final int D = 3;
    public static final int E = 4;
    public static final int F = 5;
    public static final int G = 6;
    public static final int H = 7;
    private final int rank;
    private final int file;

    private Piece piece;


    public Cell(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public boolean isShaded() {
        return (rank % 2 == 0) == (file % 2 == 0);
    }

    public void setPiece(final Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        if (piece == null) {
            return " ";
        }
        return piece.toString();
    }
}
