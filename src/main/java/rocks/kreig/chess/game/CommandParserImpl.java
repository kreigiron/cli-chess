package rocks.kreig.chess.game;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static rocks.kreig.chess.game.Command.MOVE_COMMAND;

public class CommandParserImpl implements CommandParser {
    private static final String MOVE_COMMAND_REGEX = "^MOVE\\s+([A-H])([1-8])\\s+TO\\s+([A-H])([1-8])$";
    private final Pattern moveCommandPattern = Pattern.compile(MOVE_COMMAND_REGEX, Pattern.CASE_INSENSITIVE);

    @Override
    public Command parse(final String commandString) throws InvalidMovementException {
        final Matcher matcher = moveCommandPattern.matcher(commandString.toUpperCase());

        if(matcher.matches()) {
            final MatchResult matchResult = matcher.toMatchResult();
            final int xStartPosition = getCoordinate(matchResult.group(1));
            final int yStartPosition = Integer.parseInt(matchResult.group(2)) - 1;

            final int xEndPosition = getCoordinate(matchResult.group(3));
            final int yEndPosition = Integer.parseInt(matchResult.group(4)) - 1;

            return new Command(MOVE_COMMAND, xStartPosition, yStartPosition, xEndPosition, yEndPosition);

        }

        throw new InvalidMovementException("Invalid command, please provide a valid move with proper file and rank. (e.g. move a1 to b2 ");

    }

    private int getCoordinate(String fileLetter) {
        char fileLetterChar = fileLetter.charAt(0);
        return fileLetterChar - 65;
    }
}
