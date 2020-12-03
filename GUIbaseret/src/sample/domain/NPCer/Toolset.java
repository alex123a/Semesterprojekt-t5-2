package sample.domain.NPCer;

import sample.domain.RoadBuilder;
import sample.presentation.Controller;

public class Toolset {
    final private int repair = 25;
    final private String image = "src/sample/data/textfiles/npcDescriptions/Tool.txt";

    public void repairMachine(int damaged) throws InterruptedException {
        // Mangler at tilføje tid, således statementet under bliver kørt en gang i sekundet.

        while(Controller.roadBuilder.getDamaged() != 0 || Controller.roadBuilder.getDamaged() > 0) {
            long millis = System.currentTimeMillis();
            Controller.roadBuilder.setDamaged(Controller.roadBuilder.getDamaged() - repair);
            System.out.println(100-Controller.roadBuilder.getDamaged() + "% repaired");
            try {
                Thread.sleep(1000 - millis % 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public int getRepair() {
        return this.repair;
    }
}
