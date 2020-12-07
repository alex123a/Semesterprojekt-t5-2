package sample.domain.Rooms;

import sample.domain.PlasticElements.*;

import java.util.Random;

public class Town extends Room {
    private Plastic[] numberOfPlastic;

    public Town() {
        super("src/sample/presentation/pictures/Backgrounds/Town.png");
        super.setPlasticArray(generatePlasticArray());
    }

    public Plastic[] generatePlasticArray() {
        Random random = new Random();
        int cleaningBottles = random.nextInt(3 - 2 + 1) + 2;
        int waterBottles = random.nextInt(3 - 2 + 1) + 2;
        numberOfPlastic = new Plastic[waterBottles + cleaningBottles];

        for (int i = 0; i < numberOfPlastic.length; i++) {
            numberOfPlastic[i] = i < numberOfPlastic.length/2 ? new WaterBottle() : new CleaningPlastic();
        }

        return numberOfPlastic;
    }
}
