package sample.domain.Rooms;

import sample.domain.PlasticElements.*;
import sample.domain.Room;

public class RoadBuild extends Room {
    private Plastic[] numberOfPlastic;

    public RoadBuild() {
        super("src/sample/presentation/pictures/Backgrounds/RoadBuild.png");
        super.setPlasticArray(generatePlasticArray());
    }

    //Five plastic pieces is put into the the numberOfPlastic array. Which will be displayed in the room by the Controller class.
    public Plastic[] generatePlasticArray() {
        int sodaBottles = 2;
        int waterBottles = 18;
        numberOfPlastic = new Plastic[waterBottles + sodaBottles];

        for (int i = 0; i < numberOfPlastic.length; i++) {
            numberOfPlastic[i] = i < numberOfPlastic.length/2 ? new WaterBottle() : new SodaBottle();
        }

        return numberOfPlastic;
    }
}
