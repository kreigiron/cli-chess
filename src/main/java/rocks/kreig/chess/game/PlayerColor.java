package rocks.kreig.chess.game;

public enum PlayerColor {
    WHITE(1),
    BLACK(2);

    private final int turnOrdinal;

    PlayerColor(int turnOrdinal) {
        this.turnOrdinal = turnOrdinal;
    }

    public int getTurnOrdinal() {
        return turnOrdinal;
    }
}
