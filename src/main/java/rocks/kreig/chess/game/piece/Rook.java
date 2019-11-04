package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.board.Cell;
import rocks.kreig.chess.game.player.Player;
import rocks.kreig.chess.game.exception.InvalidMovementException;

import java.util.LinkedList;
import java.util.List;

public class Rook extends Piece {

    public Rook(final Player player, final Cell cell) {
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
        return "ROOK";
    }

    @Override
    public char charRepresentation() {
        return 'R';
    }

}
