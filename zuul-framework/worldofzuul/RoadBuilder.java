package worldofzuul;

import java.util.ArrayList;

public class RoadBuilder {
    static ArrayList<Command> inventory = new ArrayList<>();

    public RoadBuilder() {
    }

    public static ArrayList<Command> inventory(Command plastic) {
        inventory.add(plastic);
        System.out.println("The road is " + inventory.size() + " of 30 Complete");
        return inventory;
    }



}
