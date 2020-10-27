package worldofzuul;

public class MilkBottle extends Plastic {
    private static int counterForMilkBottles = 0;

    public MilkBottle() {
        super("Milk bottle");
        counterForMilkBottles++;
    }

    public static int getCounterForMilkBottles() {
        return counterForMilkBottles;
    }
}