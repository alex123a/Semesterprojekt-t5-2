package sample.domain.Rooms;

import sample.domain.PlasticElements.*;
import sample.domain.Room;

import java.util.Random;

public class Town extends Room {
    private Plastic[] numberOfPlastic;

    public Town() {
        super("src/sample/presentation/pictures/Backgrounds/Town.png");
        super.setPlasticArray(generatePlasticArray());
    }

    //Generates a plastic array with random plastic objects
    public Plastic[] generatePlasticArray() {
        Random random = new Random();
        /*
        random.nextInt gives a random number
        The bound inside the parentheses is the the number that maximum can be created minus one. cleaningBottles is 3-2+1=2, so there can be created two numbers, 0 to 1.
        The number outside is merged with the number in the parentheses, so the number will be 2 or 3 waterBottles or cleaningBottles.
        */
        int cleaningBottles = random.nextInt(3 - 2 + 1) + 2;
        int waterBottles = random.nextInt(3 - 2 + 1) + 2;
        numberOfPlastic = new Plastic[waterBottles + cleaningBottles];

        for (int i = 0; i < numberOfPlastic.length; i++) {
            numberOfPlastic[i] = i < numberOfPlastic.length/2 ? new WaterBottle() : new CleaningPlastic();
        }

        return numberOfPlastic;
    }
}
