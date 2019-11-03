package rocks.kreig.chess.game;

import rocks.kreig.chess.game.piece.Piece;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private final String name;
    private final PlayerColor playerColor;

    private List<Piece> pieceList = new LinkedList<>();

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

    @Override
    public String toString() {
        return "[" + playerColor + "] " + name;
    }

    public void addPiece(final Piece piece) {
        pieceList.add(piece);
    }
}
