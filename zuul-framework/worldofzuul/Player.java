package worldofzuul;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;
    private ArrayList<worldofzuul.Plastic> plasticInv = new ArrayList();
    // private ArrayList<Tool> toolInv = new ArrayList();
    // Her er der taget udgangspunkt i et "Tool" objekt og et "Plastic" objekt.


    public Player(String name) {
        this.name = name;
    }

    public static Player newPlayerObject() {
        System.out.print("What is your name?\n> ");
        boolean nameChosen = false;
        String name = "";
        while (!nameChosen) { // In this while loop we check for a name that is valid (No only space names) etc...
            Scanner playerName = new Scanner(System.in);
            name = playerName.nextLine();
            if (name.matches(".*[0-9].*") || name.matches(".*[A-Z]*.")) {
                nameChosen = true;
            } else {
                System.out.print("Name not vaild enter new name\n> ");
            }

        }
        System.out.println("You have chosen " + name + " as your player name");
        return new Player(name);
    }

    public void plasticCollect(worldofzuul.Plastic piece) {
        this.plasticInv.add(piece);

    }

}

