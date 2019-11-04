package rocks.kreig.chess.game;

import rocks.kreig.chess.game.board.Board;
import rocks.kreig.chess.game.exception.InvalidMovementException;
import rocks.kreig.chess.game.player.Player;
import rocks.kreig.chess.game.player.Turn;
import rocks.kreig.chess.game.player.TurnStatus;

public interface Game {

    boolean hasNextTurn();

    Turn nextTurn();

    void setPlayerNames(String whitePlayerName, String blackPlayerName);

    Game initiate();

    TurnStatus tryMove(String command) throws InvalidMovementException;

    Board getBoard();

    void surrender(Player surrenderPlayer);
}
