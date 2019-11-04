package rocks.kreig.chess.game;

import rocks.kreig.chess.game.piece.Bishop;
import rocks.kreig.chess.game.piece.King;
import rocks.kreig.chess.game.piece.Knight;
import rocks.kreig.chess.game.piece.Pawn;
import rocks.kreig.chess.game.piece.Piece;
import rocks.kreig.chess.game.piece.Queen;
import rocks.kreig.chess.game.piece.Rook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Board {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private final Cell[][] cells = new Cell[64][64];

    private final Map<Player, List<Piece>> currentPieces = new HashMap<>();

    private Board() {
        initializeCells();
    }

    public Board(Player black, Player white) {
        this();
        initializePieces(black, white);
    }

    private void initializePieces(final Player black, final Player white) {

        currentPieces.put(black, new ArrayList<>());
        currentPieces.put(white, new ArrayList<>());

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // rooks
                if ((i == 0 || i == 7) && j == 0) {
                    addRook(white, cells[i][j]);
                }
                if ((i == 0 || i == 7) && j == 7) {
                    addRook(black, cells[i][j]);
                }

                // knights
                if ((i == 1 || i == 6) && j == 0) {
                    addKnight(white, cells[i][j]);
                }
                if ((i == 1 || i == 6) && j == 7) {
                    addKnight(black, cells[i][j]);
                }

                // bishops
                if ((i == 2 || i == 5) && j == 0) {
                    addBishop(white, cells[i][j]);
                }
                if ((i == 2 || i == 5) && j == 7) {
                    addBishop(black, cells[i][j]);
                }

                // Queens
                if (i == 3 && j == 0) {
                    addQueen(white, cells[i][j]);
                }
                if (i == 3 && j == 7) {
                    addQueen(black, cells[i][j]);
                }

                // Kings
                if (i == 4 && j == 0) {
                    addKing(white, cells[i][j]);
                }
                if (i == 4 && j == 7) {
                    addKing(black, cells[i][j]);
                }

                // pawns
                if (j == 1) {
                    addPawn(white, cells[i][j]);
                }
                if (j == 6) {
                    addPawn(black, cells[i][j]);
                }

            }
        }
    }

    private Piece addRook(final Player player, final Cell cell) {
        final Piece piece = new Rook(player, cell);
        return linkPiece(player, cell, piece);
    }

    private Piece addKnight(Player player, Cell cell) {
        final Piece piece = new Knight(player, cell);
        return linkPiece(player, cell, piece);
    }

    private Piece addBishop(Player player, Cell cell) {
        final Piece piece = new Bishop(player, cell);
        return linkPiece(player, cell, piece);
    }

    private Piece addQueen(final Player player, final Cell cell) {
        final Piece piece = new Queen(player, cell);
        return linkPiece(player, cell, piece);
    }

    private Piece addKing(final Player player, final Cell cell) {
        final Piece piece = new King(player, cell);
        return linkPiece(player, cell, piece);
    }

    private Piece addPawn(final Player player, final Cell cell) {
        final Piece piece = new Pawn(player, cell);
        return linkPiece(player, cell, piece);
    }

    private Piece linkPiece(final Player player, final Cell cell, final Piece piece) {
        player.addPiece(piece);
        cell.setPiece(piece);

        currentPieces.get(piece.getOwner()).add(piece);
        return piece;
    }

    private void initializeCells() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new Cell(i, j, this);
            }
        }
    }

    public Optional<Cell> getCell(int file, int rank) {
        if (file >= 0 && file < 8 && rank >= 0 && rank < 8) {
            return Optional.of(cells[file][rank]);
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        String board = "\n";

        board = board.concat("   1  2  3  4  5  6  7  8 \n");
        for (int i = 0; i < 8; i++) {
            board = board.concat((char) ('A' + i) + " ");

            for (int j = 0; j < 8; j++) {
                final Cell currentCell = cells[i][j];
                board = board.concat((
                        currentCell.isShaded() ?
                                ANSI_BLACK_BACKGROUND + ANSI_WHITE + " " + currentCell + " " :
                                ANSI_WHITE_BACKGROUND + ANSI_BLACK + " " + currentCell + " ") + ANSI_RESET);
            }
            board = board.concat("\n");
        }


        return board;
    }

    public void canMove(final Command command, final Player currentTurnPlayer) throws InvalidMovementException {
        final Cell sourceCell = cells[command.getxStartPosition()][command.getyStartPosition()];
        final Cell destinationCell = cells[command.getxEndPosition()][command.getyEndPosition()];

        final Piece piece = sourceCell.getPiece();

        if (piece == null) {
            throw new InvalidMovementException("There's no piece to move at " + command.getAlgebraicSourceCoordinates());
        }

        if (!piece.getOwner().equals(currentTurnPlayer)) {
            throw new InvalidMovementException("Cannot move other's player piece at " + command.getAlgebraicSourceCoordinates());
        }

        if (!piece.canMove(sourceCell, destinationCell)) {
            throw new InvalidMovementException("Movement not allowed for piece  " + piece + " to destination " + destinationCell);
        }
    }

    public void recalculateValidMovements() {

    }

    public TurnStatus update(final Player nextTurnPlayer, final Command command) {
        final Cell sourceCell = cells[command.getxStartPosition()][command.getyStartPosition()];
        final Piece piece = sourceCell.getPiece();
        final Cell destinationCell = cells[command.getxEndPosition()][command.getyEndPosition()];

        final TurnStatus turnStatus = piece.move(sourceCell, destinationCell);

        updateNextPlayerAllowedMoves(nextTurnPlayer);

        return turnStatus;
    }

    private void updateNextPlayerAllowedMoves(final Player nextTurnPlayer) {
        this.currentPieces.get(nextTurnPlayer).stream()
                .filter(piece -> !piece.isCaptured())
                .forEach(Piece::updateCurrentPieceMovements);
    }

}
