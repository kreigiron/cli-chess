package rocks.kreig.chess.game;

public interface Game {

    boolean hasNextTurn();

    Turn nextTurn();

    void setPlayerNames(String whitePlayerName, String blackPlayerName);

    Game initiate();

    TurnStatus tryMove(String command) throws InvalidMovementException;

    Board getBoard();

    void surrender(Player surrenderPlayer);
}
