package sample.domain.PlasticElements;

public class JuiceBottle extends Plastic {
    private static int counterForJuiceBottles = 0;

    public JuiceBottle() {
        super("Juice bottle");
        counterForJuiceBottles++;
    }

    public static int getCounterForJuiceBottles() {
        return counterForJuiceBottles;
    }
}
