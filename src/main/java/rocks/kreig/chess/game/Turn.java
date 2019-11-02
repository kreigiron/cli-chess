package rocks.kreig.chess.game;

public class Turn {

    private Player currentTurnPlayer;
    private Player nextTurnPlayer;

    public Turn(Player currentTurnPlayer, Player nextTurnPlayer) {
        this.currentTurnPlayer = currentTurnPlayer;
        this.nextTurnPlayer = nextTurnPlayer;
    }

    public Player getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }

    public Player getNextTurnPlayer() {
        return nextTurnPlayer;
    }

    public Turn next() {
        return new Turn(getNextTurnPlayer(), getCurrentTurnPlayer());
    }
}
