package sample.domain.PlasticElements;

public class CleaningPlastic extends Plastic {
    private static int counterForCleaningPlastic = 0;

    //Sets the image for cleaning plastic and count one up everytime it happens
    public CleaningPlastic() {
        super("Cleaning plastic", "src/sample/presentation/pictures/plastic/cleaningPlastic.png", -1);
        counterForCleaningPlastic++;
    }

    public static int getCounterForCleaningPlastic() {
        return counterForCleaningPlastic;
    }
}
