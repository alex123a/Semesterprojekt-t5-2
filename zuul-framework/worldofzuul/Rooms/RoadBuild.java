package worldofzuul.Rooms;

import worldofzuul.Room;
import worldofzuul.PlasticElements.*;

public class RoadBuild extends Room {
    private static Plastic[] numberOfPlastic;

    public RoadBuild(String description) {
        super(description, generatePlasticArray());
    }

    public static Plastic[] generatePlasticArray() {
        int juiceBottles = 4 + (int) (Math.random() * ((5 - 4) + 1));
        int waterBottles = 3 + (int) (Math.random() * ((5 - 3) + 1));
        numberOfPlastic = new Plastic[waterBottles + juiceBottles];

        for (int i = 0; i < numberOfPlastic.length; i++) {
            numberOfPlastic[i] = i < numberOfPlastic.length/2 ? new WaterBottle() : new JuiceBottle();
        }

        return numberOfPlastic;
    }
}
