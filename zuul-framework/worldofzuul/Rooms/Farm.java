package worldofzuul.Rooms;

import worldofzuul.CleaningPlastic;
import worldofzuul.Plastic;
import worldofzuul.Room;

public class Farm extends Room {
    private static Plastic[] numberOfPlastic;

    public Farm(String description) {
        super(description, generatePlasticArray());
    }

    public static Plastic[] generatePlasticArray() {
        int cleaningPlastics = 3 + (int) (Math.random() * ((5 - 3) + 1));
        numberOfPlastic = new Plastic[cleaningPlastics];

        for (int i = 0; i < numberOfPlastic.length; i++) {
            numberOfPlastic[i] = new CleaningPlastic();
        }

        return numberOfPlastic;
    }
}
