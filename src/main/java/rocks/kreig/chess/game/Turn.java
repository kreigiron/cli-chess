package rocks.kreig.chess.game;

public class Turn {

    private Player currentTurnPlayer;
    private Player nextTurnPlayer;

    public Turn(Player currentTurnPlayer, Player nextTurnPlayer) {
        this.currentTurnPlayer = currentTurnPlayer;
        this.nextTurnPlayer = nextTurnPlayer;
    }

    public Player getCurrentPlayer() {
        return currentTurnPlayer;
    }

    public Player getOtherPlayer() {
        return nextTurnPlayer;
    }

    public Turn next() {
        return new Turn(getOtherPlayer(), getCurrentPlayer());
    }
}
