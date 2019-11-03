package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.Cell;
import rocks.kreig.chess.game.Player;

public class Bishop extends Piece {

    public Bishop(final Player player, final Cell cell) {
        super(player, cell);
    }

    @Override
    public boolean canMove(final Cell origin, final Cell destination) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char charRepresentation() {
        return 'B';
    }

}
