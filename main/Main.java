package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dice dice = new Dice();
        System.out.println("Game is started!");

        System.out.println("Enter the total number of snakes: ");
        int totalSnakes = Integer.parseInt(scanner.nextLine());
        Snake[] snakes = Snake.buildSnakes(totalSnakes);

        System.out.println("Enter the total number of ladders: ");
        int totalLadders = Integer.parseInt(scanner.nextLine());
        Ladder[] ladders = Ladder.buildLadders(totalLadders);

        Board board = new Board(100, snakes, ladders);

        System.out.println("Enter the total number of players: ");
        int totalPlayers = Integer.parseInt(scanner.nextLine());
        Player[] players = Player.buildPlayers(totalPlayers, dice, board);

        Game game = new Game(players);
        game.play();
    }
}
