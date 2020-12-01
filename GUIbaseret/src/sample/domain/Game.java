package sample.domain;

import javafx.scene.image.ImageView;
import sample.domain.NPCer.*;
import sample.domain.PlasticElements.Plastic;
import sample.domain.Rooms.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import sample.presentation.Controller;


public class Game {
    public static String[] startDirections = {"north", "south", "west", "east"};

    private Room currentRoom;
    final private File welcomeMessage = new File("src/sample/data/textfiles/gameDescriptions/gameDescription.txt");
    final private File help = new File("src/sample/data/textfiles/gameDescriptions/help.txt");
    private static final int roadDone = 30;
    private Room RoadBuild, Town, Beach, Farm, Park, Sdu;
    private Farmer farmer = new Farmer("Farmer");
    private Mechanic mechanic = new Mechanic("Mechanic");
    private Professor professor = new Professor("Professor");
    private Toolset toolset = new Toolset();
    // private List<String> currentDirections = new ArrayList<>(Arrays.asList(startDirections));
    private List<String> currentDirections = new ArrayList<>();
    //sæt privat
    public static String changedRoom;

    public Game() {
        createRooms();
    }


    private void createRooms() {
        RoadBuild = new RoadBuild();
        Town = new Town();
        Beach = new Beach();
        Farm = new Farm();
        Park = new Park();
        Sdu = new Sdu();

        RoadBuild.setExit("east", Farm);
        RoadBuild.setExit("south", Sdu);
        RoadBuild.setExit("west", Beach);
        RoadBuild.setExit("north", Park);

        Sdu.setExit("north", RoadBuild);

        Farm.setExit("west", RoadBuild);

        Beach.setExit("east", RoadBuild);

        Park.setExit("east", Town);
        Park.setExit("south", RoadBuild);

        Town.setExit("west", Park);

        currentRoom = RoadBuild;
    }

    public void play() {

        boolean finished = false;
        /*
        while (!finished) {
            RoadBuilder.damagedMachine();
        }
        */
    }

    private void printHelp() {

        Scanner reader;
        try {
            reader = new Scanner(help);
            System.out.println(reader.nextLine());
            System.out.println(reader.nextLine());
            System.out.println(reader.nextLine());
            System.out.println(reader.nextLine());
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }
    }

    public List<Plastic> placePlastic() {
        return changedRoom != null ? currentRoom.getPlasticInRoom() : RoadBuild.getPlasticInRoom();
    }

    public void goRoom() {
        Room nextRoom = changedRoom != null ? currentRoom.getExit(changedRoom) : RoadBuild;

        if (nextRoom == null) {
            System.out.println("That is not possible!");
        } else {
            currentRoom = nextRoom;

            Controller.roomExit.removeAll(currentDirections);
            for (String directions : currentRoom.getExits().keySet()){
                currentDirections.add(directions);
            }

            Controller.roomExit.addAll(currentDirections);
            currentDirections.removeAll(currentDirections);
            currentRoom.getPlasticTypes();
            Controller.background = currentRoom.getPictureRoom();
        }
    }

    public boolean givePlastic() {
        List<Plastic> plasticInv = Controller.playerObject.getPlasticInv();
        int road = RoadBuilder.inventory(plasticInv);
        Controller.playerObject.resetPlasticInv();
        if (road >= roadDone) {
            return true;
        }
        return false;
    }

    public static int getRoadDone() {
        return roadDone;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
