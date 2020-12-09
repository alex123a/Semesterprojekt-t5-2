package sample.domain.PlasticElements;

//This class inherits from the Plastic class
public class MilkBottle extends Plastic {
    private static int counterForMilkBottles = 0;

    //Sets the image for milk bottles and count one up everytime it happens
    public MilkBottle() {
        super("Milk bottle", "src/sample/presentation/pictures/plastic/milkBottle.png", -5);
        counterForMilkBottles++;
    }

    public static int getCounterForMilkBottles() {
        return counterForMilkBottles;
    }
}
