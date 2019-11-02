package rocks.kreig.chess.game;

class Board {
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
        String board = "+-------------------------------+\n";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board = board.concat(cells[i][j].isShaded() ? "| x " : "| o ");
            }
            board = board.concat("|\n");
            board = board.concat("+-------------------------------+\n");
        }
        return board;
    }
}
