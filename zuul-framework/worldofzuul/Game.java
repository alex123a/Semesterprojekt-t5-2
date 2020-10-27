package worldofzuul;

import worldofzuul.Rooms.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    private Parser parser;
    private Room currentRoom;


    public Game() {
        createRooms();
        parser = new Parser();
    }


    private void createRooms() {
        Room RoadBuild, Town, Beach, Farm, Park, Sdu;
        File description = new File("C:\\Users\\janik\\Documents\\GitHub\\Semesterprojekt-t5-2\\zuul-framework\\txt\\roomDescription.txt");

        Scanner reader;
        try {
            reader = new Scanner(description);
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
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("You have completed 100% of the road in plastic.");
    }

    private void printWelcome() {
        File gameDescription = new File("C:\\Users\\janik\\Documents\\GitHub\\Semesterprojekt-t5-2\\zuul-framework\\txt\\gameDescription.txt");

        Scanner reader;
        try {
            reader = new Scanner(gameDescription);
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
        }
        return wantToQuit;
    }

    private void printHelp() {
        File gameDescription = new File("C:\\Users\\janik\\Documents\\GitHub\\Semesterprojekt-t5-2\\zuul-framework\\txt\\help.txt");

        Scanner reader;
        try {
            reader = new Scanner(gameDescription);
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
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}
