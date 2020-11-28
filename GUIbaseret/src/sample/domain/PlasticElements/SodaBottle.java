package sample.domain.PlasticElements;

public class SodaBottle extends Plastic {
    private static int counterForSodaBottles = 0;

    public SodaBottle() {
        super("Soda bottle", "src/sample/presentation/pictures/plastic/sodaBottle.png");
        counterForSodaBottles++;
    }

    public static int getCounterForSodaBottles() {
        return counterForSodaBottles;
    }
}
