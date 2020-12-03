package sample.domain.Rooms;

import sample.domain.PlasticElements.*;
import sample.domain.Room;

public class Town extends Room {
    private Plastic[] numberOfPlastic;

    public Town() {
        super("src/sample/presentation/pictures/Backgrounds/Town.png");
        super.setPlasticArray(generatePlasticArray());
    }

    public Plastic[] generatePlasticArray() {
        int cleaningBottles = 4 + (int) (Math.random() * ((3 - 2) + 1));
        int waterBottles = 3 + (int) (Math.random() * ((3 - 2) + 1));
        numberOfPlastic = new Plastic[waterBottles + cleaningBottles];

        for (int i = 0; i < numberOfPlastic.length; i++) {
            numberOfPlastic[i] = i < numberOfPlastic.length/2 ? new WaterBottle() : new CleaningPlastic();
        }

        return numberOfPlastic;
    }
}
