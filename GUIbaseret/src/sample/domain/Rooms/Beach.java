package sample.domain.Rooms;

import sample.domain.PlasticElements.*;
import sample.domain.Room;

import java.util.Random;

public class Beach extends Room {
    private Plastic[] numberOfPlastic;

    public Beach() {
        super("src/sample/presentation/pictures/Backgrounds/Coast.png");
        super.setPlasticArray(generatePlasticArray());
    }

    public Plastic[] generatePlasticArray() {
        Random random = new Random();
        int sodaBottles = random.nextInt(3 - 2 + 1) + 2;
        int milkBottles = random.nextInt(3 - 2 + 1) + 1;
        int waterBottles = random.nextInt(8 - 7 + 1) + 7;
        numberOfPlastic = new Plastic[sodaBottles + milkBottles + waterBottles];
        for (int i = 0; i < numberOfPlastic.length; i++) {
            if (i < sodaBottles) {
                numberOfPlastic[i] = new SodaBottle();
            } else if (i >= sodaBottles && i < milkBottles + sodaBottles) {
                numberOfPlastic[i] = new MilkBottle();
            } else {
                numberOfPlastic[i] = new WaterBottle();
            }
        }
        return numberOfPlastic;
    }
}
