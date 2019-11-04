package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.Cell;
import rocks.kreig.chess.game.Player;

import java.util.LinkedList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(final Player player, final Cell cell) {
        super(player, cell);
    }

    @Override
    public boolean canMove(final Cell origin, final Cell destination) {
        throw new UnsupportedOperationException("Movement not yet implemented for this piece");
    }

    @Override
    public List<Cell> updateCellMovementCandidates(final Cell destinationCell) {
        return new LinkedList<>();
    }

    @Override
    public String getType() {
        return "BISHOP";
    }

    @Override
    public char charRepresentation() {
        return 'B';
    }

}
