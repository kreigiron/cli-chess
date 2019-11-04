package rocks.kreig.chess.game;

import rocks.kreig.chess.game.board.Board;
import rocks.kreig.chess.game.exception.InvalidMovementException;
import rocks.kreig.chess.game.command.Command;
import rocks.kreig.chess.game.command.CommandParser;
import rocks.kreig.chess.game.command.CommandParserImpl;
import rocks.kreig.chess.game.player.Player;
import rocks.kreig.chess.game.player.PlayerColor;
import rocks.kreig.chess.game.player.Turn;
import rocks.kreig.chess.game.player.TurnStatus;

public class ChessGameImpl implements Game {

    private Turn currentTurn;
    private Player white;
    private Player black;

    private Board board;

    private CommandParser commandParser;
    private Player surrenderPlayer;

    @Override
    public boolean hasNextTurn() {
        return !isSurrender() && !isCheckMate();
    }

    private boolean isSurrender() {
        return surrenderPlayer != null;
    }

    private boolean isCheckMate() {
        return false;
    }

    @Override
    public Turn nextTurn() {
        this.currentTurn = currentTurn.next();
        return currentTurn;
    }

    @Override
    public TurnStatus tryMove(final String commandString) throws InvalidMovementException {
        final Command command = commandParser.parse(commandString);

        board.canMove(command, currentTurn.getCurrentTurnPlayer());
        return move(command);
    }

    private TurnStatus move(final Command command) {
        return board.update(currentTurn.getNextTurnPlayer(), command);
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void surrender(final Player surrenderPlayer) {
        this.surrenderPlayer = surrenderPlayer;
    }

    @Override
    public void setPlayerNames(final String whitePlayerName, final String blackPlayerName) {
        white = new Player(whitePlayerName, PlayerColor.WHITE);
        black = new Player(blackPlayerName, PlayerColor.BLACK);
    }

    @Override
    public Game initiate() {
        currentTurn = new Turn(black, white);
        board = new Board(black, white);
        commandParser = new CommandParserImpl();

        return this;
    }
}
