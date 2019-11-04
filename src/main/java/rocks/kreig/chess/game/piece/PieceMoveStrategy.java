package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.board.Cell;
import rocks.kreig.chess.game.player.TurnStatus;
import rocks.kreig.chess.game.exception.InvalidMovementException;

public interface PieceMoveStrategy {
    boolean canMove(final Cell origin, final Cell destination) throws InvalidMovementException;
    TurnStatus move(final Cell sourceCell, final Cell destinationCell);
}
