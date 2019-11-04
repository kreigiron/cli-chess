package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.Cell;
import rocks.kreig.chess.game.InvalidMovementException;
import rocks.kreig.chess.game.TurnStatus;

public interface PieceMoveStrategy {
    boolean canMove(final Cell origin, final Cell destination) throws InvalidMovementException;
    TurnStatus move(final Cell sourceCell, final Cell destinationCell);
}
