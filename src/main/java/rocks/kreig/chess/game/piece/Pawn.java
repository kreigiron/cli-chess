package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.Board;
import rocks.kreig.chess.game.Cell;
import rocks.kreig.chess.game.Player;
import rocks.kreig.chess.game.PlayerColor;

import java.util.List;
import java.util.Optional;

public class Pawn extends Piece {

    private boolean initialMove = true;

    public Pawn(final Player player, final Cell cell) {
        super(player, cell);
    }


    @Override
    public char charRepresentation() {
        return 'P';
    }

    @Override
    List<Cell> updateCellMovementCandidates(final Cell destinationCell) {
        final int allowedRankOffset = initialMove ? 2 : 1;

        final Board board = destinationCell.getBoard();

        for (int i = 0; i < allowedRankOffset; i++) {
            int currentRank = destinationCell.getRank();
            int currentFile = destinationCell.getFile();

            if (getOwner().getPlayerColor() == PlayerColor.WHITE) {
                currentRank++;
            } else {
                currentRank--;
            }

            final Optional<Cell> cellToUpdate = board.getCell(currentFile, currentRank);

            cellToUpdate.ifPresent(cell -> this.getAllowedCellsToMove().add(cell));
        }

        initialMove = false;
        return null;
    }


}
