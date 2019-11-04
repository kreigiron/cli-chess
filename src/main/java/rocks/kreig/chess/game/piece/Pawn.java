package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.board.Board;
import rocks.kreig.chess.game.board.Cell;
import rocks.kreig.chess.game.player.Player;
import rocks.kreig.chess.game.player.PlayerColor;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/* Pawn piece */
public class Pawn extends Piece {

    public Pawn(final Player player, final Cell cell) {
        super(player, cell);
    }

    @Override
    public char charRepresentation() {
        return 'P';
    }

    @Override
    public List<Cell> updateCellMovementCandidates(final Cell currentCell) {
        final List<Cell> allowedMovements = new LinkedList<>();

        final int allowedRankOffset = isAlreadyMoved() ? 2 : 1;

        final Board board = currentCell.getBoard();

        final int currentRank = currentCell.getRank();
        final int currentFile = currentCell.getFile();

        int step = (getOwner().getPlayerColor() == PlayerColor.WHITE) ? 1 : -1;

        for (int i = 1; i <= allowedRankOffset; i++) {
            final Optional<Cell> cellToUpdate = board.getCell(currentFile, currentRank + i * step);

            if (cellToUpdate.isPresent()) {
                // disallow movement on rival piece cell if movement is not capture
                final Cell cell = cellToUpdate.get();
                final Piece piece = cell.getPiece();
                if (piece == null) {
                    allowedMovements.add(cell);
                }
            }
        }

        // mark cell movement to capture move
        final Optional<Cell> firstDiagonalCell = board.getCell(currentFile + 1, currentRank + step);
        final Optional<Cell> secondDiagonalCell = board.getCell(currentFile - 1, currentRank + step);

        if (firstDiagonalCell.isPresent() && firstDiagonalCell.get().getPiece() != null && firstDiagonalCell.get().getPiece().getOwner() != this.getOwner()) {
            allowedMovements.add(firstDiagonalCell.get());
        }

        if (secondDiagonalCell.isPresent() && secondDiagonalCell.get().getPiece() != null && secondDiagonalCell.get().getPiece().getOwner() != this.getOwner()) {
            allowedMovements.add(secondDiagonalCell.get());
        }

        return allowedMovements;
    }

    @Override
    public String getType() {
        return "PAWN";
    }

    private boolean isAlreadyMoved() {
        return getCurrentCell().equals(getOriginalCell());
    }
}
