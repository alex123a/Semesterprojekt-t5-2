package sample.domain;

import sample.data.GetNPCText;

public class DialogNPC {
    private String image = "src/sample/presentation/pictures/DialogBox.png";
    private String farmerText = "src/sample/data/textfiles/npcDescriptions/FarmerenText.txt";
    private String professorText = "src/sample/data/textfiles/npcDescriptions/ProfessorText.txt";
    private String mechanicText = "src/sample/data/textfiles/npcDescriptions/VillagerText.txt";
    private String roadbuilderText = "src/sample/data/textfiles/npcDescriptions/DamagedMachine.txt";
    private String playerText ="src/sample/data/textfiles/playerDescriptions/PlayerText.txt";

    public String getImage() {
        return image;
    }

    public String getNPCText (String character, int index) {
        if (character.equals("farmer")) {
            GetNPCText getNPCText = new GetNPCText();
            var text = getNPCText.ReadFile(farmerText,index);
            return text;
        } else if (character.equals("professor")) {
            GetNPCText getNPCText = new GetNPCText();
            var text = getNPCText.ReadFile(professorText,index);
            return text;
        } else if (character.equals("mechanic")) {
            GetNPCText getNPCText = new GetNPCText();
            var text = getNPCText.ReadFile(mechanicText,index);
            return text;
        } else if (character.equals("Road builder")) {
            GetNPCText getNPCText = new GetNPCText();
            var text = getNPCText.ReadFile(roadbuilderText,index);
            return text;
        } else if (character.equals("Player")){
            GetNPCText getNPCText = new GetNPCText();
            var text = getNPCText.ReadFile(playerText,index);
            return text;
        }
        return "not possible";
    }
}
