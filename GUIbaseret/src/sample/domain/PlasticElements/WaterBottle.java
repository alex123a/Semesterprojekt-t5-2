package sample.domain.PlasticElements;

public class WaterBottle extends Plastic {
    private static int counterForWaterBottles = 0;

    //Sets the image for water bottles and count one up everytime it happens
    public WaterBottle() {
        super("Water bottle", "src/sample/presentation/pictures/plastic/waterBottle.png", 4);
        counterForWaterBottles++;
    }

    public static int getCounterForWaterBottles() {
        return counterForWaterBottles;
    }
}
