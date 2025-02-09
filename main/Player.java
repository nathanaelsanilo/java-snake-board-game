package main;

public class Player {
    public String playerName;
    public int position = 0;
    public Dice dice;
    public Board board;
    private boolean isFinished = false;

    public Player(String playerName, Dice dice, Board board) {
        this.playerName = playerName;
        this.dice = dice;
        this.board = board;
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
            System.out.println(playerName + " stepped on snake at " + snake.head + ", moving to " + snake.tail);
            position = snake.tail;
        }

        Ladder ladder = isStepOnLadder(position);
        if (ladder != null) {
            System.out.println(playerName + " stepped on ladder at " + ladder.head + ", moving to " + ladder.tail);
            position = ladder.tail;
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
