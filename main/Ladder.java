package main;

import java.util.Scanner;

class Ladder {
    private int head = 0;
    private int tail = 0;

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    public Ladder(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    public static Ladder[] buildLadders(int total) {
        Scanner scanner = new Scanner(System.in);
        Ladder[] ladders = new Ladder[total];
        for (int i = 0; i < ladders.length; i++) {
            String input = scanner.nextLine();
            int head = Integer.parseInt(input.split(" ")[0]);
            int tail = Integer.parseInt(input.split(" ")[1]);
            ladders[i] = new Ladder(head, tail);
        }

        return ladders;
    }
}
