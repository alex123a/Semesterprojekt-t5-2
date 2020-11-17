package sample.domain.Rooms;

import sample.domain.PlasticElements.Plastic;
import sample.domain.Room;

public class Sdu extends Room {
    public Sdu(String description) {
        super(description);
    }

    public Plastic[] generatePlasticArray() {
        return new Plastic[0];
    }
}
