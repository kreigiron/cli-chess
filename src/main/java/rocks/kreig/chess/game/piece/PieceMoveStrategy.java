package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.Cell;

public interface PieceMoveStrategy {
    boolean canMove(final Cell origin, final Cell destination);
}
