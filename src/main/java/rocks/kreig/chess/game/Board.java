package rocks.kreig.chess.game;

import rocks.kreig.chess.game.piece.Bishop;
import rocks.kreig.chess.game.piece.King;
import rocks.kreig.chess.game.piece.Knight;
import rocks.kreig.chess.game.piece.Pawn;
import rocks.kreig.chess.game.piece.Piece;
import rocks.kreig.chess.game.piece.Queen;
import rocks.kreig.chess.game.piece.Rook;

class Board {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private final Cell[][] cells = new Cell[64][64];

    private Board() {
        initializeCells();
    }

    public Board(Player black, Player white) {
        this();
        initializePieces(black, white);
    }

    private void initializePieces(final Player black, final Player white) {
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

    private void addRook(final Player player, final Cell cell) {
        final Piece piece = new Rook(player, cell);
        linkPiece(player, cell, piece);
    }

    private void addKnight(Player player, Cell cell) {
        final Piece piece = new Knight(player, cell);
        linkPiece(player, cell, piece);
    }

    private void addBishop(Player player, Cell cell) {
        final Piece piece = new Bishop(player, cell);
        linkPiece(player, cell, piece);
    }

    private void addQueen(final Player player, final Cell cell) {
        final Piece piece = new Queen(player, cell);
        linkPiece(player, cell, piece);
    }

    private void addKing(final Player player, final Cell cell) {
        final Piece piece = new King(player, cell);
        linkPiece(player, cell, piece);
    }

    private void addPawn(final Player player, final Cell cell) {
        final Piece piece = new Pawn(player, cell);
        linkPiece(player, cell, piece);
    }

    private void linkPiece(final Player player, final Cell cell, final Piece piece) {
        player.addPiece(piece);
        cell.setPiece(piece);
    }

    private void initializeCells() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    @Override
    public String toString() {
        String board = "\n";

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final Cell currentCell =cells[j][i];
                board = board.concat((
                        currentCell.isShaded() ?
                                ANSI_BLACK_BACKGROUND + ANSI_WHITE + " " + currentCell + " " :
                                ANSI_WHITE_BACKGROUND + ANSI_BLACK + " " + currentCell + " ") + ANSI_RESET);
            }
            board = board.concat("\n");
        }

        return board;
    }
}
