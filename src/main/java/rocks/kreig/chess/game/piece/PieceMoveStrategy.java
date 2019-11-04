package rocks.kreig.chess.game.piece;

import rocks.kreig.chess.game.board.Cell;

import java.util.List;

/** Strategy for piece's movements, defines operations that should be implemented on each concrete piece */
public interface PieceMoveStrategy {

    /** Specifies current char representation for the concrete piece in algebraic notation (except pawn which is markes as P) **/
    char charRepresentation();

    /** Updates allowed movement cell collection for the destination cell specified */
    List<Cell> updateCellMovementCandidates(Cell destinationCell);
}
