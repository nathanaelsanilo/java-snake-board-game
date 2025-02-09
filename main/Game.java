package main;

public class Game {
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
