package sample.domain.NPCs;

import sample.presentation.Main;

public class Toolset {
    // is used when repairing Roadbuilder
    final private int repair = 25;
    final private String image = "src/sample/presentation/pictures/npc/Tool.png";

    // removes the damage on Roadbuilder by 25
    public void repairMachine() {
        Main.game.getRoadBuilder().setDamaged(Main.game.getRoadBuilder().getDamaged() - repair);
    }

    public int getRepair() {
        return this.repair;
    }

    public String getImage() {
        return this.image;
    }
}
