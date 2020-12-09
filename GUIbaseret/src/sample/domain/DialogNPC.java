package sample.domain;

import sample.data.FileHandler;

public class DialogNPC {
    //Paths for textFiles
    private String image = "src/sample/presentation/pictures/keyItems/DialogBox.png";
    private String farmerText = "src/sample/data/textfiles/npcDescriptions/FarmerText.txt";
    private String professorText = "src/sample/data/textfiles/npcDescriptions/ProfessorText.txt";
    private String mechanicText = "src/sample/data/textfiles/npcDescriptions/VillagerText.txt";
    private String roadbuilderText = "src/sample/data/textfiles/npcDescriptions/RoadbuilderText.txt";
    private String playerText = "src/sample/data/textfiles/playerDescriptions/PlayerText.txt";
    private String fishermanText = "src/sample/data/textfiles/npcDescriptions/FishermanText.txt";
    private String oldLadyText = "src/sample/data/textfiles/npcDescriptions/OldLadyText.txt";


    public String getImage() {
        return image;
    }

    // Is used to read from textfiles
    public String getNPCText(String character, int index) {
        // if the character name matches on of these if statements, their textfile will be loaded.
        // else, it will return a string with no content from the textfiles.
        if (character.equals("farmer")) {
            FileHandler getNPCText = new FileHandler();
            String text = getNPCText.ReadFile(farmerText, index);
            return text;
        } else if (character.equals("professor")) {
            FileHandler getNPCText = new FileHandler();
            String text = getNPCText.ReadFile(professorText, index);
            return text;
        } else if (character.equals("mechanic")) {
            FileHandler getNPCText = new FileHandler();
            String text = getNPCText.ReadFile(mechanicText, index);
            return text;
        } else if (character.equals("Road builder")) {
            FileHandler getNPCText = new FileHandler();
            String text = getNPCText.ReadFile(roadbuilderText, index);
            return text;
        } else if (character.equals("Player")) {
            FileHandler getNPCText = new FileHandler();
            String text = getNPCText.ReadFile(playerText, index);
            return text;
        } else if (character.equals("Fisherman")) {
            FileHandler getNPCText = new FileHandler();
            String text = getNPCText.ReadFile(fishermanText, index);
            return text;
        } else if (character.equals("oldLady")) {
            FileHandler getNPCText = new FileHandler();
            String text = getNPCText.ReadFile(oldLadyText, index);
            return text;
        }
        return "not possible";
    }
}
