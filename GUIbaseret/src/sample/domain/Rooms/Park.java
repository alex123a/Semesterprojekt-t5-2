package sample.domain.Rooms;

import sample.domain.PlasticElements.*;
import sample.domain.Room;

public class Park extends Room {
    private Plastic[] numberOfPlastic;

    public Park() {
        super("src/sample/presentation/pictures/Backgrounds/Park.png");
        super.setPlasticArray(generatePlasticArray());
    }

    //Three plastic pieces is put into the the numberOfPlastic array. Which will be displayed in the room by the Controller class.
    public Plastic[] generatePlasticArray() {
        int waterBottles = 3;
        numberOfPlastic = new Plastic[waterBottles];

        for (int i = 0; i < numberOfPlastic.length; i++) {
            numberOfPlastic[i] = new WaterBottle();
        }

        return numberOfPlastic;
    }
}
