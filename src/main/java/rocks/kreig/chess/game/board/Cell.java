package rocks.kreig.chess.game.board;

import rocks.kreig.chess.game.piece.Piece;

import java.util.Objects;

public class Cell {
    private final int rank;
    private final int file;

    private final Board board;

    private Piece piece;

    public Cell(int file, int rank, final Board board) {
        this.rank = rank;
        this.file = file;
        this.board = board;
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

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Cell cell = (Cell) o;
        return rank == cell.rank &&
                file == cell.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    public Board getBoard() {
        return board;
    }

    boolean isShaded() {
        return (rank % 2 == 0) == (file % 2 == 0);
    }
}
