package sample.domain.PlasticElements;

public class SodaBottle extends Plastic {
    private static int counterForSodaBottles = 0;

    //Sets the image for soda bottles and count one up everytime it happens
    public SodaBottle() {
        super("Soda bottle", "src/sample/presentation/pictures/plastic/sodaBottle.png", 1);
        counterForSodaBottles++;
    }

    public static int getCounterForSodaBottles() {
        return counterForSodaBottles;
    }
}
