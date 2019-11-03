package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.Cell;
import rocks.kreig.chess.game.Player;
import rocks.kreig.chess.game.TurnStatus;

import java.util.LinkedList;
import java.util.List;

import static rocks.kreig.chess.game.PlayerColor.WHITE;

public abstract class Piece implements PieceMoveStrategy {

    private final Player owner;
    private Cell currentCell;
    private final Cell originalCell;

    private final List<Cell> allowedCellsToMove = new LinkedList<>();

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

    /** Checks for valid destination cells by filtering currently owned cells */
    @Override
    public boolean canMove(final Cell origin, final Cell destination) {
        return allowedCellsToMove.parallelStream()
                .filter(cell -> !cell.getPiece().getOwner().equals(origin.getPiece().getOwner()) && cell.getPiece().charRepresentation() != 'K')
                .anyMatch(destination::equals);
    }

    public Cell getOriginalCell() {
        return originalCell;
    }

    public TurnStatus move(final Cell sourceCell, final Cell destinationCell) {
        this.currentCell = destinationCell;
        return updateAllowedMovements(destinationCell);
    }


    public List<Cell> getAllowedCellsToMove() {
        return allowedCellsToMove;
    }

    private TurnStatus updateAllowedMovements(final Cell destinationCell) {
        final Player owner = getOwner();
        final Piece destinationPiece = destinationCell.getPiece();

        final TurnStatus turnStatus = new TurnStatus();

        // capture piece if available
        if (destinationPiece != null && !destinationPiece.getOwner().equals(owner) && destinationPiece.charRepresentation() != 'K'){
            owner.capture(destinationPiece);
            turnStatus.capture(destinationPiece, owner);

        }

        final List<Cell> candidates = updateCellMovementCandidates(destinationCell);

        // Look for check movement
        candidates.parallelStream()
                .map(Cell::getPiece)
                .filter(piece -> piece.charRepresentation() == 'K' && !piece.getOwner().equals(this.getOwner()))
                .findFirst()
                .ifPresent(piece -> turnStatus.check(piece.getOwner()));

        destinationCell.setPiece(this);

        return turnStatus;
    }

    abstract List<Cell> updateCellMovementCandidates(final Cell destinationCell);


}
