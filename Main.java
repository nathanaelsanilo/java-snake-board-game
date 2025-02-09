import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Game is started!");

        System.out.println("Enter the total number of snakes: ");
        int totalSnakes = Integer.parseInt(scanner.nextLine());
        Snake[] snakes = new Snake[totalSnakes];
        for (int i = 0; i < snakes.length; i++) {
            String input = scanner.nextLine();
            int head = Integer.parseInt(input.split(" ")[0]);
            int tail = Integer.parseInt(input.split(" ")[1]);
            snakes[i] = new Snake(head, tail);
        }

        System.out.println("Enter the total number of ladders: ");
        int totalLadders = Integer.parseInt(scanner.nextLine());
        Ladder[] ladders = new Ladder[totalLadders];
        for (int i = 0; i < ladders.length; i++) {
            String input = scanner.nextLine();
            int head = Integer.parseInt(input.split(" ")[0]);
            int tail = Integer.parseInt(input.split(" ")[1]);
            ladders[i] = new Ladder(head, tail);
        }

        Dice dice = new Dice();
        Board board = new Board(15, snakes, ladders);
        Player[] players = {
                new Player("alpha", dice, board),
                new Player("beta", dice, board),
        };

        Game game = new Game(players);
        game.play();

    }
}

class Game {
    private Player[] players;
    private int turn = 0;
    private boolean isFinished = false;

    public Game(Player[] players) {
        this.players = players;
    }

    public void play() {
        while (!isFinished) {
            Player player = getPLayerTurn(turn);
            isFinished = player.move();
            nextTurn();
        }
    }

    private Player getPLayerTurn(int givenTurn) {
        return players[givenTurn];
    }

    private void nextTurn() {
        turn++;
        if (turn > players.length - 1) {
            turn = 0;
        }
    }
}

class Player {
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

class Snake {
    public int head = 0;
    public int tail = 0;

    public Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }
}

class Ladder {
    public int head = 0;
    public int tail = 0;

    public Ladder(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }
}

class Board {
    private int size;
    private int[] boards;
    private Snake[] snakes;
    private Ladder[] ladders;

    public Board(int size, Snake[] snakes, Ladder[] ladders) {
        this.size = size;
        this.snakes = snakes;
        this.ladders = ladders;

        initialize(size);
    }

    private void initialize(int n) {
        boards = new int[n];
        for (int i = 0; i < n; i++) {
            boards[i] = i;
        }
    }

    public Snake getSnakeByPosition(int position) {
        for (int i = 0; i < snakes.length; i++) {
            if (position == snakes[i].head) {
                return snakes[i];
            }
        }

        return null;
    }

    public Ladder getLadderByPosition(int position) {
        for (int i = 0; i < ladders.length; i++) {
            if (position == ladders[i].head) {
                return ladders[i];
            }
        }

        return null;
    }

    public int getFinishPosition() {
        return size;
    }

}

class Dice {
    public int roll() {
        // Math.random() return 0.0 <= 1.0
        // multiple by 6 return 0.0 <= 5.999...
        // use type casting (int) to make int
        return (int) (Math.random() * 6 + 1);
    }
}
