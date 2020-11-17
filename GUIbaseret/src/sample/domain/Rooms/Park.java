package sample.domain.Rooms;

import sample.domain.PlasticElements.*;
import sample.domain.Room;

public class Park extends Room {
    private Plastic[] numberOfPlastic;

    public Park(String description) {
        super(description);
        super.setPlasticArray(generatePlasticArray());
    }

    public Plastic[] generatePlasticArray() {
        int waterBottles = 3 + (int) (Math.random() * ((6 - 3) + 1));
        numberOfPlastic = new Plastic[waterBottles];

        for (int i = 0; i < numberOfPlastic.length; i++) {
            numberOfPlastic[i] = new WaterBottle();
        }

        return numberOfPlastic;
    }
}
