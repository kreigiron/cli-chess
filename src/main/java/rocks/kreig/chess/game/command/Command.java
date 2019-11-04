package rocks.kreig.chess.game.command;

public class Command {
    public static final int MOVE_COMMAND = 1;

    private final int moveCommand;

    private final int xStartPosition;
    private final int yStartPosition;
    private final int xEndPosition;
    private final int yEndPosition;

    private final String algebraicSourceCoordinates;
    private final String algebraicDestinationCoordinates;

    public Command(int moveCommand, int xStartPosition, int yStartPosition, int xEndPosition, int yEndPosition, final String algebraicSourceCoordinates, final String algebraicDestinationCoordinates) {
        this.moveCommand = moveCommand;
        this.xStartPosition = xStartPosition;
        this.yStartPosition = yStartPosition;
        this.xEndPosition = xEndPosition;
        this.yEndPosition = yEndPosition;
        this.algebraicSourceCoordinates = algebraicSourceCoordinates;
        this.algebraicDestinationCoordinates = algebraicDestinationCoordinates;
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

    public String getAlgebraicSourceCoordinates() {
        return algebraicSourceCoordinates;
    }

    public String getAlgebraicDestinationCoordinates() {
        return algebraicDestinationCoordinates;
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
