package rocks.kreig.chess;

import rocks.kreig.chess.game.ChessGame;
import rocks.kreig.chess.game.Game;
import rocks.kreig.chess.ui.GameSession;
import rocks.kreig.chess.ui.CliGameSession;

/** CLI point of entry */
public class Main {

    public static void main(String args[]) {

        final Game chessGame = new ChessGame();

        final GameSession gameSession = new CliGameSession(chessGame);
        gameSession.start();
    }
}
