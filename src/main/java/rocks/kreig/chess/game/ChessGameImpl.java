package rocks.kreig.chess.game;

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
    public CheckStatus tryMove(final String commandString) throws InvalidMovementException {
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
        System.out.println(command);
    //    validateBounds();
//        validatePieceMovement();
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
        board = new Board();
        commandParser = new CommandParserImpl();

        return this;
    }
}
