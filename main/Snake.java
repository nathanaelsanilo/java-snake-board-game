package main;

import java.util.Scanner;

public class Snake {
    private int head = 0;
    private int tail = 0;

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    public Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    public static Snake[] buildSnakes(int total) {
        Scanner scanner = new Scanner(System.in);
        Snake[] snakes = new Snake[total];
        for (int i = 0; i < snakes.length; i++) {
            String input = scanner.nextLine();
            int head = Integer.parseInt(input.split(" ")[0]);
            int tail = Integer.parseInt(input.split(" ")[1]);
            snakes[i] = new Snake(head, tail);
        }

        return snakes;
    }
}
