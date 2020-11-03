package worldofzuul.NPCer;

import worldofzuul.RoadBuilder;

public class Toolset {
    final private int repair = 25;

    public boolean repairMachine(int damaged) throws InterruptedException {
        boolean repaired = false;
        // Mangler at tilføje tid, således statementet under bliver kørt en gang i sekundet.

        while(RoadBuilder.getDamaged() <= 0) {
            long millis = System.currentTimeMillis();
            RoadBuilder.setDamaged(RoadBuilder.getDamaged() - repair);
            System.out.println(100-RoadBuilder.getDamaged() + "% repaired");
            Thread.sleep(1000 - millis % 1000);

        }
        return repaired = true;
    }

    public int getRepair() {
        return this.repair;
    }
}
