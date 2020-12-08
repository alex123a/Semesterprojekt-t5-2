package sample.domain.NPCer;

public class Professor extends NPC {
    //Insert path into config file.
    private String file = "src/sample/data/textfiles/npcDescriptions/ProfessorText.txt";

    public Professor(String name) {
        super(name, "src/sample/presentation/pictures/npc/Professor.png");
    }
}
