package sample.domain;

import sample.data.ReadFromFile;

public class DialogNPC {
    private String image = "src/sample/presentation/pictures/DialogBox.png";
    private String farmerText = "src/sample/data/textfiles/npcDescriptions/FarmerenText.txt";
    private String professorText = "src/sample/data/textfiles/npcDescriptions/ProfessorText.txt";
    private String mechanicText = "src/sample/data/textfiles/npcDescriptions/VillagerText.txt";
    private String roadbuilderText = "src/sample/data/textfiles/npcDescriptions/DamagedMachine.txt";
    private String playerText = "src/sample/data/textfiles/playerDescriptions/PlayerText.txt";

    public String getImage() {
        return image;
    }

    public String getNPCText (String character, int index) {
        if (character.equals("farmer")) {
            ReadFromFile getNPCText = new ReadFromFile();
            var text = getNPCText.ReadFile(farmerText,index);
            return text;
        } else if (character.equals("professor")) {
            ReadFromFile getNPCText = new ReadFromFile();
            var text = getNPCText.ReadFile(professorText,index);
            return text;
        } else if (character.equals("mechanic")) {
            ReadFromFile getNPCText = new ReadFromFile();
            var text = getNPCText.ReadFile(mechanicText,index);
            return text;
        } else if (character.equals("Road builder")) {
            ReadFromFile getNPCText = new ReadFromFile();
            var text =  getNPCText.ReadFile(roadbuilderText,index);
            return text;
        } else if (character.equals("Player")) {
            ReadFromFile getNPCText = new ReadFromFile();
            var text = getNPCText.ReadFile(playerText, index);
            return text;
        }
        return "not possible";
    }
}