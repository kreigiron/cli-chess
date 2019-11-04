package rocks.kreig.chess.game.player;

import rocks.kreig.chess.game.piece.Piece;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Player {
    private final String name;
    private final PlayerColor playerColor;

    private List<Piece> pieceList = new LinkedList<>();
    private List<Piece> capturesList = new LinkedList<>();

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Player player = (Player) o;
        return playerColor == player.playerColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerColor);
    }

    public void capture(final Piece destinationPiece) {
        this.capturesList.add(destinationPiece);
        destinationPiece.setCaptured(true);
    }

    public String getStats() {
        String stats = "Stats for " + this + ":\n";
        final List piecesAvailable = pieceList.stream().filter(piece -> !piece.isCaptured()).collect(Collectors.toList());
        final List piecesCaptured = pieceList.stream().filter(Piece::isCaptured).collect(Collectors.toList());
        stats += "Pieces available: " + piecesAvailable.size() + " \n "  + piecesAvailable;
        stats += "\n";
        stats += "Captured pieces: " + piecesCaptured.size() + " \n "  + piecesCaptured;
        stats += "\n";
        stats += "Captures: " + capturesList.size() + " \n "  + capturesList;

        return stats;

    }
}
