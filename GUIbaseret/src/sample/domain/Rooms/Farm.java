package sample.domain.Rooms;

import sample.domain.PlasticElements.*;

public class Farm extends Room {
    private Plastic[] numberOfPlastic;

    public Farm() {
        super("src/sample/presentation/pictures/Backgrounds/Farm.png");
        super.setPlasticArray(generatePlasticArray());
    }

    public Plastic[] generatePlasticArray() {
        int cleaningPlastics = 2;
        this.numberOfPlastic = new Plastic[cleaningPlastics];

        for (int i = 0; i < this.numberOfPlastic.length; i++) {
            this.numberOfPlastic[i] = new CleaningPlastic();
        }

        return this.numberOfPlastic;
    }
}
