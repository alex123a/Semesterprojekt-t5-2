package sample.domain;

import sample.data.ReadFromFile;

public class DialogNPC {
    private String image = "src/sample/presentation/pictures/DialogBox.png";
    private String farmerText = "src/sample/data/textfiles/npcDescriptions/FarmerenText.txt";
    private String professorText = "src/sample/data/textfiles/npcDescriptions/ProfessorText.txt";
    private String mechanicText = "src/sample/data/textfiles/npcDescriptions/VillagerText.txt";
    private String roadbuilderText = "src/sample/data/textfiles/npcDescriptions/DamagedMachine.txt";



    public String getImage() {
        return image;
    }

    public String getNPCText (String NPC, int num) {
        if (NPC.equals("farmer")) {
            ReadFromFile getNPCText = new ReadFromFile();
            return getNPCText.ReadFile(farmerText,num);
        } else if (NPC.equals("professor")) {
            ReadFromFile getNPCText = new ReadFromFile();
            return getNPCText.ReadFile(professorText,num);
        } else if (NPC.equals("mechanic")) {
            ReadFromFile getNPCText = new ReadFromFile();
            return getNPCText.ReadFile(mechanicText,num);
        } else if (NPC.equals("Road builder")) {
            ReadFromFile getNPCText = new ReadFromFile();
            return getNPCText.ReadFile(roadbuilderText,num);
        }
        return "not possible";
    }
}
