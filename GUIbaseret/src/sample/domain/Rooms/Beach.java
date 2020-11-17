package sample.domain.Rooms;

import sample.domain.PlasticElements.*;
import sample.domain.Room;

public class Beach extends Room {
    private Plastic[] numberOfPlastic;

    public Beach(String description) {
        super(description, "src/sample/presentation/pictures/Beach.png");
        super.setPlasticArray(generatePlasticArray());
    }

    public Plastic[] generatePlasticArray() {
        int juiceBottles = 2 + (int) (Math.random() * ((6 - 2) + 1));
        int milkBottles = 2 + (int) (Math.random() * ((4 - 2) + 1));
        int waterBottles = 5 + (int) (Math.random() * ((10 - 5) + 1));
        numberOfPlastic = new Plastic[juiceBottles + milkBottles + waterBottles];
        for (int i = 0; i < numberOfPlastic.length; i++) {
            if (i < juiceBottles) {
                numberOfPlastic[i] = new JuiceBottle();
            } else if (i >= juiceBottles && i < milkBottles + juiceBottles) {
                numberOfPlastic[i] = new MilkBottle();
            } else {
                numberOfPlastic[i] = new WaterBottle();
            }
        }
        return numberOfPlastic;
    }
}
