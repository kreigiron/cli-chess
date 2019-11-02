package rocks.kreig.chess.game;

public class ChessGameImpl implements Game {

    private Turn currentTurn;
    private Player white;
    private Player black;

    private Board board;

    private CommandParser commandParser;

    @Override
    public boolean hasNextTurn() {
        return !isCheckMate();
    }

    private boolean isCheckMate() {
        return false;
    }

    @Override
    public Turn nextTurn() {
        return currentTurn.next();
    }

    @Override
    public CheckStatus tryMove(final String commandString) {
        final Command command = commandParser.parse(commandString);
        validateMovement(currentTurn, command);
        return move(command);
    }

    private CheckStatus move(Command command) {
        // final CheckStatus checkStatus = board.update(command);
        //return checkStatus;
        return null;
    }

    private void validateMovement(Turn currentTurn, Command command) {
    //    validateBounds();
//        validatePieceMovement();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void surrender(final Player surrenderPlayer) {

    }

    @Override
    public void setPlayerNames(final String whitePlayerName, final String blackPlayerName) {
        white = new Player(whitePlayerName, PlayerColor.WHITE);
        black = new Player(blackPlayerName, PlayerColor.BLACK);
    }

    @Override
    public Game initiate() {
        currentTurn = new Turn(black, white);
        board = new Board();

        return this;
    }
}
