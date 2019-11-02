package rocks.kreig.chess.game;

public class Command {
    public static final int MOVE_COMMAND = 1;

    private final int moveCommand;

    private final int xStartPosition;
    private final int yStartPosition;
    private final int xEndPosition;
    private final int yEndPosition;

    public Command(int moveCommand, int xStartPosition, int yStartPosition, int xEndPosition, int yEndPosition) {
        this.moveCommand = moveCommand;
        this.xStartPosition = xStartPosition;
        this.yStartPosition = yStartPosition;
        this.xEndPosition = xEndPosition;
        this.yEndPosition = yEndPosition;
    }

    public int getMoveCommand() {
        return moveCommand;
    }

    public int getxStartPosition() {
        return xStartPosition;
    }

    public int getyStartPosition() {
        return yStartPosition;
    }

    public int getxEndPosition() {
        return xEndPosition;
    }

    public int getyEndPosition() {
        return yEndPosition;
    }

    @Override
    public String toString() {
        return "Command{" +
                "moveCommand=" + moveCommand +
                ", xStartPosition=" + xStartPosition +
                ", yStartPosition=" + yStartPosition +
                ", xEndPosition=" + xEndPosition +
                ", yEndPosition=" + yEndPosition +
                '}';
    }
}
