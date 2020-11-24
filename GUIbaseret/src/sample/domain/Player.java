package sample.domain;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import sample.domain.PlasticElements.Plastic;
import sample.presentation.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private static String name;
    private static List<Plastic> plasticInv = new ArrayList();
    private static boolean haveToolset = false;
    // private List<Tool> toolInv = new ArrayList();
    // Her er der taget udgangspunkt i et "Tool" objekt og et "Plastic" objekt.

    public static void setName() {
        System.out.print("What is your name?\n> ");
        boolean nameChosen = false;
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
    }

    public static String getName() {
        return name;
    }

    public static void plasticCollect(Plastic piece, Room room) {
        if (room.getPlasticInRoom().size() > 0) {
            plasticInv.add(piece);
        } else {
            System.out.println("The room is empty");
        }
    }

    public static void setPlasticInv(List<Plastic> newInv) {
        plasticInv = newInv;
    }

    public static List<Plastic> getPlasticInv() {
        return plasticInv;
    }

    public static void setHaveToolset(boolean haveToolsetPara) {
        haveToolset = haveToolsetPara;
    }

    public static boolean getHaveToolset() {
        return haveToolset;
    }

    public static void resetPlasticInv() {
        plasticInv = new ArrayList<>();
    }
}

