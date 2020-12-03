package sample.domain.NPCer;

import sample.domain.Player;
import sample.presentation.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Mechanic extends NPC {
    private boolean gaveToolset = false;
    private String file = new File("src/sample/data/textfiles/npcDescriptions/VillagerText.txt").toString();


    public Mechanic(String name) {
        super(name,"src/sample/presentation/pictures/npc/Mechanic.png");
    }

    public Toolset giveToolset() {
        this.gaveToolset = true;
        Controller.playerObject.setHaveToolset(true);
        return new Toolset();
    }
}
