package rocks.kreig.chess.game;

public class Player {
    private final String name;
    private final PlayerColor playerColor;

    public Player(String name, PlayerColor playerColor) {
        this.name = name;
        this.playerColor = playerColor;
    }

    public String getName() {
        return name;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }
}
