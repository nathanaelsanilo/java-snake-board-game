package main;

public class Dice {
    public int roll() {
        // Math.random() return 0.0 <= 1.0
        // multiple by 6 return 0.0 <= 5.999...
        // use type casting (int) to make int
        return (int) (Math.random() * 6 + 1);
    }
}
