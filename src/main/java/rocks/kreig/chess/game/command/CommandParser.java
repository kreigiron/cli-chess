package rocks.kreig.chess.game.command;

import rocks.kreig.chess.game.exception.InvalidMovementException;

public interface CommandParser {
    Command parse(String commandString) throws InvalidMovementException;
}
