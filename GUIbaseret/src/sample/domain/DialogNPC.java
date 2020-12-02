package sample.domain;

import sample.data.GetNPCText;

public class DialogNPC {
    private String image = "src/sample/presentation/pictures/DialogBox.png";
    private String farmerText = "src/sample/data/textfiles/npcDescriptions/FarmerenText.txt";
    private String professorText = "src/sample/data/textfiles/npcDescriptions/ProfessorText.txt";
    private String mechanicText = "src/sample/data/textfiles/npcDescriptions/VillagerText.txt";



    public String getImage() {
        return image;
    }

    public String getNPCText (String NPC, int num) {
        if (NPC.equals("farmer")) {
            GetNPCText getNPCText = new GetNPCText();
            return getNPCText.ReadFile(farmerText,num);
        } else if (NPC.equals("professor")) {
            GetNPCText getNPCText = new GetNPCText();
            return getNPCText.ReadFile(professorText,num);
        } else if (NPC.equals("mechanic")) {
            GetNPCText getNPCText = new GetNPCText();
            return getNPCText.ReadFile(mechanicText,num);
        }
        return "not possible";
    }
}
