package rocks.kreig.chess.game;

class Board {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private final Cell[][] cells = new Cell[64][64];

    public Board() {
        initializeCells();
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
                board = board.concat((
                        cells[i][j].isShaded() ?
                                ANSI_BLACK_BACKGROUND + ANSI_WHITE + " x " :
                                ANSI_WHITE_BACKGROUND + ANSI_BLACK + " x ") + ANSI_RESET);
            }
            board = board.concat("\n");
        }
        return board;
    }
}
