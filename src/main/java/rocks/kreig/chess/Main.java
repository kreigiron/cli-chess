package rocks.kreig.chess;

import rocks.kreig.chess.game.ChessGameImpl;
import rocks.kreig.chess.game.Game;
import rocks.kreig.chess.ui.GameSession;
import rocks.kreig.chess.ui.CliGameSession;

public class Main {

    public static void main(String args[]) {

        final Game chessGame = new ChessGameImpl();

        final GameSession gameSession = new CliGameSession(chessGame);
        gameSession.start();
    }
}
