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

    //Generates a plastic array with random plastic objects
    public Plastic[] generatePlasticArray() {
        Random random = new Random();
        /*
        random.nextInt gives a random number
        The bound inside the parentheses is the the number that maximum can be created minus one. sodaBottels is 3-2+1=2, so there can be created two numbers, 0 to 1.
        The number outside is merged with the number in the parentheses, so in the first case the number will be 2 or 3 sodaBottles.
        waterBottles: 8-7+1=2 (0 to 1)+7
        */
        int sodaBottles = random.nextInt(3 - 2 + 1) + 2;
        int milkBottles = random.nextInt(2 - 1 + 1) + 1;
        int waterBottles = random.nextInt(8 - 7 + 1) + 7;
        //Number of plastic is counted
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
