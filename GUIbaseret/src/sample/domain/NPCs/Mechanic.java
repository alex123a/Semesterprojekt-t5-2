package sample.domain.NPCs;

import sample.presentation.Controller;
import sample.presentation.Main;

public class Mechanic extends NPC {
    private boolean gaveToolset = false;
    private String file = "src/sample/data/textfiles/npcDescriptions/VillagerText.txt";


    public Mechanic(String name) {
        super(name,"src/sample/presentation/pictures/npc/Mechanic.png");
    }

    public Toolset giveToolset() {
        this.gaveToolset = true;
        Main.game.getPlayerObject().setHaveToolset(true);
        return new Toolset();
    }
}
