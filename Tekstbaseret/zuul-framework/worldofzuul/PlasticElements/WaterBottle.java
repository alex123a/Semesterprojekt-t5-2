package worldofzuul.PlasticElements;

public class WaterBottle extends Plastic {
    private static int counterForWaterBottles = 0;


    public WaterBottle() {
        super("Water bottle");
        counterForWaterBottles++;
    }

    public static int getCounterForWaterBottles() {
        return counterForWaterBottles;
    }
}
