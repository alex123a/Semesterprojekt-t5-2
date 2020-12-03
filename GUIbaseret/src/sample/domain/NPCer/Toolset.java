package sample.domain.NPCer;

import sample.domain.RoadBuilder;
import sample.presentation.Controller;

public class Toolset {
    final private int repair = 25;
    final private String image = "src/sample/data/textfiles/npcDescriptions/Tool.txt";

    public void repairMachine() {
        Controller.roadBuilder.setDamaged(Controller.roadBuilder.getDamaged() - repair);
        System.out.println(100-Controller.roadBuilder.getDamaged() + "% repaired");
    }

    public int getRepair() {
        return this.repair;
    }
}
