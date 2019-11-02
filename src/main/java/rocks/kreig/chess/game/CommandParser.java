package rocks.kreig.chess.game;

public interface CommandParser {
    Command parse(String commandString) throws InvalidMovementException;
}
