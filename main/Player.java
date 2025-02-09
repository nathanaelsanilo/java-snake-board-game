package main;

import java.util.Scanner;

public class Player {
    private String playerName;
    private int position = 0;
    private Dice dice;
    private Board board;
    private boolean isFinished = false;

    public Player(String playerName, Dice dice, Board board) {
        this.playerName = playerName;
        this.dice = dice;
        this.board = board;
    }

    public static Player[] buildPlayers(int total, Dice dice, Board board) {
        Scanner scanner = new Scanner(System.in);
        Player[] players = new Player[total];
        for (int i = 0; i < players.length; i++) {
            String playerName = scanner.nextLine();
            players[i] = new Player(playerName, dice, board);
        }

        return players;
    }

    private boolean hasReachedMaxPosition(int step) {
        return position + step > board.getFinishPosition();
    }

    private void printPlayerTurn(String name, int position, int step) {
        System.out.println(name + " rolled a " + step + " and moved from " + position + " to " + (position + step));
    }

    private void printPlayerWon(String name) {
        System.out.println(name + " wins the game");
    }

    public boolean move() {
        int step = dice.roll();

        if (hasReachedMaxPosition(step)) {
            System.out.println(
                    playerName + " rolled a " + step + " and reached the max position: " + board.getFinishPosition());
            isFinished = false;
            return isFinished;
        }

        printPlayerTurn(playerName, position, step);

        // move player position
        position = position + step;

        Snake snake = isStepOnSnake(position);
        if (snake != null) {
            System.out
                    .println(playerName + " stepped on snake at " + snake.getHead() + ", moving to " + snake.getTail());
            position = snake.getTail();
        }

        Ladder ladder = isStepOnLadder(position);
        if (ladder != null) {
            System.out.println(
                    playerName + " stepped on ladder at " + ladder.getHead() + ", moving to " + ladder.getTail());
            position = ladder.getTail();
        }

        if (isFinished(position)) {
            isFinished = true;
            printPlayerWon(playerName);
        }

        return isFinished;
    }

    private Snake isStepOnSnake(int position) {
        Snake snake = board.getSnakeByPosition(position);

        if (snake == null) {
            return null;
        }

        return snake;
    }

    private Ladder isStepOnLadder(int position) {
        Ladder ladder = board.getLadderByPosition(position);

        if (ladder == null) {
            return null;
        }

        return ladder;
    }

    public boolean isFinished(int position) {
        return position == board.getFinishPosition();
    }

}
