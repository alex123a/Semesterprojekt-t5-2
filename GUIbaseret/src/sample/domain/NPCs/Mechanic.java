package sample.domain.NPCs;

import sample.presentation.Main;

public class Mechanic extends NPC {
    private boolean gaveToolset = false;

    public Mechanic(String name) {
        super(name, "src/sample/presentation/pictures/npc/Mechanic.png");
    }

    //Gives Toolset object to Player
    public Toolset giveToolset() {
        this.gaveToolset = true;
        Main.game.getPlayerObject().setHaveToolset(true);
        return new Toolset();
    }

    public void resetGaveToolSet() {
        this.gaveToolset = false;
    }
}
