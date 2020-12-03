package sample.domain.Rooms;

import sample.domain.PlasticElements.*;
import sample.domain.Room;

public class Beach extends Room {
    private Plastic[] numberOfPlastic;

    public Beach() {
        super("src/sample/presentation/pictures/Backgrounds/Coast.png");
        super.setPlasticArray(generatePlasticArray());
    }

    public Plastic[] generatePlasticArray() {
        int sodaBottles = 2 + (int) (Math.random() * ((3 - 2) + 1));
        int milkBottles = 2 + (int) (Math.random() * ((3 - 2) + 1));
        int waterBottles = 5 + (int) (Math.random() * ((8 - 7) + 1));
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
