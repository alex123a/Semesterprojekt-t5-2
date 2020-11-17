package sample.user.Rooms;

import sample.user.PlasticElements.Plastic;
import sample.user.Room;

public class Sdu extends Room {
    public Sdu(String description) {
        super(description);
    }

    public Plastic[] generatePlasticArray() {
        return new Plastic[0];
    }
}
