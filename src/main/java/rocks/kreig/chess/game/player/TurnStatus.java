package rocks.kreig.chess.game.player;

import rocks.kreig.chess.game.piece.Piece;
/** Status recorded for the current turn */
public class TurnStatus {

    private boolean checked;
    private Player checkedBy;

    private Piece captured;
    private Player capturedBy;

    public boolean isChecked() {
        return checked;
    }

    public void capture(final Piece destinationPiece, final Player owner) {
        this.captured = destinationPiece;
        this.capturedBy = owner;
    }

    public void check(final Player checkedBy) {
        this.checked = true;
        this.checkedBy = checkedBy;
    }

    public Player getCheckedBy() {
        return checkedBy;
    }

    public Piece getCaptured() {
        return captured;
    }

    public Player getCapturedBy() {
        return capturedBy;
    }
}
