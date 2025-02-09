package main;

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
        Board board = new Board(100, snakes, ladders);

        System.out.println("Enter the total number of players: ");
        int totalPlayers = Integer.parseInt(scanner.nextLine());
        Player[] players = new Player[totalPlayers];
        for (int i = 0; i < players.length; i++) {
            String name = scanner.nextLine();
            players[i] = new Player(name, dice, board);
        }

        Game game = new Game(players);
        game.play();

    }
}
