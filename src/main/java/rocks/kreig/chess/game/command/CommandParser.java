package rocks.kreig.chess.game.command;

import rocks.kreig.chess.game.exception.InvalidMovementException;

/** Implementations for this interface should parse a valid command in string format and returns a Command object */
public interface CommandParser {
    Command parse(String commandString) throws InvalidMovementException;
}
