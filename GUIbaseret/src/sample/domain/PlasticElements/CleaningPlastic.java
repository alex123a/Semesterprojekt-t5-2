package sample.domain.PlasticElements;

public class CleaningPlastic extends Plastic {
    private static int counterForCleaningPlastic = 0;

    public CleaningPlastic() {
        super("Cleaning plastic");
        counterForCleaningPlastic++;
    }

    public static int counterForCleaningPlastic() {
        return counterForCleaningPlastic;
    }
}
