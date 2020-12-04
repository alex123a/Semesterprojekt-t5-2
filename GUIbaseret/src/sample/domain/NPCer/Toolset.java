package sample.domain.NPCer;

import sample.domain.RoadBuilder;
import sample.presentation.Controller;

public class Toolset {
    final private int repair = 25;
    final private String image = "src/sample/presentation/pictures/npc/Tool.png";

    public void repairMachine() {
        Controller.roadBuilder.setDamaged(Controller.roadBuilder.getDamaged() - repair);
    }

    public int getRepair() {
        return this.repair;
    }

    public String getImage() {
        return this.image;
    }
}
