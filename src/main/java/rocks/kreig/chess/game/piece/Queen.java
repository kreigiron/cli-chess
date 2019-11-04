package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.Cell;
import rocks.kreig.chess.game.InvalidMovementException;
import rocks.kreig.chess.game.Player;

import java.util.LinkedList;
import java.util.List;

public class Queen extends Piece {

    public Queen(final Player player, final Cell cell) {
        super(player, cell);
    }

    @Override
    public boolean canMove(final Cell origin, final Cell destination) throws InvalidMovementException {
        throw new InvalidMovementException("Movement not yet implemented for this piece");
    }

    @Override
    public List<Cell> updateCellMovementCandidates(final Cell destinationCell) {
        return new LinkedList<>();
    }

    @Override
    public String getType() {
        return "QUEEN";
    }

    @Override
    public char charRepresentation() {
        return 'Q';
    }

}
