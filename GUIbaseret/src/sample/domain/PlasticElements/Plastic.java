package sample.domain.PlasticElements;

import java.util.Random;

public class Plastic {
    /**
     * @param name name of the plastic type
     * @param image path to the image of the plastic type
     * @param adjustXForInventory makes the x-value for the specific plastic image fit in the inventory
     */
    private double[] position = new double[2];
    private String image;
    private String name;
    private static int counter = 0;
    private int adjustXForInventory;


    //If the plastic object created Isn't specified
    public Plastic() {
        this.name = "Unknown plastic type";
        counter++;
    }

    /*Creating af specified piece of plastic and adjusting it's picture in the inventory slots and when
    the plastic is set in the map.
     */
    public Plastic(String name, String image, int adjustXForInventory) {
        this.name = name;
        this.image = image;
        this.adjustXForInventory = adjustXForInventory;
        counter++;
        this.newPosition();
    }

    //Spawning the plastic in random locations on the map
    public void newPosition() {
        Random random = new Random();
        this.position[0] = random.nextInt( 320 + 320 + 1) - 320;
        this.position[1] = random.nextInt(200 + 200 + 1) - 200;
    }

    @Override
    public String toString() {
        return this.name;
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

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static void setCounter(int counter) {
        Plastic.counter = counter;
    }

    public int getAdjustXForInventory() {
        return adjustXForInventory;
    }
}
