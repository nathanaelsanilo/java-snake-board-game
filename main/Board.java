package main;

public class Board {
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
            if (position == snakes[i].getHead()) {
                return snakes[i];
            }
        }

        return null;
    }

    public Ladder getLadderByPosition(int position) {
        for (int i = 0; i < ladders.length; i++) {
            if (position == ladders[i].getHead()) {
                return ladders[i];
            }
        }

        return null;
    }

    public int getFinishPosition() {
        return size;
    }

}
