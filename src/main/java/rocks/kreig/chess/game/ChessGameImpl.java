package rocks.kreig.chess.game;

public class ChessGameImpl implements Game {

    private Turn currentTurn;
    private Player white;
    private Player black;

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
    public void tryMove(final String command) {

    }

    @Override
    public Board getBoard() {
        return new Board();
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
        currentTurn = new Turn(white, black);

        return this;
    }
}
