package sample.domain.NPCer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Professor extends NPC {
    //Insert path into config file.
    private String file = new File("src/sample/data/textfiles/npcDescriptions/ProfessorText.txt").toString();

    public Professor(String name) {
        super(name, "src/sample/presentation/pictures/npc/Professor.png");
    }
}
