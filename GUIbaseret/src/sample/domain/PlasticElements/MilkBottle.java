package sample.domain.PlasticElements;

public class MilkBottle extends Plastic {
    private static int counterForMilkBottles = 0;

    public MilkBottle() {
        super("Milk bottle", "src/sample/presentation/pictures/plastic/milkBottle.png");
        counterForMilkBottles++;
    }

    public static int getCounterForMilkBottles() {
        return counterForMilkBottles;
    }
}
