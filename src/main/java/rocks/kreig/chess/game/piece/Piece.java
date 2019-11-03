package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.Cell;
import rocks.kreig.chess.game.Player;

import static rocks.kreig.chess.game.PlayerColor.BLACK;
import static rocks.kreig.chess.game.PlayerColor.WHITE;

public abstract class Piece implements PieceMoveStrategy {

    private final Player owner;
    private final Cell cell;

    public Piece(final Player player, final Cell cell) {
        this.owner = player;
        this.cell = cell;
    }

    public abstract char charRepresentation();

    @Override
    public String toString() {
        if(WHITE == owner.getPlayerColor()) {
            return String.valueOf(charRepresentation()).toLowerCase();
        }
        return String.valueOf(charRepresentation());
    }

    public Player getOwner() {
        return owner;
    }

    public Cell getCell() {
        return cell;
    }

}
