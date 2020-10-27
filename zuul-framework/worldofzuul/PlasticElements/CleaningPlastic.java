package worldofzuul;

public class CleaningPlastic extends worldofzuul.Plastic {
    private static int counterForCleaningPlastic = 0;

    public CleaningPlastic() {
        super("Cleaning plastic");
        counterForCleaningPlastic++;
    }

    public static int counterForCleaningPlastic() {
        return counterForCleaningPlastic;
    }
}
