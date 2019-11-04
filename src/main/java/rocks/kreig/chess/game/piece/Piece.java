package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.board.Cell;
import rocks.kreig.chess.game.exception.InvalidMovementException;
import rocks.kreig.chess.game.player.Player;
import rocks.kreig.chess.game.player.TurnStatus;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static rocks.kreig.chess.game.player.PlayerColor.WHITE;

/** Abstract operations for Pieces, defines generic movement and capture logic, and cell boundaries updates */
public abstract class Piece implements PieceMoveStrategy {

    private final Player owner;

    private Cell currentCell;
    private final Cell originalCell;

    private List<Cell> allowedCellsToMove = new LinkedList<>();
    private boolean captured;

    public Piece(final Player player, final Cell cell) {
        this.owner = player;
        this.currentCell = cell;
        this.originalCell = cell;

        this.allowedCellsToMove.addAll(updateCellMovementCandidates(cell));
    }

    /**
     * Checks for valid destination cells by filtering currently owned cells
     */
    public boolean canMove(final Cell origin, final Cell destination) throws InvalidMovementException {

        Optional<Cell> allowedCell = allowedCellsToMove.parallelStream()
                .filter(cell -> cell.equals(destination)).findFirst();

        if (allowedCell.isPresent()) {
            final Cell cell = allowedCell.get();
            final Piece piece = cell.getPiece();

            return piece == null || !piece.getOwner().equals(this.getOwner());
        }
        return false;
    }

    public TurnStatus move(final Cell destinationCell) {
        // update piece references
        this.currentCell.setPiece(null);

        // update current cell references
        this.currentCell = destinationCell;

        return updateAllowedMovements(this.currentCell);
    }

    @Override
    public String toString() {
        if (WHITE == owner.getPlayerColor()) {
            return String.valueOf(charRepresentation()).toLowerCase();
        }
        return String.valueOf(charRepresentation());
    }

    public Player getOwner() {
        return owner;
    }

    Cell getCurrentCell() {
        return currentCell;
    }

    Cell getOriginalCell() {
        return originalCell;
    }

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(final boolean captured) {
        this.captured = captured;
    }

    public void updateCurrentPieceMovements() {
        this.allowedCellsToMove.clear();
        this.allowedCellsToMove.addAll(updateCellMovementCandidates(this.currentCell));
    }

    public abstract String getType();

    /** Updates current piece list of allowed movements for the specified cell */
    private TurnStatus updateAllowedMovements(final Cell cell) {
        final Player currentPlayer = getOwner();
        final Piece destinationPiece = cell.getPiece();

        final TurnStatus turnStatus = new TurnStatus();

        // capture piece if available
        if (destinationPiece != null && !destinationPiece.getOwner().equals(currentPlayer) && destinationPiece.charRepresentation() != 'K') {
            currentPlayer.capture(destinationPiece);
            turnStatus.capture(destinationPiece, currentPlayer);
        }

        cell.setPiece(this);

        this.allowedCellsToMove.clear();
        this.allowedCellsToMove.addAll(updateCellMovementCandidates(cell));

        // Look for check movement
        allowedCellsToMove.parallelStream()
                .map(Cell::getPiece)
                .filter(Objects::nonNull)
                .filter(piece -> piece.charRepresentation() == 'K' && !piece.getOwner().equals(this.getOwner()))
                .findFirst()
                .ifPresent(piece -> turnStatus.check(piece.getOwner()));

        cell.setPiece(this);

        return turnStatus;
    }

}
