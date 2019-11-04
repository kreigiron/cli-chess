package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.Cell;
import rocks.kreig.chess.game.Player;
import rocks.kreig.chess.game.TurnStatus;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static rocks.kreig.chess.game.PlayerColor.WHITE;

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

    public abstract char charRepresentation();

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

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(final Cell currentCell) {
        this.currentCell = currentCell;
    }


    /**
     * Checks for valid destination cells by filtering currently owned cells
     */
    @Override
    public boolean canMove(final Cell origin, final Cell destination) {

        Optional<Cell> allowedCell = allowedCellsToMove.parallelStream()
                .filter(cell -> cell.equals(destination)).findFirst();

        if (allowedCell.isPresent()) {
            final Cell cell = allowedCell.get();
            final Piece piece = cell.getPiece();

            return piece == null || !piece.getOwner().equals(this.getOwner());
        }
        return false;
    }

    public Cell getOriginalCell() {
        return originalCell;
    }

    @Override
    public TurnStatus move(final Cell sourceCell, final Cell destinationCell) {
        // update piece references
        this.currentCell.setPiece(null);
        // update current cell references
        this.currentCell = destinationCell;

        return updateAllowedMovements(this.currentCell);
    }


    public List<Cell> getAllowedCellsToMove() {
        return allowedCellsToMove;
    }

    TurnStatus updateAllowedMovements(final Cell currentCell) {
        final Player owner = getOwner();
        final Piece destinationPiece = currentCell.getPiece();

        final TurnStatus turnStatus = new TurnStatus();

        // capture piece if available
        if (destinationPiece != null && !destinationPiece.getOwner().equals(owner) && destinationPiece.charRepresentation() != 'K') {
            owner.capture(destinationPiece);
            turnStatus.capture(destinationPiece, owner);
        }

        currentCell.setPiece(this);

        this.allowedCellsToMove.clear();
        this.allowedCellsToMove.addAll(updateCellMovementCandidates(currentCell));

        // Look for check movement
        allowedCellsToMove.parallelStream()
                .map(Cell::getPiece)
                .filter(Objects::nonNull)
                .filter(piece -> piece.charRepresentation() == 'K' && !piece.getOwner().equals(this.getOwner()))
                .findFirst()
                .ifPresent(piece -> turnStatus.check(piece.getOwner()));

        currentCell.setPiece(this);

        return turnStatus;
    }

    public abstract List<Cell> updateCellMovementCandidates(final Cell destinationCell);


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
}
