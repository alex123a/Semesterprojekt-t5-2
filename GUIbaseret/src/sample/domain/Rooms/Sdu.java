package sample.domain.Rooms;

import sample.domain.PlasticElements.Plastic;
import sample.domain.Room;

public class Sdu extends Room {
    public Sdu() {
        super("src/sample/presentation/pictures/Backgrounds/SDU.png");
    }

    public Plastic[] generatePlasticArray() {
        return new Plastic[0];
    }
}
