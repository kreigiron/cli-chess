package rocks.kreig.chess.game;

public interface Game {

    boolean hasNextTurn();

    Turn nextTurn();

    void setPlayerNames(String whitePlayerName, String blackPlayerName);

    Game initiate();

    void tryMove(String command);

    Board getBoard();

    void surrender(Player surrenderPlayer);
}
