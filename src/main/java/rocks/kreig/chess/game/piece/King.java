package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.Cell;
import rocks.kreig.chess.game.Player;

import java.util.List;

public class King extends Piece {

    public King(final Player player, final Cell cell) {
        super(player, cell);
    }

    @Override
    public boolean canMove(final Cell origin, final Cell destination) {
        throw new UnsupportedOperationException();
    }

    @Override
    List<Cell> updateCellMovementCandidates(final Cell destinationCell) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public char charRepresentation() {
        return 'K';
    }

}
