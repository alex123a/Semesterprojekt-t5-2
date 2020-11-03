package worldofzuul;

import worldofzuul.PlasticElements.Plastic;
import worldofzuul.Rooms.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Scanner;

public class Game {
    private Parser parser;
    private Room currentRoom;
    private String name;
    final private File welcomeMessage = Paths.get(new File("worldofzuul/textfiles/gameDescription.txt").getAbsolutePath()).toFile();
    final private File roomDescription = Paths.get(new File("worldofzuul/textfiles/roomDescription.txt").getAbsolutePath()).toFile();
    final private File help = Paths.get(new File("worldofzuul/textfiles/help.txt").getAbsolutePath()).toFile();
    private static final int roadDone = 30;
    private Room RoadBuild, Town, Beach, Farm, Park, Sdu;

    public Game() {
        createRooms();
        parser = new Parser();
    }


    private void createRooms() {
        Scanner reader;
        try {
            reader = new Scanner(roomDescription);
            RoadBuild = new RoadBuild(reader.nextLine());
            Town = new Town(reader.nextLine());
            Beach = new Beach(reader.nextLine());
            Farm = new Farm(reader.nextLine());
            Park = new Park(reader.nextLine());
            Sdu = new Sdu(reader.nextLine());
            reader.close();

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
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            RoadBuilder.damagedMachine();
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
    }

    private void printWelcome() {
        Scanner reader;
        Timer.setStartTime();
        try {
            reader = new Scanner(welcomeMessage);
            System.out.println(reader.nextLine());
            System.out.println(reader.nextLine());
            System.out.println(reader.nextLine());
            System.out.println(reader.nextLine());
            System.out.println(reader.nextLine());
            System.out.println();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }
        Player.setName();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.GIVE) {
            if (RoadBuilder.getDamaged() == 0) {
                if (currentRoom == RoadBuild) {
                    if (givePlastic(command)) {
                        System.out.println("You have completed 100% of the road in plastic.");
                        Timer.setEndTime();
                        Timer.timeScore();
                        Timer.setHighScore();
                        wantToQuit = true;
                    }
                } else {
                    System.out.println("Go to the Roadbuilder to give plastic");
                }
            } else {
                System.out.println("Machine \"i am broken, can't help you\"");
            }
        } else if (commandWord == commandWord.COLLECT) {
            Player.plasticCollect(currentRoom.getPlastic(), currentRoom);
        }
        return wantToQuit;
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
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("That is not possible!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            currentRoom.getPlasticTypes();
        }
    }

    private boolean givePlastic(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Do you want to give plastic? Just write give");
            return false;
        }
        ArrayList<Plastic> plasticInv = Player.getPlasticInv();
        ArrayList<Plastic> road = RoadBuilder.inventory(plasticInv);
        Player.resetPlasticInv();
        if (road.size() >= roadDone) {
            return true;
        }
        return false;
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit");
            return false;
        } else {
            return true;
        }

    }

    public static int getRoadDone() {
        return roadDone;
    }

}
