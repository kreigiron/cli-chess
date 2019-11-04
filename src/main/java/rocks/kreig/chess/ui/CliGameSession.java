package rocks.kreig.chess.ui;

import rocks.kreig.chess.game.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CliGameSession implements GameSession {

    public static final String VALID_NAME_REGEX = "\\w{1,16}";

    private final Pattern validNamePattern = Pattern.compile(VALID_NAME_REGEX);

    private final Game chessGame;
    private Scanner scanner;

    public CliGameSession(final Game chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            this.scanner = scanner;

            greet();
            renamePlayers();
            play();

        } catch (IOException e) {
            System.out.println("Error while reading user information.");
            e.printStackTrace();
        }
    }

    private void greet() {
        //TODO: replace with a nicer welcome
        System.out.println("Welcome to CLI-CHESS");
    }

    private void renamePlayers() throws IOException {
        chessGame.setPlayerNames(parsePlayerName(PlayerColor.WHITE), parsePlayerName(PlayerColor.BLACK));
    }

    private String parsePlayerName(final PlayerColor playerColor) throws IOException {
        System.out.println("Please select " + playerColor.name() + " player name");

        while (true) {
            final String name = scanner.nextLine();

            if (name.isEmpty()) {
                return playerColor.name().toLowerCase();
            }

            final Matcher matcher = validNamePattern.matcher(name);

            if (matcher.matches()) {
                return name;
            }

            System.out.println("Name should be up to 16 chars long and should contains only alphanumeric or underscore characters");

        }
    }

    private void play() throws IOException {
        System.out.println("Game start!");
        System.out.println("==========");

        chessGame.initiate();
        while (chessGame.hasNextTurn()) {
            final Turn turn = chessGame.nextTurn();
            playerTurn(turn);
        }
    }

    private void playerTurn(Turn turn) {
        final Player currentPlayer = turn.getCurrentTurnPlayer();

        while (true) {
            try {
                System.out.println(currentPlayer.getName() + " - [" + currentPlayer.getPlayerColor() + "]" + " turn, please provide movement: ");

                final String command = scanner.nextLine();

                if ("BOARD".equalsIgnoreCase(command)) {
                    System.out.println(chessGame.getBoard());
                } else if ("STATS".equalsIgnoreCase(command)) {
                    System.out.println(currentPlayer.getStats());
                } else if ("SURRENDER".equalsIgnoreCase(command)) {
                    chessGame.surrender(currentPlayer);
                    System.out.println(turn.getCurrentTurnPlayer() + " surrender, " + turn.getNextTurnPlayer() + " wins");

                    return;
                } else {
                    final TurnStatus turnStatus;

                    turnStatus = chessGame.tryMove(command);

                    if (turnStatus.getCaptured() != null) {
                        System.out.println(turnStatus.getCapturedBy() + " captures " + turnStatus.getCaptured() + " from the rival" );
                    }

                    if (turnStatus.isChecked()) {
                        System.out.println(turn.getNextTurnPlayer() + " checked by " + turnStatus.getCheckedBy() );
                    }
                    System.out.println("Board status:");
                    System.out.println(chessGame.getBoard());

                    return;
                }
            } catch (InvalidMovementException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
