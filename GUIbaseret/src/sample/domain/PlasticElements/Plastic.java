package sample.domain.PlasticElements;

public class Plastic {
    // private double[] position = new double[2]; // Used for GUI
    private String name;
    private static int counter = 0;

    public Plastic() {
        this.name = "Unknown plastic type";
        counter++;
    }

    public Plastic(String name) {
        this.name = name;
        counter++;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
